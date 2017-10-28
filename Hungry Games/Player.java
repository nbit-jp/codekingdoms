package space.codekingdoms.nbituser.myswiftgames;

import org.bukkit.Sound;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		if (getGame().gamePhase > 1) {
			
			setGameMode(GameMode.SPECTATOR);
			sendMessage("There is already a game in progress! You've been made a spectator!");
			
		} else {

			startGame();

		}

	}
	
	public void onDeath() {
		
		setGameMode(GameMode.SPECTATOR);
		getGame().checkGameOver();
		getGame().playSound(Sound.ENTITY_LIGHTNING_THUNDER);
		
	}

	public void startGame() {
		
		setGameMode(GameMode.ADVENTURE);
		clearInventory();
		setFoodLevel(20);
		setHealth(20);		

	}	
		
}