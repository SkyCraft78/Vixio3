package info.itsthesky.disky.skript.events.member;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import info.itsthesky.disky.tools.events.DiSkyEvent;
import info.itsthesky.disky.tools.events.LogEvent;
import info.itsthesky.disky.tools.events.SimpleDiSkyEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;

public class MemberRoleRemove extends DiSkyEvent<GuildMemberRoleRemoveEvent> {

    static {
        DiSkyEvent.register("Inner Event Name", MemberRoleRemove.class, EvtMemberRoleRemove.class,
                "member role remove[d]")
                .setName("Docs Event Name")
                .setDesc("Event description")
                .setExample("Event Example");


       EventValues.registerEventValue(EvtMemberRoleRemove.class, Role[].class, new Getter<Role[], EvtMemberRoleRemove>() {
            @Override
            public Role[] get(EvtMemberRoleRemove event) {
                return event.getJDAEvent().getRoles().toArray(new Role[0]);
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleRemove.class, Member.class, new Getter<Member, EvtMemberRoleRemove>() {
            @Override
            public Member get(EvtMemberRoleRemove event) {
                return event.getJDAEvent().getMember();
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleRemove.class, User.class, new Getter<User, EvtMemberRoleRemove>() {
            @Override
            public User get(EvtMemberRoleRemove event) {
                return event.getJDAEvent().getUser();
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleRemove.class, Guild.class, new Getter<Guild, EvtMemberRoleRemove>() {
            @Override
            public Guild get(EvtMemberRoleRemove event) {
                return event.getJDAEvent().getGuild();
            }
        }, 0);

       EventValues.registerEventValue(EvtMemberRoleRemove.class, JDA.class, new Getter<JDA, EvtMemberRoleRemove>() {
            @Override
            public JDA get(EvtMemberRoleRemove event) {
                return event.getJDAEvent().getJDA();
            }
        }, 0);

    }

    public static class EvtMemberRoleRemove extends SimpleDiSkyEvent<GuildMemberRoleRemoveEvent>implements LogEvent {
        public EvtMemberRoleRemove(MemberRoleRemove event) { }

        @Override
        public User getActionAuthor() {
            return getJDAEvent().getGuild().retrieveAuditLogs().complete().get(0).getUser();
        }
    }

}