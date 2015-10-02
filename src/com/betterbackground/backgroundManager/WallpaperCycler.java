import java.io.File;
import java.util.HashMap;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;


public class WallpaperCycler extends Thread{
	String wall;
	public WallpaperCycler(String dest){
		wall = dest;
		//need to make it so that it runs continuously and just cycles through wallpapers in a folder
	}
	public void run(){
		CycleWallpaper(wall);
	}
    public static int CycleWallpaper(String path){
    	/*final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("jpeg", "png");
    			final File file = new File("whatever");
    			for (final File child : file.listFiles()) {
    			    if(extensionFilter.accept(child)) {
    			        //do stuff with image
    			    }
    			}
    			*/
	      SPI.INSTANCE.SystemParametersInfo(
	              new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
	              new UINT_PTR(0), 
	              path, 
	              new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
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
