package space.codekingdoms.nbituser.mylazyheist;

import com.codekingdoms.nozzle.base.BaseGame;
import org.bukkit.Location;
import org.bukkit.Difficulty;

public class Game extends BaseGame {
	
	public Location jail;
	
	public Location outside;
	
	public void onCodeUpdate() {
		
		world.setDifficulty(Difficulty.PEACEFUL);
		world.setPVP(false);
		jail = new Location(world, 856.83, 5, 114.38);
		outside = new Location(world, 852.39, 4, 110.33);
	
	}
	
	
}