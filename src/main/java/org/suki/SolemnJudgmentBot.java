package org.suki;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;


public class SolemnJudgmentBot {
    public static void main(String[] args) {

        String token = Dotenv.load().get("DISCORD_TOKEN");


        try {
            //inicia o bot
            var Jda = JDABuilder.createDefault(token)
                    .enableIntents(net.dv8tion.jda.api.requests.GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new SlashCommandListener(),new CommandListener())
                    .build()
                    .awaitReady();

            Jda.updateCommands().addCommands(
                    Commands.slash("ping", "Responde com Pong!")
            ).queue();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


