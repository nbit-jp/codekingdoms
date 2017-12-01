package space.codekingdoms.nbituser.mybrainybunnies;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;
import org.bukkit.ChatColor;

public class Game extends BaseGame {
	
	public Faction humans;
	public Faction bunnies;
	
	public Player getRandomPlayer() {
		
		Player[] array = getPlayerList();
		if (array.length == 0) {
			
			return null;
			
		}
		
		return array[Random.generateInteger(0, array.length - 1)];
	
	}
	
	public void startRound() {
		
		humans = new Faction();
		humans.setColour(ChatColor.GREEN);
		bunnies = new Faction();
		bunnies.setColour(ChatColor.RED);
		for (Player player:getPlayerList()) {
			
			player.setHuman();
			
		}

		getRandomPlayer().setBunny();
		
	}
	
	public void onCodeUpdate() {
		
		disableMobSpawning();
		createWorldBorder(50);
		world.setTime(13000);
		startTimer(30);
		broadcastMessage("Game starts in 30 seconds!");
		
	}
	
	public void checkGameOver(String SurvivorName) {
		
		for(Player player:getPlayerList()) {
			
			if(player.isHuman) {
				return;
			}

		}
		
		broadcastMessage("GameOver! the last survivor was " + SurvivorName);
		startTimer(30);
		broadcastMessage("Next round starts in 30 seconds!");
		
	}
	
	public void onTimerExpire() {
		
		startRound();
		
	}
}