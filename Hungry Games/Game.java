package space.codekingdoms.nbituser.myswiftgames;

import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public void onCodeUpdate() {
		
		createWorldBorder(6);
		setWorldBorderCenter(new Location(world, 140.53, 72, 242.64));
		startTimer(30);
		world.setPVP(false);
		disableMobSpawning();
	}
	
	public void onTimerExpire() {
		
		world.setPVP(true);
		createWorldBorder(500);
	}
			
}