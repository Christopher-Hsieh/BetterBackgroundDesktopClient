import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;


public class passURLTest extends BackgroundManager {
	String temp [] = {"www.galaxy.com", "www.coolgalaxy.com", "www.neatgalaxy.com", "www.onegalaxyonefuture.com"};
	String dogs [] = {"puppies.com", "morepuppies.com", "lotsofpuppies.com", "heylookpuppies.com"};
	@Test
	public void testPassURLS() {
		BackgroundManager bm = new BackgroundManager();
		bm.newChannel("galaxy", temp);
		bm.passURLS(dogs);
		
		int aLen = temp.length;
		int bLen = dogs.length;
		String[] newURLS= new String[aLen+bLen];
		System.arraycopy(temp, 0, newURLS, 0, aLen);
		System.arraycopy(dogs, 0, newURLS, aLen, bLen);
		
		if(Arrays.equals(newURLS, bm.pics)){
			
		}else{
			fail("Not yet implemented");
		}
	}

}
