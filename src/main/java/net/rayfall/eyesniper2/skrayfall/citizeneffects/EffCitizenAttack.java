package net.rayfall.eyesniper2.skrayfall.citizeneffects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffCitizenAttack extends Effect {

  private Expression<Number> id;
  private Expression<Entity> toBeAttacked;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<Number>) exp[0];
    toBeAttacked = (Expression<Entity>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event evt, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC attacker = registry.getById(id.getSingle(evt).intValue());
    if (attacker != null) {
      attacker.getNavigator().setTarget(toBeAttacked.getSingle(evt), true);
    }
  }

}
