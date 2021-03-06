package space.codekingdoms.nbituser.myangryegg;

import com.codekingdoms.nozzle.base.BasePlayer;
import org.bukkit.Material;
import org.bukkit.GameMode;

public class Player extends BasePlayer {

	public boolean hasEgg;
	public Block egg;

	public void onJoin() {
		
		egg = world.getBlockAt(getLocation());
		if (getGame().fightStarted) {
			setGameMode(GameMode.SPECTATOR);
			hasEgg = false;
			sendMessage("There's already a game in progress... You will join when it restarts!");
		} else {
			onStart();
		}

	}
	
	public void onStart() {
		
		setAllowFlight(true);					// 飛べるか
		clearInventory();						// アイテムのクリア
		setGameMode(GameMode.SURVIVAL);			// サバイバルモード
		addItemToInventory(Material.BEACON);	// アイテムをインベントリに追加
		hasEgg = false;
		canMine = false;
	}
	
	public void onPlaceBlock( Block block ) {
		
		if (block.getType() == Material.BEACON) {
			if (!hasEgg && !getGame().fightStarted) {
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
		getGame().placeEgg();

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
		
		if (getGame().fightStarted) {
			setSpawn();
			clearInventory();
			if (! hasEgg) {
				setGameMode(GameMode.SPECTATOR);
				getGame().onPlayerLost();
			}
		}
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

	public void onStartFight() {
		
		clearInventory();
		returnToSpawn();
		canMine = true;
		setAllowFlight(false);
		resetHealth();
		
	}

}