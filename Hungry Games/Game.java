package space.codekingdoms.nbituser.myswiftgames;

import org.bukkit.World;
import org.bukkit.Location;
import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public int gamePhase;
	Location centerStage = new Location(world, 140.53, 72, 242.64);

	public void onCodeUpdate() {
		
		createWorldBorder(6);
		setWorldBorderCenter(centerStage);
		startTimer(30);
		world.setPVP(false);
		disableMobSpawning();
		gamePhase = 1;
		broadcastMessage("Game starts in 30 seconds...");
		for (Player player : getPlayerList()) {
			
			player.startGame();
			
		}		

	}
	
	public void onTimerExpire() {
	
		gamePhase = gamePhase + 1;
		
		switch (gamePhase) {
			
			case 2:

				createWorldBorder(500);
				gamePhase = 2;
				startTimer(10);
				broadcastMessage("Go! PVP starrts in 10 seconds!");
				break;
			
			case 3:
			
				world.setPVP(true);
				broadcastMessage("PVP is started! Fight!");
				startTimer(600);
				break;
			
			case 4:
				
				expandWorldBorder(-450, 120);
				break;
				
			case 5:
				
				resetGame();
				break;
		}
	}
	
	public void checkGameOver() {
		
		int playerCount = 0;

		for (Player player : getPlayerList()) {

			if (player.getGameMode() == GameMode.ADVENTURE) {

				playerCount = playerCount + 1;
				
			}
						
		}
		
		if (playerCount < 2) {
				
			startTimer(10);
			broadcastTitle("You won!", "May the odds be ever in your favour!");
			gamePhase = 4;
				
		}
	}
}