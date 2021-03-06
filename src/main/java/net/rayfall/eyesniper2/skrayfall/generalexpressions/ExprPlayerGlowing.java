package net.rayfall.eyesniper2.skrayfall.generalexpressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class ExprPlayerGlowing extends SimpleExpression<Boolean> {

  // %player% glowing

  Expression<Player> player;

  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    player = (Expression<Player>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  @Nullable
  protected Boolean[] get(Event evt) {
    if (player != null) {
      return new Boolean[] {player.getSingle(evt).isGlowing()};
    }
    return null;
  }

}
