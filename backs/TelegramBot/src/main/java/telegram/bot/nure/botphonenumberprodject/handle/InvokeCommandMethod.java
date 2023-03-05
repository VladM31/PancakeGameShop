package telegram.bot.nure.botphonenumberprodject.handle;

import java.util.Set;

public interface InvokeCommandMethod {

    HandleRequest get(String command);

    Set<String> getCommands();

    HandleRequest put(String command, HandleRequest handleRequest);

    HandleRequest getOrDefault(String command, HandleRequest handleRequest);

    boolean containsCommand(String command);
}
