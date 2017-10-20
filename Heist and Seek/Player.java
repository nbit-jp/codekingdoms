package space.codekingdoms.nbituser.mylazyheist;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class Player extends BasePlayer {
	
	public int score;
	public boolean isHider;
	
	public void onJoin() {
		
		setTimeout(
			
			() -> {
				
				setResourcePack("https://s3-eu-west-1.amazonaws.com/modding.codekingdoms.com/resourcePacks/present.zip");
				
			}
			
		
		, 1);
	
	}
	
	public void onMine( Block block ) {
		
		if (( ! isHider ) && block.getType().equals(Material.STAINED_GLASS)) {
			
			score = score + 1;
			getGame().broadcastMessage( name + " got a box, now has " + score);
			
		}
	
	}
	
	public void startHiding() {
		
		sendMessage("You're hiding the boxes! Hide as many boxes as you can around the map.");
		teleport( getGame().outside );
		
	}
	
	public void freeze() {
		
		sendMessage("You're going to be finding boxes! wait for the hider to hide all the boxes!" );
		teleport( getGame().jail );
		
	}
	
}