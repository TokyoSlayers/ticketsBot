package net.TokyoSlayer.bot.Command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class Maj extends Command {

    public Maj()
    {
        this.name = "maj";
        this.help = "crée une command";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (!event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){
            return;
        }

    }
}