package space.codekingdoms.nbituser.mybrainybunnies;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

public class Player extends BasePlayer {
	
	public boolean isHuman;
	
	public void equip() {
		
		addItemToInventory(new ItemStack(Material.EGG, 8));
	
	}
	
	public void onJoin() {
		
		equip();

	}
	
	public void onProjectileHitTarget( String projectileType, Location hitZone ) {
		
		world.createExplosion(hitZone, 3f, true);
	
	}
	
	public void setHuman() {
		
		isHuman = true;
		
	}

	public void setBunny() {
		
		isHuman = false;
		
	}	
}