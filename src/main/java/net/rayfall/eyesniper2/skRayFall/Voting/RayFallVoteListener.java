package net.rayfall.eyesniper2.skRayFall.Voting;

import java.util.Collection;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.njol.skript.Skript;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.vexsoftware.votifier.model.VotifierEvent;

public class RayFallVoteListener implements Listener{
	
	private static Multimap<String, OfflineVote> voteMap = HashMultimap.create();;
	
	public RayFallVoteListener(skRayFall core) {
		core.getServer().getPluginManager().registerEvents(this, core);
	}
	
	@EventHandler
	public void onVote(VotifierEvent VotifierEvent){
		String h = VotifierEvent.getVote().getUsername();
    	if (Bukkit.getPlayer(h) != null && Bukkit.getPlayer(h).isOnline()){
    		RayFallVoteEvent event = new RayFallVoteEvent(Bukkit.getPlayer(h), VotifierEvent.getVote().getServiceName());
			Bukkit.getPluginManager().callEvent(event); 
    	}
    	else{
    		//Store player and vote in the hashMap
    		voteMap.put(h, new OfflineVote(VotifierEvent.getVote().getServiceName(),VotifierEvent.getVote().getTimeStamp()));
    		if (Bukkit.getPlayer(h) != null && !Bukkit.getPlayer(h).isOnline()){
    			RayFallOfflineVoteEvent offlineEvent = new RayFallOfflineVoteEvent(Bukkit.getPlayer(h), VotifierEvent.getVote().getServiceName());
    			Bukkit.getPluginManager().callEvent(offlineEvent);
    		}
    		Skript.error("Player is not online, saving vote for the next time they are online");
    		
    	}
	}
	
	@EventHandler
	public void TriggerVoteOnJoin(PlayerJoinEvent evt){
		Player p = evt.getPlayer();
		if (voteMap.containsKey(p.getName())){
			Collection<OfflineVote> s = voteMap.get(p.getName());
			for(OfflineVote vote : s){
				RayFallVoteEvent event = new RayFallVoteEvent(p, vote.getSite());
				Bukkit.getPluginManager().callEvent(event);
			}
			voteMap.removeAll(p.getName());
		}
	}
	

}
