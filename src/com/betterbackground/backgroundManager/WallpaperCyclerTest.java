package com.betterbackground.backgroundManager;

import static org.junit.Assert.*;

import org.junit.Test;


public class WallpaperCyclerTest {
	String temp [] = {"http://www.planwallpaper.com/static/images/black-and-blue-cubes-wallpaper1.jpg", "http://www.hobicell.com/wallpaper/lotr/lotr-rohan-desktop-wallpaper-by-zangville-on-rohan-middle-earth-wallpaper-.jpg",
			"http://www.hobicell.com/wallpaper/castle/castle-of-rivendell-wallpaper-hd-resolution-desktop-image-rivendell-wallpaper-for-desktop-.jpg", "http://www.neatorama.com/wp-content/uploads/2011/05/apocalypse-13-500x302.jpg",
			"http://neatdesigns.net/wp-content/uploads/2012/04/1238.jpg", "http://www.neatorama.com/wp-content/uploads/2011/05/apocalypse-13-500x302.jpg"};//6 wallpapers from funnyjunk
	WallpaperCycler wp;
	@Test
	public void testWallpaperCycler() {
		wp = new WallpaperCycler(temp);
		wp.run();
		if(wp.x != 2){
			fail("Not yet implemented");
		}

System.out.println("poop");
	}

}
