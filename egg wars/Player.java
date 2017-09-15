package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {

	public boolean hasEgg;

	public void onJoin() {
		
		onStart();
	
	}
	
	public void onStart() {
		
		setAllowFlight(true);					// 飛べるか
		clearInventory();						// アイテムのクリア
		setGameMode(GameMode.SURVIVAL);			// サバイバルモード
		addItemToInventory(Material.BEACON);	// アイテムをインベントリに追加
		hasEgg = false;
	}
	
	public void onPlaceBlock( Block block ) {
		
		if (block.getType() == Material.BEACON) {
			if (! hasEgg) {
				placeSpawn(block);
			}
		}
	}
	
	public void placeSpawn( Block block ) {
		
		buildBase(block);
		hasEgg = true;
		getGame().broadcastMessage(ChatColor.AQUA + (name + " placed their egg."));
	
	}
	
	public void buildBase( Block block ) {
		
		getGame().setBlockTypeInArea(
			Material.IRON_BLOCK, 
			block.getLocation().add((new Location(world, -1, -1, -1))),
			block.getLocation().add((new Location(world,  1, -1,  1)))
		);
		getGame().setBlockTypeInArea(
			Material.AIR, 
			block.getLocation().add((new Location(world,  0,  1, 0))),
			block.getLocation().add((new Location(world,  0,128, 0)))
		);
		addProtectedArea(
			block.getLocation().add((new Location(world, -1, -1, -1))),
			block.getLocation().add((new Location(world,  1, -1,  1)))
		);
		block.getRelative( 1, 0, 0).setType(Material.WORKBENCH);
		block.getRelative(-1, 0, 0).setType(Material.CHEST);

	}
		
}