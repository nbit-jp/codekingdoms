package space.codekingdoms.nbituser.myswiftgames;

import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		if  (getGame().gamePhase > 1) {
			
			kickPlayer("There is already a game in progress!");

		}	

	}	

	public void onDeath() {
		
		kickPlayer("You died!");
		getGame().checkGameOver();
		getGame().playSound(Sound.ENTITY_LIGHTNING_THUNDER);
		
	}
		
}