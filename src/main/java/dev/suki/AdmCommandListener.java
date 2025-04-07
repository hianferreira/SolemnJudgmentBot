package dev.suki;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class AdmCommandListener extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (message.toLowerCase().startsWith("!clear")){
            String table = message.substring(7);
            Image.deleteTableContent(table);
            event.getChannel().sendMessage("Tabela limpada").queue();
        }
    }
}
