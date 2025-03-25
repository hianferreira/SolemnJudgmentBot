package dev.suki;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;


public class SolemnJudgmentBot {
    public static void main(String[] args) {

        final String TOKEN = Dotenv.load().get("DISCORD_TOKEN");

        try {
            //Inicia o bot e define classes como "Listeners"
            var Jda = JDABuilder.createDefault(TOKEN)
                    //permissão do Gateway do Dicord para ler msg
                    .enableIntents(net.dv8tion.jda.api.requests.GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new SlashCommandListener(),new CommandListener())
                    .build()
                    .awaitReady();
            //Upa os comandos de "slash" para o discord
            Jda.updateCommands().addCommands(
                    Commands.slash("ping", "Responde com Pong!")
            ).queue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


