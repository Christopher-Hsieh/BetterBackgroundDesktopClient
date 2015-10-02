import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
/*to do:
 * make it cycle through a folder, right now it is just passed images for testing
 */
public class ImageDownloader extends Thread {


	  String temp[];
	  int len; 
	  String destinationFile = "blah";
	  String imageUrl ="blah";
	  String finalImage = "";
	  
	ImageDownloader(String passedUrl){
		  //takes the last part of the url and makes it the name
		  temp = passedUrl.split("/");
		  imageUrl = passedUrl;
		  len = temp.length;
		  destinationFile = "\"" + temp[len-1];
	}
	
  public void run(){
	  finalImage = saveImage();
  }
  
  public String saveImage(){
	  URL url; 
	  InputStream is;
	  OutputStream os;
  try{
	   url = new URL(imageUrl);
  }catch( MalformedURLException e){
	  return "Bad URL";//this way we know it's a bad url
  }
  try{
	  is = url.openStream();
  }catch(Exception e){
	  System.out.println("Something weird is happening here");
	  return "Bad Stream";
  }
  try{
	  os = new FileOutputStream(destinationFile);
  }catch(Exception e){
	  System.out.println("Something is happening with the destination file");
	  return "Bad Destination";
  }
  //add different things to return depending on the file
  //to do just read in one byte at a time
  byte[] b = new byte[900000];
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
  return destinationFile;
 }

}