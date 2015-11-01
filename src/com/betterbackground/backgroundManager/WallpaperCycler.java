package com.betterbackground.backgroundManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
		id = new ImageDownloader(0, wallpaperURLS);
	}
	
	
	public synchronized void changeURLS(String[] a ){
		images = a; 
	}
	
	public String getAnother(){
		id.run();
		try {
			id.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("heeeelllppp");
		}
		return id.finalImage;
	}
	public void run(){
		String temp;
		while(true){
			System.out.println("hey");
			File dir = new File("C:\\Users\\Public\\BetterBackground");
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
			  for (File child : directoryListing) {
			      // Do something with child
				  System.out.println("hey doing something");
				  String path = (child.getPath());
			      System.out.println("PATH: " + path);
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
				  System.out.println("it's past the cycleWallpaper");
			    }
			  }
			System.out.println("2");
		}
			/*String sourceDirectory = "C:\\Users\\Public\\BetterBackground";
			File dir = new File(sourceDirectory);
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
			  for (File child : directoryListing) {
			      // Do something with child
				  System.out.println("hey doing something");
				  cycleWallpaper(child.getPath());
				  System.out.println("it's past the cycleWallpaper");
			    }
			  } else {
			    // Handle the case where dir is not really a directory.
			    // Checking dir.isDirectory() above would not be sufficient
			    // to avoid race conditions with another process that deletes
			    // directories.
			  }
		}*/
			/*	while(!(inFolder.get(0).equals("haha"))){
					inFolder.remove((inFolder.get(0)));
					System.out.println("REMOVE: " + inFolder.get(0));
					System.out.println("Count: " + count);
					cycleWallpaper();
					temp = getAnother();
					count++;
					if(temp.equals("haha")){
						break;
					}
					inFolder.add(temp);
					//System.out.println("ADD: " + id.finalImage);
					System.out.println("Made it here");
				}
				System.out.println("OUT OF STUFF SO I'M LOOPING");
				inFolder.remove((inFolder.get(0)));
				cycleWallpaper();
				inFolder.remove((inFolder.get(0)));
				count = 0;
				//id = new ImageDownloader();
				//id.run(count,images);
				//inFolder.add(id.finalImage);
				System.out.println("count: " + count);
				getInitialBatch();
				//System.out.println("X is : " + x);
			}*/
	}
	
	/*public int getInitialBatch(){
		while(count < 3){
			id = new ImageDownloader(count, images);
			id.start();
			try {
				id.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("heeeelllppp");
			}
			inFolder.add(id.finalImage);
			if(count == 0){
				System.out.println("RRREEEEEEEEEEEEEEEEEEE");
				cycleWallpaper(in);
			}
			count++;
		}
		return count;
	}*/
	
	
    public int cycleWallpaper(String path){

    	 //System.out.println("NEXT: " + inFolder.get(0));
		 //System.out.println("CYCLE");
    	 System.out.println("PATH: " + path);
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
