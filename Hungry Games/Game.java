package space.codekingdoms.nbituser.myswiftgames;

import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public int gamePhase;
	
	public void onCodeUpdate() {
		
		createWorldBorder(6);
		setWorldBorderCenter(new Location(world, 140.53, 72, 242.64));
		startTimer(30);
		world.setPVP(false);
		disableMobSpawning();
		gamePhase = 1;
		broadcastMessage("Game starts in 30 seconds...");
		
	}
	
	public void onTimerExpire() {
	
		if (gamePhase == 1) {
			
			createWorldBorder(500);
			gamePhase = 2;
			startTimer(10);
			broadcastMessage("Go! PVP starrts in 10 seconds!");
			
		} else if (gamePhase == 2) {
			
			world.setPVP(true);
			broadcastMessage("PVP is started! Fight!");
			gamePhase = 3;
			
		}
	}
			
}