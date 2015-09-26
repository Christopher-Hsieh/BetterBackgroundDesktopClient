import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageDownloader {

 public static int saveImage(String imageUrl, String destinationFile) throws IOException {
  URL url = new URL(imageUrl);
  InputStream is = url.openStream();
  OutputStream os = new FileOutputStream(destinationFile);
  //add different things to return depending on the file
  byte[] b = new byte[900000];
  int length;

  while ((length = is.read(b)) != -1) {
   os.write(b, 0, length);
  }

  is.close();
  os.close();
  return 0;
 }

}