package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;
import org.bukkit.Location;

public class Game extends BaseGame {
	
	int radius = 50;
	
	public Location getRandomBlockAtHeight( int yOffest ) {
		
		Location spawnLocation = world.getSpawnLocation();
		int x = spawnLocation.getBlockX() + Random.generateInteger(- radius, radius);
		int z = spawnLocation.getBlockZ() + Random.generateInteger(- radius, radius);
		int y = world.getHighestBlockAt(x, z).getY() + yOffest;
		return new Location(world, x, y, z);
	
	}
	
	
}