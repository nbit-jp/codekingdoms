package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Rabbit.Type;
import org.bukkit.ChatColor;

public class Game extends BaseGame {
	
	int radius = 50;
	
	Location chestPosition;
	
	public void onCodeUpdate() {
		
		startTimer(300);
		createWorldBorder(radius * 2);
		startRound();
		for (Player player : getPlayerList()) {
			
			player.score = 0;
			player.addToScore(0);
			
		}
	}
	
	public void startRound() {
		
		chestPosition = getRandomBlockAtHeight(-3);
		setBlockTypeAtLocation(Material.EMERALD_BLOCK, chestPosition);
		setInterval(
			
			() -> {
				
				world.spawnParticle(Particle.ENCHANTMENT_TABLE, world.getHighestBlockAt(chestPosition.getBlockX(), chestPosition.getBlockZ()).getLocation(), 50);
				
			}
		, 0, 1);
		for (Player player: getPlayerList()) {
			
			player.spawnPlayer();
			
		}
		
	}
	
	public void endGame() {
		
		String bestPlayer = "";
		int highestScore = 0;
		//Check every Player
		for (Player player: getPlayerList()) {
			
			player.sendMessage(( ChatColor.AQUA + "Yer score be " ) + player.score);
			if (player.score > highestScore) {
				
				highestScore = player.score;
				bestPlayer = player.name;
				
			}
		}
		
		if (getPlayerList().length > 1) {
			
			broadcastTitle("Game over!", ( ChatColor.GREEN + "The best player be " ) + bestPlayer);
			
		}
		
		else {
			
			broadcastTitle("Game over!", (ChatColor.GREEN + "Yer score be ") + highestScore);
			
		}
		
		//We have the best player!
		broadcastMessage("The game is over!");
		cancelChest();
		resetGame();
	
	}
	
	public void cancelChest() {
		
		setBlockTypeAtLocation(Material.AIR, chestPosition);
		stopAllTimeouts();
	
	}
	
	public void onTimerExpire() {
		
		endGame();
	
	}
	
	public void checkSurvivors() {
		
		int alivePlayerCount = 0;
		int lastPlayerIndex = -1;
		Player[] playerList = getPlayerList();
		for (int i = 0; i < playerList.length; i = i + 1) {
			
			if (playerList[i].alive) {
				
				lastPlayerIndex = i;
				alivePlayerCount = alivePlayerCount + 1;
				if (alivePlayerCount >= 2) {
					return;
				}
			}
			
		}
		
		if (alivePlayerCount == 0) {
			endGame();
			return;
		}
		Player lastSurvivor = playerList[lastPlayerIndex];
		lastSurvivor.addToScore(5);
		cancelChest();
		startRound();
		broadcastTitle("Yarr!", lastSurvivor.name + " be the last pirate standing!");
	}
	
	public Location getRandomBlockAtHeight( int yOffest ) {
		
		Location spawnLocation = world.getSpawnLocation();
		int x = spawnLocation.getBlockX() + Random.generateInteger(- radius, radius);
		int z = spawnLocation.getBlockZ() + Random.generateInteger(- radius, radius);
		int y = world.getHighestBlockAt(x, z).getY() + yOffest;
		return new Location(world, x, y, z);
	
	}
	
	
}