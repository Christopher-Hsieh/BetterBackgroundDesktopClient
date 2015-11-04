import static org.junit.Assert.*;

import org.junit.Test;


public class saveImageTest {
	ImageDownloader id = new ImageDownloader();
	String blah = id.saveImage("http://www.planwallpaper.com/static/images/black-and-blue-cubes-wallpaper1.jpg");
	@Test
	public void testImageDownloader() {
		if(blah.equals("C:\\Users\\Public\\BetterBackground\\black-and-blue-cubes-wallpaper1.jpg")){
			System.out.println("haha gottem");
		}else{
			fail("Not yet implemented");
		}
	}

}
