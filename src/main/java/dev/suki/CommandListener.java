package dev.suki;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.regex.*;


import java.awt.Color;


public class CommandListener extends ListenerAdapter {

    EmbedBuilder embedBuilder = new EmbedBuilder();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        //Imprime as mensagens escritas nos canais que ele tem acesso (para fins de log)
        System.out.println(message);

        if (message.toLowerCase().startsWith("!add")){

            String regex = "https?://[^\\s\"']+\\.(jpg|jpeg|png|gif|webp)";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

            String[] args = message.substring(5).split(" ", 2);
            String nome = args[0];
            String link = args[1];

            Matcher matcher = pattern.matcher(link);

            if (matcher.matches() != true){
                event.getChannel().sendMessage("❌ **Erro:** Você precisa fornecer um link de imagem valido\n" +
                        "Exemplo: https://img.com/meme.jpg").queue();
                return;
            }

            if (args.length != 2){
                event.getChannel().sendMessage("❌ **Erro:** Você precisa fornecer um nome e um link!\n"
                        + "Exemplo: `!add memelegal https://img.com/meme.jpg`").queue();
                return;
            }


            if(Image.insertImageLink(link, nome) && matcher.matches()){
                event.getChannel().sendMessage("✅ **Meme adicionado!**\n📌 Nome: `" + nome + "`\n🖼️ Link: " + link).queue();
            }else {
                event.getChannel().sendMessage("Erro ao enviar sua imagem").queue();
            }

            //adicinar metodo de inserir descrição depois

        }


        //Cria um comando simples que ler "|ping" e devolve Pong.
        if (message.equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        //Comando de julgar a mensagem
        if (message.equalsIgnoreCase("!judge")) {
                embedBuilder.setTitle("CULPADO!⚖️");
                embedBuilder.setImage(Image.getRandomImage());
                embedBuilder.setColor(Color.DARK_GRAY);
                event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();

        }
    }
}
