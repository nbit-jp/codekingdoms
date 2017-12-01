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
		startRound();
	
	}
	
}