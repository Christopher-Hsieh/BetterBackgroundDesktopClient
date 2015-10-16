import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;


public class newChannelTest extends BackgroundManager {
	String c = "Galaxies";
	String temp [] = {"www.galaxy.com", "www.coolgalaxy.com", "www.neatgalaxy.com", "www.onegalaxyonefuture.com"};
	@Test
	public void testPassURLS() {
		BackgroundManager bm = new BackgroundManager();
		bm.newChannel(c, temp);
		if((bm.channel).equals(c) && Arrays.equals(temp, bm.pics)){
			
		}else{
			fail("Not yet implemented");
		}
	}

}
