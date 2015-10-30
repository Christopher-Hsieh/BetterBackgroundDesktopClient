package com.betterbackground.backgroundManager;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.platform.FileUtils;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class BackgroundManager {
	String channel;
	String [] pics;
	int count = 0;
	WallpaperCycler wp;
	String directory = "C:\\Users\\Public\\BetterBackground";
	
	//adds the new wallpapers to the end of the existing wallpapers and then passes it back to the wallpaper cycler which contains a count int
	public void passURLS(String[] urls){
		int aLen = pics.length;
		int bLen = urls.length;
		String[] newURLS= new String[aLen+bLen];
		System.arraycopy(pics, 0, newURLS, 0, aLen);
		System.arraycopy(urls, 0, newURLS, aLen, bLen);
		pics = newURLS;
	}
	
	public void newChannel(String name, String[] urls){
		channel = name;
		pics = urls;
		count = 0;
	}
	
	public void startWallpaperCycler(){
		wp = new WallpaperCycler(pics);
	}
	
	public BackgroundManager(){
    	/*makes the folder should it not exits/exists with pictures in it*/
		if (Files.exists(Paths.get(directory))) {
			new File(directory).delete();
		}    
		boolean success = new File(directory).mkdir();
		if(success != true){
			//this failed
			System.out.println("failed");
		}else{
			System.out.println("Created" + directory);
		}
	}
	
}
