package space.codekingdoms.nbituser.mylazyheist;

import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		setTimeout(
			
			() -> {
				
				setResourcePack("https://s3-eu-west-1.amazonaws.com/modding.codekingdoms.com/resourcePacks/present.zip");
				
			}
			
		
		, 1);
	
	}
	
	
}