import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;


public class FolderTest {

	@Test
	public void testBackgroundManager() {
		BackgroundManager bm = new BackgroundManager();
		if (Files.exists(Paths.get("C:\\Users\\Public\\BetterBackground"))) {
			
		}else{
			fail("Not yet implemented");
		}
	}

}
