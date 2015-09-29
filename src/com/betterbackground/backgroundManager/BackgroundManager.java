
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.platform.FileUtils;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class BackgroundManager {
	String channel;
	String [] pics;
	String directory = "C:\\Users\\Public\\BB";
	int buffer = 3;
	int count = 0;
	ImageDownloader imageDownloader;
	
	public BackgroundManager(String name, String[] urls){
		channel = name;
		pics = urls;
		int c = 0;
		int i = 0;
		imageDownloader = new ImageDownloader();
		
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
	
	public BackgroundManager() {
		// TODO Auto-generated constructor stub
	}

	private int downloadPic(int cnt, int w) {
		//make it a runnable
		int err = 0;
		try {
			err = imageDownloader.saveImage(pics[w], "name");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return err;
	}
	
	private int removePic(){
		int err = 0;
		
		return err;
	}
	
	private void cycleWallpaper(){
		// This is where the actual cycling and main part of the method happens
	}
	
    public static void main(String[] args) {
       //supply your own path instead of using this one
       //String path = "C:\\Users\\Public\\BBWallpapers\\test2.jpg";
    }
   

}
