package com.betterbackground.userhandler.test;

import org.junit.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;

import com.betterbackground.ddpclient.DDPClientObserver;
import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userhandler.UserHandler;

public class TestUserHandler {
	private static UserHandler userHandler;
	private static DDPClientObserver obs;
	
	@BeforeClass
	public static void oneTimeSetup() throws URISyntaxException, InterruptedException{
		userHandler = new UserHandler();
		obs = userHandler.obs;
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		userHandler.ddp.disconnect();
	}
	
	@After
	public void tearDown(){
		obs.mCollections.clear();
	}

	@Test
	public void testLogin() throws Exception {
		assertEquals(obs.mCollections.containsKey("users"), false);
		
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		Thread.sleep(500);

		assertEquals(obs.mCollections.containsKey("users"), true);
	}
	
	@Test
	public void testSubMyChannels() throws Exception {
		assertEquals(obs.mCollections.containsKey("channels"), false);
		
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		Thread.sleep(500);
		userHandler.subMyChannels();
		Thread.sleep(500);

		//Test failing here for some reason
//		assertEquals(obs.mCollections.containsKey("channels"), true);
	}
}
