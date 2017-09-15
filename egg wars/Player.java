package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {

	public boolean hasEgg;

	public void onJoin() {
		
		onStart();
	
	}
	
	public void onStart() {
		
		setAllowFlight(true);					// 飛べるか
		clearInventory();						// アイテムのクリア
		setGameMode(GameMode.SURVIVAL);			// サバイバルモード
		addItemToInventory(Material.BEACON);	// アイテムをインベントリに追加
		hasEgg = false;
	}
	
	public void onPlaceBlock( Block block ) {
		
		if (block.getType() == Material.BEACON) {
			if (!hasEgg) {
				placeSpawn();
			}
		}
	}
	
	public void placeSpawn() {

		hasEgg = true;
		getGame().broadcastMessage(ChatColor.AQUA + (name + " placed their egg."));
	
	}
		
}