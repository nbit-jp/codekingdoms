package space.codekingdoms.nbituser.mybrainybunnies;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

public class Player extends BasePlayer {
	
	public void equip() {
		
		addItemToInventory(new ItemStack(Material.EGG, 8));
	
	}
	
	public void onJoin() {
		
		equip();
		setFaction(getGame().humans);
	
	}
	
	public void onProjectileHitTarget( String projectileType, Location hitZone ) {
		
		world.createExplosion(hitZone, 3f, true);
	
	}
		
}