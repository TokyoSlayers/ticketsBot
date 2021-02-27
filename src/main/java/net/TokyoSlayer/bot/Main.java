/*
 * Copyright 2017 John Grosh (jagrosh).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.TokyoSlayer.bot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import net.TokyoSlayer.bot.commands.Commandmcbot;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException
    {
        String token = "ODE1MjA2MzUxMDQ2MzExOTY2.YDpCEw.6i-KVZ_XEtZRrCN0Ti_lVZBhfog";

        String ownerId = "189683818603610112";

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();

        client.setOwnerId(ownerId);

        client.setPrefix("!!");

        client.addCommands(

                new Commandmcbot(),

                new ShutdownCommand());

        //noinspection deprecation
        new JDABuilder(AccountType.BOT)
                .setToken(token)

                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("loading..."))

                .addEventListeners(waiter, client.build())

                .build();
    }
}
