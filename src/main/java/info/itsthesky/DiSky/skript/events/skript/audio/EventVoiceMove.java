package info.itsthesky.DiSky.skript.events.skript.audio;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import info.itsthesky.DiSky.tools.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Voice Channel Move")
@Description("Fired when any member is moved to another voice channel.")
@Examples("on member channel move:")
@Since("1.8.1")
public class EventVoiceMove extends Event {

    static {
        Skript.registerEvent("Voice Channel Move", SimpleEvent.class, EventVoiceMove.class, "[discord] [member] [voice] channel move");

        EventValues.registerEventValue(EventVoiceMove.class, VoiceChannel.class, new Getter<VoiceChannel, EventVoiceMove>() {
            @Nullable
            @Override
            public VoiceChannel get(final @NotNull EventVoiceMove event) {
                return event.getE().getChannelJoined();
            }
        }, 0);

        EventValues.registerEventValue(EventVoiceMove.class, GuildChannel.class, new Getter<GuildChannel, EventVoiceMove>() {
            @Nullable
            @Override
            public GuildChannel get(final @NotNull EventVoiceMove event) {
                return event.getE().getChannelJoined();
            }
        }, 0);

        EventValues.registerEventValue(EventVoiceMove.class, Guild.class, new Getter<Guild, EventVoiceMove>() {
            @Nullable
            @Override
            public Guild get(final @NotNull EventVoiceMove event) {
                return event.getE().getGuild();
            }
        }, 0);

        EventValues.registerEventValue(EventVoiceMove.class, JDA.class, new Getter<JDA, EventVoiceMove>() {
            @Nullable
            @Override
            public JDA get(final @NotNull EventVoiceMove event) {
                return event.getE().getJDA();
            }
        }, 0);

        EventValues.registerEventValue(EventVoiceMove.class, Member.class, new Getter<Member, EventVoiceMove>() {
            @Nullable
            @Override
            public Member get(final @NotNull EventVoiceMove event) {
                return event.getE().getEntity();
            }
        }, 0);

        EventValues.registerEventValue(EventVoiceMove.class, User.class, new Getter<User, EventVoiceMove>() {
            @Nullable
            @Override
            public User get(final @NotNull EventVoiceMove event) {
                return event.getE().getEntity().getUser();
            }
        }, 0);

    }

    private final GuildVoiceMoveEvent e;

    public EventVoiceMove(
            final GuildVoiceMoveEvent e) {
        super(Utils.areEventAsync());
        this.e = e;
    }

    public GuildVoiceMoveEvent getE() {
        return e;
    }

    private static final HandlerList HANDLERS = new HandlerList();
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}