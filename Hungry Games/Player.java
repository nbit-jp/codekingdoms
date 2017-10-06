package space.codekingdoms.nbituser.myswiftgames;

import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		if (getGame().gamePhase > 1) {
			
			setGameMode(GameMode.SPECTATOR);
			sendMessage("There is already a game in progress! You've been made a spectator!");
			
		}

	}
	
	public void onDeath() {
		
		setGameMode(GameMode.SPECTATOR);
		getGame().checkGameOver();
		getGame().playSound(Sound.ENTITY_LIGHTNING_THUNDER);
		
	}
		
}