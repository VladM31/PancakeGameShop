package telegram.bot.nure.botphonenumberprodject.service;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.nure.botphonenumberprodject.events.Event;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TelegramEventsImpl implements TelegramEvents {
    private final static Object PUT_MONITOR = new Object();
    private final Map<Long, List<Event>> events;

    public TelegramEventsImpl(){
        this.events = new ConcurrentHashMap<>();
    }

    @Override
    public boolean put(@NotNull Long chatId, Event request) {
        synchronized (PUT_MONITOR){
            if(!events.containsKey(chatId)){
                events.put(chatId,new ArrayList<>());
            }
        }
        return events.get(chatId).add(request);
    }

    @Override
    public Event remove(Long chatId) {
        return new EventList(events.remove(chatId));
    }

    @Override
    public boolean removeIf(Long chatId, Predicate<Event> predicate) {
        return events.get(chatId).removeIf(predicate);
    }

    @Override
    public Event get(Long chatId) {
        return new EventList(events.get(chatId));
    }

    @Override
    public Event getOrDefault(Long chatId, Event request) {
        return new EventList(events.getOrDefault(chatId, List.of(request)));
    }

    @PreDestroy
    private void destroy(){
        events.forEach((id,es) -> es.forEach(Event::destroy));
    }

    private record EventList(
            List<Event> eventList) implements Event {
        @Override
        public void destroy() {
            eventList.forEach(Event::destroy);
        }

        @Override
        public Object apply(Update update) {
            return eventList.stream().map(e -> e.apply(update)).collect(Collectors.toList());
        }

        @Override
        public boolean isExpired() {
            return eventList.stream().allMatch(Event::isExpired);
        }

        @Override
        public Event setMessage(Message message) {
            eventList.forEach(e -> e.setMessage(message));
            return this;
        }
    }
}
