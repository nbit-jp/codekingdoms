package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;

public class Game extends BaseGame {
	
	int radius = 50;
	Location chestPosition;
	
	public void onCodeUpdate() {
		
		startTimer(300);
		createWorldBorder(radius*2);
		startRound();
	
	}
	
	public void startRound() {
		
		chestPosition = getRandomBlockAtHeight(-3);
		setBlockTypeAtLocation(Material.EMERALD_BLOCK, chestPosition);
		setInterval(
			
			() -> {
				
				broadcastMessage(chestPosition.toString());
                world.spawnParticle(Particle.ENCHANTMENT_TABLE, 
                                    world.getHighestBlockAt(chestPosition.getBlockX(), chestPosition.getBlockZ()).getLocation(), 
                                    50);
				
			}
		, 0, 1);
	
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