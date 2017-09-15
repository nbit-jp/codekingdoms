package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public void onCodeUpdate() {
		
		setKeepInventory(true);		// 死んだ時にアイテムをキープするか
		canDropItems = false;		// アイテムをドロップするか
		for (Player player:getPlayerList()) {
			
			player.onStart();
			
		}
		
	
	}
	
	
}