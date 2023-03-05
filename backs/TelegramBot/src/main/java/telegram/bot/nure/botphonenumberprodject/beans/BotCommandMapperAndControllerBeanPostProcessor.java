package telegram.bot.nure.botphonenumberprodject.beans;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandController;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandMapper;
import telegram.bot.nure.botphonenumberprodject.handle.InvokeCommandMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class BotCommandMapperAndControllerBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotCommandMapperAndControllerBeanPostProcessor.class);

    private final ApplicationContext context;
    private final InvokeCommandMethod commandAndMethod;
    private final Environment env;

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NotNull String beanName) throws BeansException {
        BotCommandController controller = bean.getClass().getAnnotation(BotCommandController.class);
        if (controller == null) {
            return bean;
        }

        Method[] methods = bean.getClass().getMethods();

        BotCommandMapper mapper;
        for (final Method method : methods) {
            mapper = method.getAnnotation(BotCommandMapper.class);

            if (mapper == null) {
                continue;
            }

            final Object[] parameters = new Object[method.getParameters().length];
            final int indexParameter = setParameters(parameters, method, method.getParameters());

            for (String command : mapper.commands()) {
                if (indexParameter == -1) {
                    commandAndMethod.put(checkProperty(command), (update) -> ReflectionUtils.invokeMethod(method, bean, parameters));
                } else {
                    commandAndMethod.put(checkProperty(command), (update) -> {
                        parameters[indexParameter] = update;
                        return ReflectionUtils.invokeMethod(method, bean, parameters);
                    });
                }

            }

        }

        return bean;
    }

    public String checkProperty(String command) {
        String propertyCommand = useEnvironment(command);
        if (propertyCommand == null) {
            throw new RuntimeException("Property " + command + " was not found");
        }
        if (commandAndMethod.containsCommand(propertyCommand)) {
            throw new RuntimeException("Duplication command = " + propertyCommand);
        }
        return propertyCommand;
    }

    public String useEnvironment(String command) {
        if (!(command.startsWith("${") && command.endsWith("}"))) {
            return command;
        }
        command = env.getProperty(command.substring(2, command.length() - 1));
        return command;
    }

    public int setParameters(Object[] parameters, Method method, Parameter[] methodParameters) {
        int indexUpdateParameter = -1;
        for (int i = 0; i < parameters.length; i++) {
            if (methodParameters[i].getType().isAssignableFrom(Update.class)) {
                indexUpdateParameter = i;
            } else if (methodParameters[i].getAnnotation(Qualifier.class) != null) {
                parameters[i] = context.getBean(methodParameters[i].getAnnotation(Qualifier.class).value());
            } else if (methodParameters[i].getAnnotation(Value.class) != null) {
                parameters[i] = getObjectByTypeParameterFromValue(methodParameters[i]);
            } else {
                try {
                    parameters[i] = context.getBean(methodParameters[i].getType());
                } catch (NoSuchBeanDefinitionException e) {
                    LOGGER.warn("Bean for method " + method.getName() + " not found", e);
                    parameters[i] = null;
                }
            }
        }
        return indexUpdateParameter;
    }

    private static final Map<Class<?>, Function<String, ?>> valueToParameterClass = new HashMap<>();

    static {
        valueToParameterClass.put(Long.class, Long::valueOf);
        valueToParameterClass.put(Integer.class, Integer::valueOf);
        valueToParameterClass.put(Short.class, Short::valueOf);
        valueToParameterClass.put(Byte.class, Byte::valueOf);
        valueToParameterClass.put(String.class, String::valueOf);
        valueToParameterClass.put(Double.class, Double::valueOf);
        valueToParameterClass.put(Float.class, Float::valueOf);
        valueToParameterClass.put(Character.class, s -> s.trim().isEmpty() ? null : s.charAt(0));
        valueToParameterClass.put(LocalDateTime.class, LocalDateTime::parse);
        valueToParameterClass.put(LocalTime.class, LocalTime::parse);
        valueToParameterClass.put(LocalDate.class, LocalDate::parse);
    }

    public Object getObjectByTypeParameterFromValue(Parameter parameter) {
        String value = useEnvironment(parameter.getAnnotation(Value.class).value());

        return valueToParameterClass.get(parameter.getType()).apply(value);
    }
}



