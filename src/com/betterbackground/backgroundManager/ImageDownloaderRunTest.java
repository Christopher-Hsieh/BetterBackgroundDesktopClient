import static org.junit.Assert.*;

import org.junit.Test;


public class ImageDownloaderRunTest {
	
	String temp [] = {"http://www.planwallpaper.com/static/images/black-and-blue-cubes-wallpaper1.jpg", "http://www.hobicell.com/wallpaper/lotr/lotr-rohan-desktop-wallpaper-by-zangville-on-rohan-middle-earth-wallpaper-.jpg",
"http://www.hobicell.com/wallpaper/castle/castle-of-rivendell-wallpaper-hd-resolution-desktop-image-rivendell-wallpaper-for-desktop-.jpg", "http://media.moddb.com/images/members/1/458/457142/gaming_ak_1854084142.png",
"http://neatdesigns.net/wp-content/uploads/2012/04/1238.jpg", "http://www.neatorama.com/wp-content/uploads/2011/05/apocalypse-13-500x302.jpg"};//6 wallpapers from funnyjunk
	int count = 0;
	

	@Test
	public void testRunInt() {
		ImageDownloader id = new ImageDownloader(temp);
		id.run(2);
		System.out.println("HEY THE FINAL IMAGE IS: " + id.finalImage);
		if((id.finalImage).equals("C:\\Users\\Public\\BetterBackground\\castle-of-rivendell-wallpaper-hd-resolution-desktop-image-rivendell-wallpaper-for-desktop-.jpg")){
			System.out.println("I'm on a roll");
		}else{
			fail("Not yet implemented");
		}
	}

}
