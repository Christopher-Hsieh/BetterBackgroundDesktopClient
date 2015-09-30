package userhandler.test;

import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userHandler.UserHandler;
import java.util.Observable;

import junit.framework.TestCase;

public class TestUserHandler extends TestCase {
	//Test not working with observer
	public void testLogin() throws Exception {
		UserHandler userHandler = new UserHandler();
		
		userHandler.addObserver((Observable obj, Object arg) -> { 
			assertEquals(arg, true);
        });
		
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
	}
}
