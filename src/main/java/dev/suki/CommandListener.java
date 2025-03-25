package dev.suki;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


import java.awt.Color;


public class CommandListener extends ListenerAdapter {

    EmbedBuilder embedBuilder = new EmbedBuilder();

    private String meme1 = "https://i.redd.it/jk0rf2e2bgud1.jpeg";

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        //Imprime as mensagens escritas nos canais que ele tem acesso (para fins de log)
        System.out.println(message);

        if (message.toLowerCase().startsWith("!add")){

            String[] args = message.substring(5).split(" ", 2);

            if (args.length < 2){
                event.getChannel().sendMessage("❌ **Erro:** Você precisa fornecer um nome e um link!\n"
                        + "Exemplo: `!add memelegal https://img.com/meme.jpg`").queue();
                return;
            }

            String nome = args[0];
            String link = args[1];

            if(Meme.insertImageLink(link, nome)){
                event.getChannel().sendMessage("✅ **Meme adicionado!**\n📌 Nome: `" + nome + "`\n🖼️ Link: " + link).queue();
            }else {
                event.getChannel().sendMessage("Erro ao enviar ao SQL").queue();
            }


            //adicinar metodo de inserir descrição depois
//            Meme.insertImageLink(message.substring("!add".length()), null);
//            event.getChannel().sendMessage("Meme recebido").queue();


        }


        //Cria um comando simples que ler "|ping" e devolve Pong.
        if (message.equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
        //Comando de julgar a mensagem
        if (message.equalsIgnoreCase("!judge")) {
                embedBuilder.setTitle("CULPADO!⚖️");
                embedBuilder.setImage(Meme.getRandomMeme());
                embedBuilder.setColor(Color.DARK_GRAY);
                event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();

        }
    }
}
