package info.itsthesky.disky.skript.expressions.fromid;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import info.itsthesky.disky.managers.BotManager;
import info.itsthesky.disky.tools.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.Event;

@Name("Text Channel from ID")
@Description("Return a text channel from its id.")
@Examples("set {_cha} to text channel with id \"731885527762075648\"")
@Since("1.11")
public class ExprFIDText extends SimpleExpression<TextChannel> {

	static {
		Skript.registerExpression(ExprFIDText.class, TextChannel.class, ExpressionType.SIMPLE,
				"["+ Utils.getPrefixName() +"] text channel with [the] id %string%"
		);
	}

	private Expression<String> exprID;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		exprID = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	protected TextChannel[] get(Event e) {
		String id = exprID.getSingle(e);
		if (id == null) return new TextChannel[0];
		if (!Utils.isNumeric(id)) return new TextChannel[0];
		return new TextChannel[] {BotManager.search(bot -> bot.getTextChannelById(id))};
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends TextChannel> getReturnType() {
		return TextChannel.class;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "text channel with id " + exprID.toString(e, debug);
	}

}