package com.betterbackground.backgroundManager;

import static org.junit.Assert.*;

import org.junit.Test;


public class getInitialBatchTest {
	String temp [] = {"http://www.planwallpaper.com/static/images/black-and-blue-cubes-wallpaper1.jpg", "http://www.hobicell.com/wallpaper/lotr/lotr-rohan-desktop-wallpaper-by-zangville-on-rohan-middle-earth-wallpaper-.jpg",
"http://www.hobicell.com/wallpaper/castle/castle-of-rivendell-wallpaper-hd-resolution-desktop-image-rivendell-wallpaper-for-desktop-.jpg", "http://media.moddb.com/images/members/1/458/457142/gaming_ak_1854084142.png",
"http://neatdesigns.net/wp-content/uploads/2012/04/1238.jpg", "http://www.neatorama.com/wp-content/uploads/2011/05/apocalypse-13-500x302.jpg"};//6 wallpapers
	ImageDownloader id = new ImageDownloader();
	WallpaperCycler wc;
	@Test
	public void testGetInitialBatch() {
		wc = new WallpaperCycler(temp);
		wc.getInitialBatch();
		fail("Not yet implemented");
	}

}
