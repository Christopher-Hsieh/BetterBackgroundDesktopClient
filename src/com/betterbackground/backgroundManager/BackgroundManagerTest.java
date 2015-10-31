package com.betterbackground.backgroundManager;
import static org.junit.Assert.*;

import org.junit.Test;


public class BackgroundManagerTest {
	String temp [] = {"http://www.planwallpaper.com/static/images/black-and-blue-cubes-wallpaper1.jpg", "http://www.hobicell.com/wallpaper/lotr/lotr-rohan-desktop-wallpaper-by-zangville-on-rohan-middle-earth-wallpaper-.jpg",
			"http://www.hobicell.com/wallpaper/castle/castle-of-rivendell-wallpaper-hd-resolution-desktop-image-rivendell-wallpaper-for-desktop-.jpg", "http://www.neatorama.com/wp-content/uploads/2011/05/apocalypse-13-500x302.jpg",
			"http://neatdesigns.net/wp-content/uploads/2012/04/1238.jpg", "http://i.imgur.com/GlHQ5vO.jpg", "http://i.imgur.com/bz94RpX.jpg"};//6 wallpapers from funnyjunk
	BackgroundManager bm = new BackgroundManager();

	@Test
	public void test() {
		bm.newChannel("Galaxies", temp);
		bm.startWallpaperCycler();
		//fail("Not yet implemented");
	}

}
