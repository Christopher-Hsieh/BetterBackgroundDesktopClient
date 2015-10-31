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
	
	public void getBatch(){
		int x = getInitialBatch();
		System.out.println("X after initial batch is : " + x);
	}
	
	public synchronized void changeURLS(String[] a ){
		images = a; 
	}
	
	public void run(){
		while(!(inFolder.get(0).equals("haha"))){
			inFolder.remove((inFolder.get(0)));
			//System.out.println("REMOVE: " + inFolder.get(0));
			cycleWallpaper();
			id.run(count,images);
			if(id.finalImage.equals("haha")){
				break;
			}
			inFolder.add(id.finalImage);
			//System.out.println("ADD: " + id.finalImage);
			
			count++;
		}
		//System.out.println("OUT OF STUFF SO I'M LOOPING");
		inFolder.remove((inFolder.get(0)));
		count = 0;
		//id = new ImageDownloader();
		//id.run(count,images);
		//inFolder.add(id.finalImage);
		getInitialBatch();
		run();
		//System.out.println("X is : " + x);
	}
	
	public int getInitialBatch(){
		while(count < 3){
			id.run(count,images);
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

    	 String path = "";
    	 //System.out.println("NEXT: " + inFolder.get(0));
		 //System.out.println("CYCLE");
    	 path = inFolder.get(0);
	      SPI.INSTANCE.SystemParametersInfo(
	              new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
	              new UINT_PTR(0), 
	              path, 
	              new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
	      
	      try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
