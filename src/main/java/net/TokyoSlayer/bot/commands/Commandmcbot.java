package net.TokyoSlayer.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;

import java.util.Collections;

import static net.dv8tion.jda.api.Permission.VIEW_CHANNEL;

public class Commandmcbot extends Command{
    
    public Commandmcbot()
    {
        this.name = "command";
        this.help = "crée une command";
    }

    @Override
    protected void execute(CommandEvent event) {
        System.out.println(event.getTextChannel().getId());
        if(event.getTextChannel().getId().equals("765254868751220766")) {

            for(GuildChannel channel : event.getGuild().getChannels()) {
                if (channel.getName().equals(event.getMessage().getAuthor().getName().toLowerCase())) {
                    event.replyInDm("Un chanel a déjà été crée a se nom !");

                }else{
                    createTextChannel(event.getMessage().getMember(), event.getMessage().getAuthor().getName(), event.getGuild());

                    event.replyInDm("Vous pouvez désormais faire votre demande dans le chanel qui a été crée pour vous !");
                }
            }
            if (!event.getMessage().getAuthor().isBot()) {
                event.getMessage().delete().complete();
            }
        }
    }

    public static void createTextChannel(Member member, String name,Guild guild) {
        guild.getCategoryById("766531442129240074").createTextChannel(name)
                .addPermissionOverride(guild.getRoleById("742329743818358876"), null, Collections.singleton(VIEW_CHANNEL))
                .addRolePermissionOverride(guild.getRoleById("742329743818358876").getIdLong(),null,Collections.singleton(VIEW_CHANNEL))

                .addPermissionOverride(member, Collections.singleton(VIEW_CHANNEL), null)
                .addMemberPermissionOverride(member.getIdLong(), Collections.singleton(VIEW_CHANNEL),null)
                .queue();
    }

}
