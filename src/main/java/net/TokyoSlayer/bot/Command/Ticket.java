package net.TokyoSlayer.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.TokyoSlayer.bot.Main;
import net.TokyoSlayer.bot.Utils.Files;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.*;
public class Ticket extends Command {

    public Ticket() {
        this.name = "command";
        this.help = "crée une command";
    }

    private static final Files files = Main.files;

    @Override
    protected void execute(CommandEvent event) {
        if(event.getTextChannel().getId().equals("822911433586245672")) {
            if (!event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){
                System.out.println(event.getAuthor().getName()+" don't ave permission to send !!command");
                return;
            }
            if(event.getChannel().hasLatestMessage()){
                event.getChannel().deleteMessageById(event.getChannel().getLatestMessageId()).queue();
            }
            EmbedBuilder eb = new EmbedBuilder();

            eb.setColor(Color.YELLOW);
            eb.setDescription(":tools:  Informations Ticket\n" +
                    "\n" +
                    "<:"+ files.translate("bot.logo.minecraft")+"> Pour un incident sur minecraft."+"\n"+
                    "<:"+files.translate("bot.logo.web")+"> Pour un incident sur le site."+"\n"+
                    "<:"+files.translate("bot.logo.discord")+"> Pour un incident sur discord."+
                    "\n"+"```fix\n" +
                    "Cordialement Tout le staff DropCraft\n" +
                    "```");
            eb.setTitle("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n" +
                    "Ticket" +
                    "\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n");
            eb.setThumbnail("https://dropcraft.net/storage/public/logo_default.png");

            event.getChannel().sendMessage(eb.build()).queue(message -> {
                message.addReaction(files.translate("bot.logo.minecraft")).queue();
                message.addReaction(files.translate("bot.logo.web")).queue();
                message.addReaction(files.translate("bot.logo.discord")).queue();
            });
        }
    }
}