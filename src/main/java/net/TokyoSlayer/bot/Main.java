package net.TokyoSlayer.bot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.GuildlistCommand;
import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import net.TokyoSlayer.bot.Command.*;
import net.TokyoSlayer.bot.Utils.Files;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    public static Files files;

    public static void main(String[] args) throws LoginException, IllegalArgumentException {
        files = new Files();
        files.load("config");
        // the first is the bot token
        String token = files.translate("bot.token");

        // the second is the bot's owner's id
        String ownerId = files.translate("bot.owner");

        // define an eventwaiter, dont forget to add this to the JDABuilder!
        EventWaiter waiter = new EventWaiter();

        // define a command client
        CommandClientBuilder client = new CommandClientBuilder();

        // The default is "Type !!help" (or whatver prefix you set)
        client.useDefaultGame();

        // sets the owner of the bot
        client.setOwnerId(ownerId);

        // sets the bot prefix
        client.setPrefix(files.translate("bot.cmd"));

        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        // adds commands
        client.addCommands(

                new Maj(),

                new Ticket(),

                new GuildlistCommand(waiter),

                // command to shut off the bot
                new ShutdownCommand());

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.playing("loading..."));
        builder.addEventListeners(waiter,client.build());
        builder.addEventListeners(new DiscordListeners());
        builder.build();

        System.out.println("Bot Load!!");
    }

}