package com.betterbackground.backgroundManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.betterbackground.userhandler.Interfaces.GetUrlsListener;
import com.sun.jna.Native;
import com.sun.jna.platform.FileUtils;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class BackgroundManager implements GetUrlsListener {
	String channel;
	String [] pics;
	int count = 0;
	WallpaperCycler wp;
	String directory = "C:\\Users\\Public\\BetterBackground";
	
	@Override
	public void getUrlsResult(String[] urls) {
		//ArrayList<String> pics
		//pics.add(urls);
		pics = urls;
		
		wp = new WallpaperCycler(pics);
		wp.getBatch();
		wp.run();
	}
	
	//adds the new wallpapers to the end of the existing wallpapers and then passes it back to the wallpaper cycler which contains a count int
	//public void passURLS(String channelName, String[] urls){
		//int aLen = pics.length;
		//int bLen = urls.length;
		//String[] newURLS= new String[aLen+bLen];
		//System.arraycopy(pics, 0, newURLS, 0, aLen);
		//System.arraycopy(urls, 0, newURLS, aLen, bLen);
		//pics = newURLS;
		//wp.images = urls;
		//wp.run();
	//}
	
	public void newChannel(String name, String[] urls){
		int aLen = pics.length;
		int bLen = urls.length;
		String[] newURLS= new String[aLen+bLen];
		System.arraycopy(pics, 0, newURLS, 0, aLen);
		System.arraycopy(urls, 0, newURLS, aLen, bLen);
		pics = newURLS;
		channel = name;
		if(wp.isAlive()){
			wp.changeURLS(pics);
		}
		//pics = urls;
		//count = 0;
		//synchronized method
	}
	
	public void startWallpaperCycler(){
		wp = new WallpaperCycler(pics);
		wp.getBatch();
		wp.run();
	}
	
	public BackgroundManager(){
    	/*makes the folder should it not exits/exists with pictures in it*/
		if (Files.exists(Paths.get(directory))) {
			new File(directory).delete();
			System.out.println("It was real");
		}    
		boolean success = new File(directory).mkdir();
		if(success != true){
			//this failed
			//System.out.println("failed in making the folder");
		}else{
			System.out.println("Created" + directory);
		}
	}	
}
