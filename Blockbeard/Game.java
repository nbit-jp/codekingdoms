package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;

public class Game extends BaseGame {
	
	int radius = 50;
	
	public void onCodeUpdate() {
		
		startTimer(300);
		createWorldBorder(radius*2);
		startRound();
	
	}
	
	public void startRound() {
		
		Location chestPosition = getRandomBlockAtHeight(-3);
		broadcastMessage(chestPosition.toString());
		setBlockTypeAtLocation(Material.EMERALD_BLOCK, chestPosition);
	
	}
	
	public void endGame() {
		
		broadcastMessage("The game is over!");
		resetGame();
	
	}
	
	public void onTimerExpire() {
		
		endGame();
	
	}
	
	public Location getRandomBlockAtHeight( int yOffest ) {
		
		Location spawnLocation = world.getSpawnLocation();
		int x = spawnLocation.getBlockX() + Random.generateInteger(- radius, radius);
		int z = spawnLocation.getBlockZ() + Random.generateInteger(- radius, radius);
		int y = world.getHighestBlockAt(x, z).getY() + yOffest;
		return new Location(world, x, y, z);
	
	}
	
	
}