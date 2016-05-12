package net.rayfall.eyesniper2.skRayFall.Voting;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RayFallOfflineVoteEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private String site;
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public RayFallOfflineVoteEvent(Player player, String site) {
	    this.player = player;
	    this.site = site;
	}
	 
	public Player getPlayer() {
	    return this.player;
	}
	public String getSite(){
		return this.site;
	}

}

