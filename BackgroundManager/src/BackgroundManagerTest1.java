import static org.junit.Assert.*;

import org.junit.Test;


public class BackgroundManagerTest1 {
	BackgroundManager bm = new BackgroundManager();
	@Test
	public void testChangeWallpaper() {
		int j = bm.changeWallpaper("C:\\Users\\Public\\BBWallpapers\\test.jpg");
		if(j == 1){
			fail("Not yet implemented");
		}
	}

}
