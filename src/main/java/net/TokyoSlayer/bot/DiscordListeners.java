package net.TokyoSlayer.bot;

import net.TokyoSlayer.bot.Utils.Files;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class DiscordListeners extends ListenerAdapter {

    private static final Files files = Main.files;

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if(!event.getUser().isBot()){
            if(event.getChannel().getName().equals(checkName(event.getUser().getName()).toLowerCase())) {
                if(event.getReactionEmote().getName().equals(files.translate("bot.logo.close"))){
                    for(Role role : event.getGuild().getRoles()) {
                        if(role.getId().equals("781518028365103154")){
                            System.out.println(event.getChannel().getName()+" closed!");
                            event.getChannel().delete().reason("null").queue();
                            event.getChannel().retrieveInvites().queue();
                        }
                    }
                }else if(event.getReactionEmote().getName().equals(files.translate("bot.logo.bot"))) {
                    event.getChannel().sendMessage(files.translate("bot.text.game.essage.bot")).queue();
                    for(Role role : event.getGuild().getRoles()) {
                        if(role.getId().equals("745257340713828443")) {
                            for (PermissionOverride permissionOverride :event.getChannel().getRolePermissionOverrides()){
                                if(permissionOverride.getRole() == role){
                                    event.getReaction().removeReaction(event.getUser()).queue();
                                    return;
                                }
                            }
                            System.out.println(event.getUser().getName()+" add "+role.getName());
                            event.getChannel().createPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL).queue();
                        }
                    }
                }else if(event.getReactionEmote().getName().equals(files.translate("bot.logo.player"))) {
                    event.getChannel().sendMessage(files.translate("bot.text.game.essage.player")).queue();
                    for(Role role : event.getGuild().getRoles()) {
                        if(role.getId().equals("781295847937277985") || role.getId().equals("787759959785603112")) {
                            for (PermissionOverride permissionOverride :event.getChannel().getRolePermissionOverrides()){
                                if(permissionOverride.getRole() == role){
                                    event.getReaction().removeReaction(event.getUser()).queue();
                                    return;
                                }
                            }
                            System.out.println(event.getUser().getName()+" add "+role.getName());
                            event.getChannel().createPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL).queue();
                        }
                    }
                }else if(event.getReactionEmote().getName().equals(files.translate("bot.logo.plugin"))) {
                    event.getChannel().sendMessage(files.translate("bot.text.game.essage.plugin")).queue();
                    for(Role role : event.getGuild().getRoles()) {
                        if(role.getId().equals("745257340713828443")) {
                            for (PermissionOverride permissionOverride :event.getChannel().getRolePermissionOverrides()){
                                if(permissionOverride.getRole() == role){
                                    event.getReaction().removeReaction(event.getUser()).queue();
                                    return;
                                }
                            }
                            System.out.println(event.getUser().getName()+" add "+role.getName());
                            event.getChannel().createPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL).queue();
                        }
                    }
                } else if(event.getReactionEmote().getName().equals(files.translate("bot.logo.map"))) {
                    event.getChannel().sendMessage(files.translate("bot.text.game.essage.map")).queue();
                    for(Role role : event.getGuild().getRoles()) {
                        if(role.getId().equals("754349272232755271")) {
                            for (PermissionOverride permissionOverride :event.getChannel().getRolePermissionOverrides()){
                                if(permissionOverride.getRole() == role){
                                    event.getReaction().removeReaction(event.getUser()).queue();
                                    return;
                                }
                            }
                            System.out.println(event.getUser().getName()+" add "+role.getName());
                            event.getChannel().createPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL).queue();
                        }
                    }
                } else if(event.getReactionEmote().getName().equals(files.translate("bot.logo.shop"))) {
                    event.getChannel().sendMessage(files.translate("bot.text.game.essage.shop")).queue();
                    for(Role role : event.getGuild().getRoles()) {
                        if(role.getId().equals("769501770044538880")) {
                            for (PermissionOverride permissionOverride :event.getChannel().getRolePermissionOverrides()){
                                if(permissionOverride.getRole() == role){
                                    event.getReaction().removeReaction(event.getUser()).queue();
                                    return;
                                }
                            }
                            System.out.println(event.getUser().getName()+" add "+role.getName());
                            event.getChannel().createPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL).queue();
                        }
                    }
                }
                event.getReaction().removeReaction(event.getUser()).queue();

            }else if(event.getChannel().getId().equals("822911433586245672")) {
                String reaction = event.getReactionEmote().getName();

                List<String> s = new ArrayList<>();

                for(GuildChannel channel : event.getGuild().getChannels()) {
                    s.add(channel.getName());
                }
                if (s.contains(event.getUser().getName().toLowerCase())) {
                    event.getUser().openPrivateChannel().queue((channel) -> channel.sendMessage(files.translate("bot.text.game.essage.already")).queue());
                    System.out.println(event.getUser().getName()+" already channel");
                }else{
                    createTextChannel(event.getMember(), event.getGuild(),reaction);
                    event.getUser().openPrivateChannel().queue((channel) -> channel.sendMessage(files.translate("bot.text.game.essage.create")).queue());
                    System.out.println(event.getUser().getName()+" create new channel");
                }
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }
    }

    private static String site(String name){
        return files.translate("bot.text.web.0")
                +files.translate("bot.text.web.1")
                +files.translate("bot.text.web.2")
                +files.translate("bot.text.web.1")
                +files.translate("bot.text.web.2")
                +files.translate("bot.text.web.3")+" <:"+files.translate("bot.logo.shop")+">"
                +files.translate("bot.text.web.4")+" <:"+files.translate("bot.logo.player")+">"
                +files.translate("bot.text.web.5")
                +files.translate("bot.text.web.6")+" "+files.translate("bot.logo.close")+
                "<@" + name + ">\n";
    }
    private static String minecraft(String name){
        return files.translate("bot.text.game.0")
                +files.translate("bot.text.game.1")
                +files.translate("bot.text.game.2")
                +files.translate("bot.text.game.3")
                +files.translate("bot.text.game.4")
                +files.translate("bot.text.game.5")+" <:"+files.translate("bot.logo.plugin")+">"
                +files.translate("bot.text.game.6")+" <:"+files.translate("bot.logo.player")+">"
                +files.translate("bot.text.game.7")+" <:"+files.translate("bot.logo.map")+">"
                +files.translate("bot.text.game.8")
                +files.translate("bot.text.game.9")+" "+files.translate("bot.logo.close")+
                "<@" + name + ">\n";
    }
    private static String discord(String name){
        return files.translate("bot.text.discord.0")
                +files.translate("bot.text.discord.1")
                +files.translate("bot.text.discord.2")
                +files.translate("bot.text.discord.3")
                +files.translate("bot.text.discord.4")
                +files.translate("bot.text.discord.5")+" <:"+files.translate("bot.logo.bot")+">"
                +files.translate("bot.text.discord.6")+" <:"+files.translate("bot.logo.player")+">"
                +files.translate("bot.text.discord.7")
                +files.translate("bot.text.discord.8")+" "+files.translate("bot.logo.close")+
                "<@" + name + ">\n";
    }
    private static void textInChannel(Guild guild,String reaction,Member member) {
        String name = member.getUser().getName();
        TimerTask task = new TimerTask() {
            public void run() {
                List<TextChannel> textChannels = guild.getTextChannels().stream().filter(textChannel -> textChannel.getName().equals(name.toLowerCase())).collect(Collectors.toList());
                if (reaction.equals(files.translate("bot.logo.discord"))) {
                    textChannels.forEach(textChannel -> textChannel.sendMessage(discord(member.getUser().getId())).queue(message -> {
                        message.addReaction(files.translate("bot.logo.bot")).queue();
                        message.addReaction(files.translate("bot.logo.player")).queue();
                        message.addReaction(files.translate("bot.logo.close")).queue();
                    }));
                } else if (reaction.equals(files.translate("bot.logo.minecraft"))) {
                    textChannels.forEach(textChannel -> textChannel.sendMessage(minecraft(member.getUser().getId())).queue(message -> {
                        message.addReaction(files.translate("bot.logo.plugin")).queue();
                        message.addReaction(files.translate("bot.logo.player")).queue();
                        message.addReaction(files.translate("bot.logo.map")).queue();
                        message.addReaction(files.translate("bot.logo.close")).queue();
                    }));
                }else if (reaction.equals(files.translate("bot.logo.web"))) {
                    textChannels.forEach(textChannel -> textChannel.sendMessage(site(member.getUser().getId())).queue(message -> {
                        message.addReaction(files.translate("bot.logo.player")).queue();
                        message.addReaction(files.translate("bot.logo.shop")).queue();
                        message.addReaction(files.translate("bot.logo.close")).queue();
                    }));
                } else {
                    textChannels.forEach(textChannel -> textChannel.sendMessage("tes null <@189683818603610112>").queue());
                }
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        timer.schedule(task, delay);
    }

    public static void createTextChannel(Member member, Guild guild,String reaction) {
        Map<String,Category> s = new HashMap<>();
        Category cat = null;
        for(Category category : guild.getCategories()) { s.put(category.getName(),category); }
        if (!s.containsKey("Minecraft")){
            cat = guild.createCategory("Minecraft").complete();
        }else{
            if(reaction.equals("minecraft")){
                cat = s.get("Minecraft");
            }
        }

        if (!s.containsKey("Discord")){
            cat = guild.createCategory("Discord").complete();
        }else{
            if(reaction.equals("discord")){
                cat = s.get("Discord");
            }
        }

        if(!s.containsKey("Site")){
            cat = guild.createCategory("Site").complete();
        }else {
            if(reaction.equals(files.translate("bot.logo.web"))){
                cat = s.get("Site");
            }
        }

        cat.createTextChannel(checkName(member.getUser().getName()))

                .addPermissionOverride(guild.getRoleById("810520715606491136"), null, Collections.singleton(Permission.VIEW_CHANNEL))
                .addRolePermissionOverride(guild.getRoleById("810520715606491136").getIdLong(),null,Collections.singleton(Permission.VIEW_CHANNEL))

                .addPermissionOverride(guild.getRoleById("781518028365103154"), null, Collections.singleton(Permission.VIEW_CHANNEL))
                .addRolePermissionOverride(guild.getRoleById("781518028365103154").getIdLong(),null,Collections.singleton(Permission.VIEW_CHANNEL))

                .addPermissionOverride(member, Collections.singleton(Permission.VIEW_CHANNEL), null)
                .addMemberPermissionOverride(member.getIdLong(), Collections.singleton(Permission.VIEW_CHANNEL),null)
                .queue();

        textInChannel(guild,reaction,member);
    }

    private static String checkName(String name){
        StringBuilder sb = new StringBuilder();
        for(char i : name.toCharArray()){
            if(Character.isAlphabetic(i) || Character.isDigit(i)){
                sb.append(i);
            }
        }
        return sb.toString();

    }


}