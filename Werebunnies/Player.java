package space.codekingdoms.nbituser.mybrainybunnies;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Player extends BasePlayer {
	
	public void equip() {
		
		addItemToInventory(new ItemStack(Material.EGG, 8));
	
	}
	
	public void onJoin() {
		
		equip();
		
	}
}