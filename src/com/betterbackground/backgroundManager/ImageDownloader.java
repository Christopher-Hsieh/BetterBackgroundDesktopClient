import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageDownloader extends Thread {
	  String temp[];
	  String finalImage;
	  String removeImage;
	  String passedURLS[];
	  ArrayList<String> locals = new ArrayList<String>();
	  int count;

	  
	ImageDownloader(String someURLS[]){
		count = 0;
		passedURLS = someURLS;
		System.out.println("passed string is " + someURLS[count]);
	}
	
	ImageDownloader(){
		
	}
	
  public void run(int count, String [] stuff){
	 // if(count > 5){
	  passedURLS = stuff;
	  String temp = null;
	  	try{
	  		System.out.println("LOOKING FOR:  "+ passedURLS[count]);
	  		temp = passedURLS[count];
	  	}catch(Exception e){
	  		temp = "haha";
	  	}
	//  }else{
		// System.out.println(temp);
	  	if(temp.equals("haha")){
	  		System.out.println("ITS NULL");
	  		finalImage = "haha";
	  		locals.add("haha");
	  	}else{
		  finalImage = saveImage(temp);
		  locals.add(finalImage);
		 // if(count > 3){
		//	   removeImage = destroyImage();
		//	   locals.remove(0);
		 // }
		  //System.out.println("after");
	  //}
	  	}

  }
  
  public String destroyImage(){
	  String temp;
	  temp = locals.get(0);
	  System.out.println("DESTRYOING: " + locals.get(0));
	  File file = new File("C:\\Users\\Public\\BetterBackground\\" + locals.get(0));
	  try{
		  file.delete();
	  }catch(Exception e){
		  System.out.println(e);
	  }
	  return temp;
  }
  
  public String saveImage(String imageUrl){
	  
	  int len; 
	  String destinationFile = "C:\\Users\\Public\\BetterBackground\\";
	  String name; 
	  
	  URL url = null; 
	  InputStream is;
	  OutputStream os;
	  BufferedImage image = null;
	  String fileType = "";
	  HttpURLConnection connection;
	  
	  if (imageUrl.equals(null)){
		  return imageUrl;
	  }
	  try {
		    url = new URL(imageUrl);
		    if(url == null){
		    	System.out.println("this is why");
		    }
		    connection = (HttpURLConnection) url.openConnection();
		    if(connection == null){
		    	System.out.println("connection is null");
		    }
		    connection.setRequestProperty(
		    	    "User-Agent",
		    	    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		    image = ImageIO.read(connection.getInputStream());
		    if(image == null){
		    	System.out.println("wtf");
		    }
			name = url.getFile();
			destinationFile =  destinationFile + name.substring(name.lastIndexOf("/") + 1);
			if(name.substring(name.lastIndexOf(".") + 1).equals("jpg")){
				fileType = "jpg";
			}
			else if(name.substring(name.lastIndexOf(".") + 1).equals("png")){
				fileType = "png";
			}else{
				System.out.println("new format");
			}
			System.out.println("inside saveImage, filetype: " + fileType + " filename: " + destinationFile);
			
		    ImageIO.write(image, fileType, new File(destinationFile));
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("halp");
		}
	  
	  /*
	  try{
		  url = new URL(imageUrl);
		  System.out.println("good url");
	  }catch( MalformedURLException e){
		  count++;
		  return "Bad URL";//this way we know it's a bad url
	  }
	  
	  
	  /*
	  	try{
	  		connection=url.openConnection();
	  	}catch(Exception e){
	  		count++;
	  		System.out.println(e);
	  		return "Bad Connection";
	  	}
	  	
	  System.out.println("desination file ISSSS:" + destinationFile);
	  try{
		  is = url.openStream();
	  }catch(Exception e){
		  count++;
		  System.out.println("Something weird is happening here");
		  System.out.println(e);
		  return "Bad Stream";
	  }
	  
	  try{
		  os = new FileOutputStream(destinationFile);
	  }catch(Exception e){
		  count++;
		  System.out.println("Something is happening with the destination file");
		  return "Bad Destination";
	  }
	  //add different things to return depending on the file
	  //to do just read in one byte at a time
	  byte[] b = new byte[2048];
	  int length;

	  try {
		  while ((length = is.read(b)) != -1) {
			  try {
				  os.write(b, 0, length);
			  } catch (IOException e) {
				  e.printStackTrace();
				  return "Bad Image";
			  }
		  }
	  } catch (IOException e1) {
		  e1.printStackTrace();
	  }

	  try {
		  is.close();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  
	  try {
		  os.close();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	  
	  */
	  //count++;
  return destinationFile;
 }
}