package com.betterbackground.backgroundManager;
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
	  int fails;
	  int passes;
	  
	ImageDownloader(String someURLS[]){
		count = 0;
		fails = 0;
		passes = 0;
		passedURLS = someURLS;
		System.out.println("passed string is " + someURLS[count]);
	}
	
	ImageDownloader(){
		
	}
	
  public void run(int count, String [] stuff){
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
	  	}else if(saveImage(temp).equals("BadURL") || saveImage(temp).equals("ConnectionException") || saveImage(temp).equals("ImageNull") || 
	  			saveImage(temp).equals("WrongFormat") || saveImage(temp).equals("IOException")){
	  		fails++;
	  	}
	  	else{
	  	}
		  finalImage = saveImage(temp);
		  locals.add(finalImage);
		  passes++; 
		 // if(count > 3){
		//	   removeImage = destroyImage();
		//	   locals.remove(0);
		 // }
		  //System.out.println("after");
	  //}

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
		    	return("BadURL");
		    }
		    connection = (HttpURLConnection) url.openConnection();
		    if(connection == null){
		    	return("ConnectionException");
		    }
		    connection.setRequestProperty(
		    	    "User-Agent",
		    	    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		    image = ImageIO.read(connection.getInputStream());
		    if(image == null){
		    	return("ImageNull");
		    }
			name = url.getFile();
			destinationFile =  destinationFile + name.substring(name.lastIndexOf("/") + 1);
			if(name.substring(name.lastIndexOf(".") + 1).equals("jpg")){
				fileType = "jpg";
			}
			else if(name.substring(name.lastIndexOf(".") + 1).equals("png")){
				fileType = "png";
			}else{
				return("WrongFormat");
			}
			System.out.println("inside saveImage, filetype: " + fileType + " filename: " + destinationFile);
			
		    ImageIO.write(image, fileType, new File(destinationFile));
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("halp");
			return("IOException");
		}
  return destinationFile;
 }
}