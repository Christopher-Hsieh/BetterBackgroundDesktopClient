package com.betterbackground.userhandler.test;

import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userhandler.UserHandler;
import com.betterbackground.userhandler.Interfaces.LoginListener;

import junit.framework.TestCase;

public class TestUserHandler extends TestCase implements LoginListener {
	public void testLogin() throws Exception {
		UserHandler userHandler = new UserHandler();
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
	}

	@Override
	public void loginResult(boolean result) {
		// TODO Auto-generated method stub
		System.out.println("HERE");
	}
}
