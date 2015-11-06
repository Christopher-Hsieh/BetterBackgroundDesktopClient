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
	
<<<<<<< HEAD
	
	public synchronized void changeURLS(String[] a ){
		images = a; 
=======
<<<<<<< HEAD
	
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
		//	System.out.println("heeeelllppp");
		}
		return id.finalImage;
	}
	public void run(){
		while(true){
		//	System.out.println("heyddddddddddddddd");
			File dir = new File("C:\\Users\\Public\\BetterBackground");
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
			  for (File child : directoryListing) {
			      // Do something with child
			//	  System.out.println("hey doing something");
				  String path = (child.getPath());
		//	      System.out.println("PATH: " + path);
				  if(path.endsWith(".png") || path.endsWith(".jpg") ){
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
				  }
			//	  System.out.println("it's past the cycleWallpaper");
			    }
			  }
		//	System.out.println("2");
=======
	public void getBatch(){
		int x = getInitialBatch();
		System.out.println("X after initial batch is : " + x);
>>>>>>> master
	}
	
	public String getAnother(){
		id.run();
		try {
			id.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	System.out.println("heeeelllppp");
		}
		return id.finalImage;
	}
	public void run(){
<<<<<<< HEAD
		while(true){
		//	System.out.println("heyddddddddddddddd");
			File dir = new File("C:\\Users\\Public\\BetterBackground");
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
			  for (File child : directoryListing) {
			      // Do something with child
			//	  System.out.println("hey doing something");
				  String path = (child.getPath());
		//	      System.out.println("PATH: " + path);
				  if(path.endsWith(".png") || path.endsWith(".jpg") ){
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
				  }
			//	  System.out.println("it's past the cycleWallpaper");
			    }
			  }
		//	System.out.println("2");
=======
		while(!(inFolder.get(0).equals("haha"))){
			inFolder.remove((inFolder.get(0)));
			System.out.println("REMOVE: " + inFolder.get(0));
			cycleWallpaper();
			id.run(count,images);
			if(id.finalImage.equals("haha")){
				break;
			}
			inFolder.add(id.finalImage);
			System.out.println("ADD: " + id.finalImage);
			
			count++;
>>>>>>> origin/master
>>>>>>> master
		}
	}
	
<<<<<<< HEAD
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
=======
<<<<<<< HEAD
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
=======
	public int getInitialBatch(){
		while(count < 3){
			id.run(count,images);
>>>>>>> origin/master
>>>>>>> master
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

<<<<<<< HEAD
    	 //System.out.println("NEXT: " + inFolder.get(0));
		 //System.out.println("CYCLE");
    	// System.out.println("PATH: " + path);
=======
<<<<<<< HEAD
    	 //System.out.println("NEXT: " + inFolder.get(0));
		 //System.out.println("CYCLE");
    	// System.out.println("PATH: " + path);
=======
    	 String path = "";
    	 System.out.println("NEXT: " + inFolder.get(0));
		 System.out.println("CYCLE");
    	 path = inFolder.get(0);
>>>>>>> origin/master
>>>>>>> master
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