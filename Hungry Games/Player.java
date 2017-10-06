package space.codekingdoms.nbituser.myswiftgames;

import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void onDeath() {
		
		kickPlayer("You died!");
		
	}
		
}