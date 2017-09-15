package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {

	public boolean hasEgg;
	public Block egg;

	public void onJoin() {
		
		onStart();
		egg = world.getBlockAt( getLocation() );

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
		egg = block;
		setSpawn();

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

	public void setSpawn(){
		
		Location spawnLocation = egg.getLocation();
		spawnLocation = world.getHighestBlockAt(spawnLocation).getLocation();
		spawnLocation.add(new Location(world, 0, 1, 0));
		setBedSpawnLocation(spawnLocation);
		
	}
	
	public void onDeath(){
		
		setSpawn();
		
	}

	public void onMine(Block block){
		if (block.getType() == Material.BEACON) {
			for(Player player : getGame().getPlayerList()){
				if (player.hasEgg && player.egg.equals(block)){
					player.loseEgg();
				}
			}
		}
	}
	
	public void loseEgg() {
		
		hasEgg = false;
		sendMessage(ChatColor.RED + "Your egg was broken! You will no longer respawn!");
		getGame().broadcastMessage(ChatColor.AQUA + (name + "'s egg was broken."));
	}
	
}