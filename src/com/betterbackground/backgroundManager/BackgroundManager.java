
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
	String directory = "C:\\Users\\Public\\BetterBackground";
	int bufferPics = 3;
	int count = 0;
	ImageDownloader imageDownloader;
	ArrayList<String> list = new ArrayList<String>();
	WallpaperCycler wp;
	/*
	public BackgroundManager(String name, String[] urls){
		channel = name;
		pics = urls;
		int c = 0;
		int i = 0;
		ImageDownloader thread = new ImageDownloader(urls[0]);
		
		//just bs paths
		if (Files.exists(Paths.get(directory))) {
			new File("/dir/path").delete();
		}    
		boolean success = new File("/dir/path").mkdir();	
		
		
		//download the buffer pics
		while(count < buffer - 1){
			 c=downloadPic(count, i);
			 if(c == 0){
				 if(count == 0){
					 changeWallpaper("path");
				 }
				 count++;
				 i++;
			 }else{
				 i++;
			 }
		}
		cycleWallpaper();//cycle the wallpapers
	}
	*/
	
    public static void main(String[] args) {
       //supply your own path instead of using this one when we start to implement
       //String path = "C:\\Users\\Public\\BBWallpapers\\test2.jpg";
		//just bs paths
    	String channel;
    	String [] pics;
    	String directory = "C:\\Users\\Public\\BetterBackground";
    	int bufferPics = 3;
    	int count = 0;
    	ImageDownloader imageDownloader;
    	ArrayList<String> images = new ArrayList<String>();
    	WallpaperCycler wp;
    	
    	/*makes the folder should it not exits/exists with pictures in it*/
		if (Files.exists(Paths.get(directory))) {
			new File("directory").delete();
		}    
		boolean success = new File("/dir/path").mkdir();
		
		//download the first four pictures
		for(int x = 0; x < bufferPics; x++){
			imageDownloader = new ImageDownloader("tempstring");
			imageDownloader.run();
			//check the string to make sure it's not an error
			images.add(imageDownloader.finalImage);
			//start the wallpaper cycler after the first wallpaper
			if(x == 0){
				//make it so that wallpapercycler() just runs off of one folder and starts right away
				wp = new WallpaperCycler(imageDownloader.finalImage);
			}
		}
		
		while(true){
			//implement the wallpaper remover
			//wallpaper cycler should be cycling 
			//downloader should be called when remove is called
			
		}
    }
   
	
}
