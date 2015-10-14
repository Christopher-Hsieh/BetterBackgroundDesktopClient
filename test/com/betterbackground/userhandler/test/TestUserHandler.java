package com.betterbackground.userhandler.test;

import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userhandler.UserHandler;

import junit.framework.TestCase;

public class TestUserHandler extends TestCase {
	public void testLogin() throws Exception {
		UserHandler userHandler = new UserHandler();
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		
		Thread.sleep(500);
		
		assertEquals(UserHandler.loggedIn, true);
	}
	
	public void testCreateAccount() throws Exception {
		UserHandler userHandler = new UserHandler();
		userHandler.createUser(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		
		Thread.sleep(500);
		
		assertEquals(UserHandler.accountCreated, false);
	}
}
