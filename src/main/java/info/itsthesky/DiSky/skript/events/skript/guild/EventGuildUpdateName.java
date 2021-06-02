package info.itsthesky.disky.skript.events.skript.guild;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import info.itsthesky.disky.skript.events.skript.role.EventRoleDelete;
import info.itsthesky.disky.tools.UpdatedValue;
import info.itsthesky.disky.tools.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Name("Guild Name Update")
@Description({"Run when the name of the guild is updated.",
        "Possible updated values:",
        "new guild name",
        "old guild name",
})
@Examples("on guild name change:")
@Since("1.10")
public class EventGuildUpdateName extends Event {

    private static final UpdatedValue<String> updatedName;

    static {
        Skript.registerEvent("Guild Name Update", SimpleEvent.class, EventGuildUpdateName.class, "[discord] guild name (change|update)")
                .description(new String[]{"Run when the name of the guild is updated.",
                        "Possible updated values:",
                        "new guild name",
                        "old guild name",
                })
                .examples("on guild name change:")
                .since("1.10");

        updatedName = new UpdatedValue<>(String.class, EventGuildUpdateName.class, "[discord] guild [nick]name", true).register();

        EventValues.registerEventValue(EventGuildUpdateName.class, Guild.class, new Getter<Guild, EventGuildUpdateName>() {
            @Nullable
            @Override
            public Guild get(final @NotNull EventGuildUpdateName event) {
                return event.getEvent().getGuild();
            }
        }, 0);

        EventValues.registerEventValue(EventGuildUpdateName.class, JDA.class, new Getter<JDA, EventGuildUpdateName>() {
            @Nullable
            @Override
            public JDA get(final @NotNull EventGuildUpdateName event) {
                return event.getEvent().getJDA();
            }
        }, 0);

        EventValues.registerEventValue(EventGuildUpdateName.class, User.class, new Getter<User, EventGuildUpdateName>() {
            @Nullable
            @Override
            public User get(final @NotNull EventGuildUpdateName event) {
                return event.author;
            }
        }, 0);

        EventValues.registerEventValue(EventGuildUpdateName.class, Member.class, new Getter<Member, EventGuildUpdateName>() {
            @Nullable
            @Override
            public Member get(final @NotNull EventGuildUpdateName event) {
                return event.authorM;
            }
        }, 0);

    }

    private static final HandlerList HANDLERS = new HandlerList();

    private final GuildUpdateNameEvent e;
    private final User author;
    private final Member authorM;

    public EventGuildUpdateName(
            final GuildUpdateNameEvent e
            ) {
        super(Utils.areEventAsync());
        this.e = e;
        updatedName.setNewObject(e.getNewName());
        updatedName.setOldObject(e.getOldName());
        author = e.getGuild().retrieveAuditLogs().complete().get(0).getUser();
        authorM = e.getGuild().getMember(author);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public GuildUpdateNameEvent getEvent() {
        return e;
    }
}