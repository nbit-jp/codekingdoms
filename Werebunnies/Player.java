package space.codekingdoms.nbituser.mybrainybunnies;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;
import com.codekingdoms.nozzle.utils.ArmorSet;

public class Player extends BasePlayer {
	
	public boolean isHuman;
	public int score = 0;
	
	public void equip() {
		
		clearInventory();
		equipItem(Material.DIAMOND_SWORD);
		
		if (!isHuman) {
			
			addItemToInventory(new ItemStack(Material.EGG, 8));
			equipFullArmorSet(ArmorSet.LEATHER);
			
		}
		
	}
	
	public void onJoin() {
		
		setHuman();
	
	}
	
	public void onProjectileHitTarget( String projectileType, Location hitZone ) {
		
		world.createExplosion(hitZone, 3f, true);
	
	}
	
	public void setHuman() {
		
		isHuman = true;
		setFaction(getGame().humans);
		equip();
	
	}
	
	public void setBunny() {
		
		isHuman = false;
		getGame().broadcastMessage(name + " became a werebunny!");
		setFaction(getGame().bunnies);
		equip();
	
	}
	
	public void onRespawn() {
		
		equip();
		
	}
	
	public void killed() {
		
		if(isHuman) {
			
			setBunny();
			getGame().checkGameOver(name);
			
		}
		
	}
	
	public void onKillPlayer(String playerKilledName) {
		
		if(!isHuman) {
			
			getGame().getPlayer(playerKilledName).killed();
			
		}
		
		if(isHuman == getGame().getPlayer(playerKilledName).isHuman) {
			
			score = score - 1;
			sendMessage("You betrayed " + playerKilledName);
			
		} else {
		
			score = score + 1;
			sendMessage("You killed " + playerKilledName);
		
		}

		sendMessage("Your score is now " + score);

	}
}