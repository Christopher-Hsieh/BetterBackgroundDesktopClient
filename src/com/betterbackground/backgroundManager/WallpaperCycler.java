package com.betterbackground.backgroundManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;


public class WallpaperCycler extends Thread{
		String dest = "C:\\Users\\Public\\BetterBackground";
		String images [];
		int count = 0;
		int x = 0;//testing purposes
		ArrayList<String> inFolder;
		ImageDownloader id; 
		
	public WallpaperCycler(String [] wallpaperURLS){
		images = wallpaperURLS;
		count = 0;
		inFolder = new ArrayList<String>();
		id = new ImageDownloader(wallpaperURLS);
	}
	
	public void run(){
		int x = getInitialBatch();
		while(true){
			cycleWallpaper();
			System.out.println("hey i'm in here");
			inFolder.remove((inFolder.get(0)));
			id.run(count);
			inFolder.add(id.finalImage);
			count++;
		}
	}
	
	public int getInitialBatch(){
		String temp;
		while(count < 3){
			id.run(count);
			inFolder.add(id.finalImage);
			if(count == 0){
				cycleWallpaper();
			}
			count++;
			x++;
		}
		return count;
	}
	
	
    public int cycleWallpaper(){

    	System.out.println("finally into cycleWallpaper");
    	 String path = "";
    	 System.out.println("Looking for this one: " + inFolder.get(0));
    	 path = inFolder.get(0);
	      SPI.INSTANCE.SystemParametersInfo(
	              new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
	              new UINT_PTR(0), 
	              path, 
	              new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
	      
	      try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	      System.out.println("yeah returning");
	      return 0;
}
    

  public interface SPI extends StdCallLibrary {

  //from MSDN article
  long SPI_SETDESKWALLPAPER = 20;
  long SPIF_UPDATEINIFILE = 0x01;
  long SPIF_SENDWININICHANGE = 0x02;

  SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
     {
        put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
        put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
     }
  });

  boolean SystemParametersInfo(
      UINT_PTR uiAction,
      UINT_PTR uiParam,
      String pvParam,
      UINT_PTR fWinIni
    );
  }
}
