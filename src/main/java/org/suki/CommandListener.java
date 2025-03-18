package org.suki;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();


        System.out.printf("[%s] %#s: %s\n",
                event.getChannel(),
                event.getAuthor(),
                event.getMessage());


        if (message.equalsIgnoreCase("$ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
    }
}
