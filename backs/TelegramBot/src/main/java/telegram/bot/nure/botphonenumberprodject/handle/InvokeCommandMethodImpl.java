package telegram.bot.nure.botphonenumberprodject.handle;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InvokeCommandMethodImpl implements InvokeCommandMethod {
    private final Map<String, HandleRequest> commandAndMethod;

    public InvokeCommandMethodImpl() {
        this.commandAndMethod = new ConcurrentHashMap<>();
    }

    @Override
    public HandleRequest get(String command) {
        return commandAndMethod.get(command);
    }

    @Override
    public Set<String> getCommands() {
        return commandAndMethod.keySet();
    }

    @Override
    public HandleRequest put(String command, HandleRequest handleRequest) {
        return commandAndMethod.put(command, handleRequest);
    }

    @Override
    public HandleRequest getOrDefault(String command, HandleRequest handleRequest) {
        return commandAndMethod.getOrDefault(command, handleRequest);
    }

    @Override
    public boolean containsCommand(String command) {
        return commandAndMethod.containsKey(command);
    }
}
