package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		onStart();
	
	}
	
	public void onStart() {
		
		setAllowFlight(true);					// 飛べるか
		clearInventory();						// アイテムのクリア
		setGameMode(GameMode.SURVIVAL);			// サバイバルモード
		addItemToInventory(Material.BEACON);	// アイテムをインベントリに追加
		
	}
	
	public void onPlaceBlock( Block block ) {
		
		if( block.getType() == Material.BEACON ) {
			
			sendMessage("Egg placed!");
		}
	
	}
		
}