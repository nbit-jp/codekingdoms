package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public boolean fightStarted;

	public void onCodeUpdate() {
		
		setKeepInventory(true);		// 死んだ時にアイテムをキープするか
		canDropItems = false;		// アイテムをドロップするか
		fightStarted = false;
		for (Player player : getPlayerList()) {
			
			player.onStart();
			
		}
	
	}

	public void placeEgg() {
		
		for (Player player : getPlayerList()) {
			if(!player.hasEgg) {
				return;
			}
		}
		startFight();
	}
	
	public void startFight() {
		
		broadcastTitle("All eggs placed", "Time to fight!");
		fightStarted = true;
		for (Player player : getPlayerList()) {
			player.onStartFight();
		}		

	}
	
	public void onPlayerLost() {
		
		int survivors = 0;
		String winnerName = "NOBODY";
		for (Player player:getPlayerList()) {
			
			if (player.getGameMode() == GameMode.SURVIVAL) {
				
				survivors = survivors + 1;
				winnerName = player.name;
				
			}

		}
		if ( survivors > 1 ) {
			return;
		}
		broadcastTitle(winnerName + " wins!", "");
		resetGame();

	}	
}