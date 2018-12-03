package space.codekingdoms.nbituser.myswiftgames;

import org.bukkit.World;
import org.bukkit.Location;
import com.codekingdoms.nozzle.base.BaseGame;
import org.bukkit.GameMode;

public class Game extends BaseGame {
	
	public int gamePhase;
	Location centerStage = new Location(world, 140.53, 72, 242.64);
	public Location[] spawnPoints;
	public int spawnNumber;

	public void onCodeUpdate() {
		
		spawnNumber = 0;
		spawnPoints = new Location[8];
		spawnPoints[0] = new Location(world, 140.52, 72 ,224.24);
		spawnPoints[1] = new Location(world, 140.51, 72 ,260.66);
		spawnPoints[2] = new Location(world, 158.55, 72 ,242.53);
		spawnPoints[3] = new Location(world, 122.2 , 72 ,242.31);
		spawnPoints[4] = new Location(world, 153.86, 72 ,255.43);
		spawnPoints[5] = new Location(world, 127.28, 72 ,229.5);
		spawnPoints[6] = new Location(world, 127.6,  72 ,255.64);
		spawnPoints[7] = new Location(world, 153.48, 72 ,229.33);
		
		setWorldBorderCenter(centerStage);
		startTimer(30);
		world.setPVP(false);
		disableMobSpawning();
		gamePhase = 1;
		broadcastMessage("Game starts in 30 seconds...");
		for(Player player : getPlayerList()) {
			movePlayerToSpawn(player);
		}

	}
	
	public void movePlayerToSpawn(Player player) {

			player.startGame();
			player.teleport(spawnPoints[spawnNumber]);
			spawnNumber = spawnNumber + 1;
			if(spawnNumber >= spawnPoints.length) {

				spawnNumber = 0;

			}
			createWorldBorder(500);
		
	}

	public void onTimerExpire() {
		
		gamePhase = gamePhase + 1;

		switch (gamePhase) {
			
			case 2:
				
				gamePhase = 2;
				startTimer(10);
				broadcastMessage("Go! PVP starrts in 10 seconds!");
				for (Player player : getPlayerList()) {
					player.canMove = true;
				}
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