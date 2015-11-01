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
	  
	ImageDownloader(int count, String someURLS[]){
		this.count = count;
		fails = 0;
		passes = 0;
		passedURLS = someURLS;
	}
	
	ImageDownloader(){
		
	}
	
  public void run(){
	  while(true){
		  //System.out.println("1");
		  while(count < passedURLS.length){
			  String temp;
			  System.out.println("count: " + count + "length: " + passedURLS.length);
		  	try{
		  		System.out.println("LOOKING FOR:  "+ passedURLS[count]);
		  		temp = passedURLS[count];
		  	}catch(Exception e){
		  		temp = "haha";
		  	}
		//  }else{
		  System.out.println(temp);
		  	if(temp.equals("haha")){
		  		System.out.println("ITS NULL");
		  		finalImage = "haha";
		  		locals.add("haha");
		  	}else if(temp.equals("BadURL") || temp.equals("ConnectionException") || temp.equals("ImageNull") || 
		  			temp.equals("WrongFormat") || temp.equals("IOException")){
		  		System.out.println("BAD");
		  		fails++;
		  	}
		  	else{

			  finalImage = saveImage(temp);
			  System.out.println("temp");
			  locals.add(finalImage);
			  passes++; 
		  	}
		  	count++;
		  	System.out.println("count");
		  }
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
	  System.out.println("IN HERE LOOKING FOR: " + imageUrl);
	  if (imageUrl.equals(null)){
		  System.out.println("hey");
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
	  System.out.println("hey");
  return destinationFile;
 }
}