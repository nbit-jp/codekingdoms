package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void spawnPlayer() {
		
		teleport(getGame().getRandomBlockAtHeight(0));

	}

	public void onJoin() {
		
		spawnPlayer();
		
	}
	
	public void onRespawn() {
		
		spawnPlayer();
		
	}
	
}