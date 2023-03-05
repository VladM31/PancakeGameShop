package telegram.bot.nure.botphonenumberprodject.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class InvokeMethodFromTelegramControllers implements CommandHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvokeMethodFromTelegramControllers.class);
    private final InvokeCommandMethod commandMethod;

    public InvokeMethodFromTelegramControllers(
            InvokeCommandMethod commandAndMethod) {
        this.commandMethod = commandAndMethod;
    }

    @Override
    public Object invoke(Update update) {

        var mess = update.getMessage();

        try {
            String command = mess.getText();
            if (command.contains(" ")) {
                command = command.substring(0, mess.getText().indexOf(' '));
            }
            return commandMethod.getOrDefault(command, (u) -> null).apply(update);
        } catch (Exception e) {
            LOGGER.error("Command telegram error", e);
        }

        return null;
    }
}
