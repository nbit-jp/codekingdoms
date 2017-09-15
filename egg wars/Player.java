package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		onStart();
	
	}
	
	public void onStart() {
		
		setAllowFlight(true);
		clearInventory();
		setGameMode(GameMode.SURVIVAL);
		addItemToInventory(Material.BEACON);
	
	}
	
	
}