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
import com.betterbackground.userhandler.Interfaces.*;
import com.sun.jna.win32.*;

public class BackgroundManager {
	String channel;
	String [] pics;
	int count;
	WallpaperCycler wp;
	String directory = "C:\\Users\\Public\\BetterBackground";
	
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
/*	@Override
	public void getUrlsResult(String[] urls) {
		pics = urls; 
		if(wp.isAlive()){
			wp.changeURLS(pics);
		}else{
			//do nothing
			wp.getBatch();
		}
	}*/
	
	public void newChannel(String name, String[] urls){
		if(pics == null){
			pics = urls;
			channel = name;
		}else{
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
		}
		//pics = urls;
		//count = 0;d
		//synchronized method
	}
	
	public void startWallpaperCycler(){
		wp = new WallpaperCycler(pics);
		wp.getBatch();
		while(true){
			wp.run();
			try {
				wp.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wp.changeURLS(pics);
			wp.count = 0;
			wp.getInitialBatch();
		}
	}
	
	public BackgroundManager(){
		count = 0;
    	/*makes the folder should it not exits/exists with pictures in it*/
		if (Files.exists(Paths.get(directory))) {
			new File(directory).delete();
			//System.out.println("It was real");
		}    
		boolean success = new File(directory).mkdir();
		if(success != true){
			//System.out.println("failed in making the folder");
		}else{
			//System.out.println("Created" + directory);
		}
	}
	
}
