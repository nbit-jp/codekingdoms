package space.codekingdoms.nbituser.mylazyheist;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class Player extends BasePlayer {
	
	public int score;
	
	public void onJoin() {
		
		setTimeout(
			
			() -> {
				
				setResourcePack("https://s3-eu-west-1.amazonaws.com/modding.codekingdoms.com/resourcePacks/present.zip");
				
			}
			
		
		, 1);
	
	}
	
	public void onMine( Block block ) {
		
		if (block.getType().equals(Material.STAINED_GLASS)) {
			
			score = score + 1;
			getGame().broadcastMessage( name + " got a box, now has " + score );
			
		}
		
	
	}
	
	
}