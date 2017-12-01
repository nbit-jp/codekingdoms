package space.codekingdoms.nbituser.mybrainybunnies;

import com.codekingdoms.nozzle.base.BaseGame;
import com.codekingdoms.nozzle.utils.Random;

public class Game extends BaseGame {
	
	public Faction humans;
	
	public Player getRandomPlayer() {
		
		Player[] array = getPlayerList();
		if (array.length == 0) {
			
			return null;
			
		}
		
		return array[Random.generateInteger(0, array.length - 1)];
	
	}
	
	public void startRound() {
		
		humans = new Faction();
		
	}
	
	public void onCodeUpdate() {
		
		disableMobSpawning();
		createWorldBorder(50);
		world.setTime(13000);
		startRound();
		
	}
		
}