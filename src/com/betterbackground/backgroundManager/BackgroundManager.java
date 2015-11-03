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
	ImageDownloader id; 
	ArrayList<String> seen;
	String directory = "C:\\Users\\Public\\BetterBackground";
	
	
	public void newChannel(String name, String[] urls){
		//System.out.println("Hey i'm in newchannel");
		//remove this check
		boolean found = false;
		for(int i = 0; i < seen.size(); i ++){
			if(seen.get(i).equals(name)){
				found = true;
				break;
			}
		}
		if (found == false){
			seen.add(name); //returns if the channel name is already there
		}else{
			return;
		}
		
		if(pics == null){
			//System.out.println("it's null");
			pics = urls;
			channel = name;
			id = new ImageDownloader(count, pics);
		}else{
			int aLen = pics.length;
			int bLen = urls.length;
			String[] newURLS= new String[aLen+bLen];
			System.arraycopy(pics, 0, newURLS, 0, aLen);
		//remove this
			System.arraycopy(urls, 0, newURLS, aLen, bLen);
		//so it doesn't append
			pics = newURLS;
			channel = name;
			
			if(wp.isAlive()){
				//System.out.println("It's alive----------------------------------------------");
					wp.changeURLS(pics);
					id = new ImageDownloader(count, pics);
			}
			
		}
		id.start();
	}
	
	public void startWallpaperCycler(){
		wp = new WallpaperCycler(pics);
		wp.start();
		//System.out.println("started both");
	}
	
	
	public BackgroundManager(){
		count = 0;
		seen = new ArrayList<String>();
    	/*makes the folder should it not exits/exists with pictures in it*/
		File index = new File(directory);
		if (Files.exists(Paths.get(directory))) {
			String[] entries = index.list();
			for(String s: entries){
			    File currentFile = new File(index.getPath(),s);
			    currentFile.delete();
			}
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
