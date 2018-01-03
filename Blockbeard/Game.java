package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;

public class Game extends BaseGame {
	
	public int radius = 50;
	public Location chestPosition;
	
	public void onCodeUpdate() {
		
		startTimer(300);
		createWorldBorder(radius * 2);
		startRound();
	
	}
	
	public void startRound() {
		
		chestPosition = getRandomBlockAtHeight(-3);
		setBlockTypeAtLocation(Material.EMERALD_BLOCK, chestPosition);
		setInterval(
			
			() -> {
				
				world.spawnParticle(Particle.ENCHANTMENT_TABLE, world.getHighestBlockAt(chestPosition).getLocation(), 50);
				
			}
		, 0, 1);
	
	}
	
	public void endGame() {
		
		cancelChest();
		resetGame();
	
	}
	
	public void onTimerExpire() {
		
		broadcastMessage("Time is out!");
		endGame();
	
	}
	
	public Location getRandomBlockAtHeight( int yOffest ) {
		
		Location spawnLocation = world.getSpawnLocation();
		int x = spawnLocation.getBlockX() + Random.generateInteger(- radius, radius);
		int z = spawnLocation.getBlockZ() + Random.generateInteger(- radius, radius);
		int y = world.getHighestBlockAt(x, z).getY() + yOffest;
		return new Location(world, x, y, z);
	
	}
	
	public void cancelChest() {
		
		stopAllTimeouts();
		setBlockTypeAtLocation(Material.AIR, chestPosition);
		
	}
	
}