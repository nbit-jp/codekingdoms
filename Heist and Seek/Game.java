package space.codekingdoms.nbituser.mylazyheist;

import com.codekingdoms.nozzle.base.BaseGame;
import org.bukkit.Location;
import org.bukkit.Difficulty;
import com.codekingdoms.nozzle.utils.Random;

public class Game extends BaseGame {
	
	public Location jail;
	
	public Location outside;
	
	public void onCodeUpdate() {
		
		world.setDifficulty(Difficulty.PEACEFUL);
		world.setPVP(false);
		jail = new Location(world, 856.83, 5, 114.38);
		outside = new Location(world, 852.39, 4, 110.33);
		startHiding();
	
	}
	
	public void startHiding() {
		
		if (getPlayerList().length == 0) {
			
			broadcastMessage("No players on your server, game stopping.");
			return;
			
		}
		
		int chosenPlayerNumber = Random.generateInteger(1, getPlayerList().length);
		chosenPlayerNumber = chosenPlayerNumber - 1;
		Player chosenPlayer = getPlayerList()[chosenPlayerNumber];

		for (Player player:getPlayerList()) {		
			if (player.equals(chosenPlayer)) {
				
				player.isHider = true;
				player.startHiding();
				
			} else {
				
				player.isHider = false;
				player.freeze();
				
			}
		}
	}
		
}