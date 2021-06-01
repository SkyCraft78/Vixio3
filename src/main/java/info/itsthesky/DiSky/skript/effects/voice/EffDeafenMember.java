package info.itsthesky.disky.skript.effects.voice;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import info.itsthesky.disky.tools.AsyncEffect;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import info.itsthesky.disky.tools.DiSkyErrorHandler;
import info.itsthesky.disky.tools.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.event.Event;

@Name("Deafen / UnDeafen Member")
@Description("Deafen or UnDeafen a member in a guild.")
@Examples("deafen event-member")
@Since("1.9")
public class EffDeafenMember extends AsyncEffect {

    static {
        Skript.registerEffect(EffDeafenMember.class,
                "["+ Utils.getPrefixName() +"] deafen [discord] %member% [in guild] [(with|via|using) %-bot%]",
                "["+ Utils.getPrefixName() +"] un[( |-)]deafen [discord] %member% [in guild] [(with|via|using) %-bot%]"
        );
    }

    private Expression<Member> exprMember;
    private Expression<JDA> exprBot;
    private int pattern;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        exprMember = (Expression<Member>) exprs[0];
        if (exprs.length != 1) exprBot = (Expression<JDA>) exprs[1];
        pattern = matchedPattern;
        return true;
    }

    @Override
    protected void execute(Event e) {
        DiSkyErrorHandler.executeHandleCode(e, Event -> {
            Member member = exprMember.getSingle(e);
            if (exprBot != null && !Utils.areJDASimilar(member.getJDA(), exprBot.getSingle(e))) return;
            if (member == null) return;
            if (pattern == 0) {
                member.deafen(true).queue(null, DiSkyErrorHandler::logException);
            } else {
                member.deafen(false).queue(null, DiSkyErrorHandler::logException);
            }
        });
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "deafen member " + exprMember.toString(e, debug) + exprBot == null ? "" : " with bot " + exprBot.toString(e, debug);
    }

}
