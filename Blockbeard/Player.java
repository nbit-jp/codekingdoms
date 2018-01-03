package space.codekingdoms.nbituser.mysmallisland;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Player extends BasePlayer {
	
	public void spawnPlayer() {
		
		clearInventory();
		teleport(getGame().getRandomBlockAtHeight(0));
		addItemToInventory(new ItemStack(Material.IRON_SWORD));
		addItemToInventory(new ItemStack(Material.IRON_PICKAXE));
		addItemToInventory(new ItemStack(Material.IRON_SPADE));
		addItemToInventory(new ItemStack(Material.COMPASS));
		setCompassTarget(getGame().chestPosition);
	
	}
	
	public void onJoin() {
		
		spawnPlayer();
	
	}
	
	public void onRespawn() {
		
		spawnPlayer();
		setBedSpawnLocation(getGame().getRandomBlockAtHeight(0));
	
	}
	
	
}