package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {
	
	public boolean alive;
	public int score;
	
	public void spawnPlayer() {
		
		alive = true;
		setGameMode(GameMode.SURVIVAL);
		clearInventory();
		teleport(getGame().getRandomBlockAtHeight(0));
		addItemToInventory(new ItemStack(Material.IRON_SWORD));
		addItemToInventory(new ItemStack(Material.IRON_PICKAXE));
		addItemToInventory(new ItemStack(Material.IRON_SPADE));
		addItemToInventory(new ItemStack(Material.COMPASS));
		setTimeout(
			
			() -> {
				
				setCompassTarget(getGame().chestPosition);
				
			}
		, 1);
	}
	
	public void onJoin() {
		
		spawnPlayer();
	
	}
	
	public void onRespawn() {
		
		if (alive) {
			
			spawnPlayer();
			setBedSpawnLocation(getGame().getRandomBlockAtHeight(0));
		}
			
	}
	
	public void onMine( Block block ) {
		
		if (block.equals(world.getBlockAt(getGame().chestPosition))) {
			
			addToScore(10);
			getGame().broadcastTitle("Treasure found!", name + " has found the loot!");
			getGame().cancelChest();
			getGame().startRound();
			
		}
	}
	
	public void addToScore( int scoreChange ) {
		
		score = score + scoreChange;
		setPlayerListName(( name + ": " ) + score);
	
	}
	
	public void onKillPlayer( String playerKilledName ) {
		
		addToScore(1);
	
	}
	
	public void onDeath() {
		
		alive = false;
		setGameMode(GameMode.SPECTATOR);
		getGame().checkSurvivors();
	
	}
	
}