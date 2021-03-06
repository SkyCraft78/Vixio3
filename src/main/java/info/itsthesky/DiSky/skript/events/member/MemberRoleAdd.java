package info.itsthesky.disky.skript.events.member;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import info.itsthesky.disky.tools.events.DiSkyEvent;
import info.itsthesky.disky.tools.events.LogEvent;
import info.itsthesky.disky.tools.events.SimpleDiSkyEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;

public class MemberRoleAdd extends DiSkyEvent<GuildMemberRoleAddEvent> {

    static {
        DiSkyEvent.register("Inner Event Name", MemberRoleAdd.class, EvtMemberRoleAdd.class,
                "member role add[ed]")
                .setName("Docs Event Name")
                .setDesc("Event description")
                .setExample("Event Example");


       EventValues.registerEventValue(EvtMemberRoleAdd.class, Role[].class, new Getter<Role[], EvtMemberRoleAdd>() {
            @Override
            public Role[] get(EvtMemberRoleAdd event) {
                return event.getJDAEvent().getRoles().toArray(new Role[0]);
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleAdd.class, Member.class, new Getter<Member, EvtMemberRoleAdd>() {
            @Override
            public Member get(EvtMemberRoleAdd event) {
                return event.getJDAEvent().getMember();
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleAdd.class, User.class, new Getter<User, EvtMemberRoleAdd>() {
            @Override
            public User get(EvtMemberRoleAdd event) {
                return event.getJDAEvent().getUser();
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleAdd.class, Guild.class, new Getter<Guild, EvtMemberRoleAdd>() {
            @Override
            public Guild get(EvtMemberRoleAdd event) {
                return event.getJDAEvent().getGuild();
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleAdd.class, JDA.class, new Getter<JDA, EvtMemberRoleAdd>() {
            @Override
            public JDA get(EvtMemberRoleAdd event) {
                return event.getJDAEvent().getJDA();
            }
        }, 0);

    }

    public static class EvtMemberRoleAdd extends SimpleDiSkyEvent<GuildMemberRoleAddEvent> implements LogEvent {
        public EvtMemberRoleAdd(MemberRoleAdd event) { }

        @Override
        public User getActionAuthor() {
            return getJDAEvent().getGuild().retrieveAuditLogs().complete().get(0).getUser();
        }
    }

}