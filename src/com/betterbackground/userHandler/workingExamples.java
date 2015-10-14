package com.betterbackground.userhandler;

import java.net.URISyntaxException;

import com.betterbackground.ddpclient.DDPClientObserver;
import com.betterbackground.ddpclient.test.TestConstants;

public class workingExamples implements LoginListener{
	public static void main(String[] args) throws InterruptedException, URISyntaxException{
		UserHandler userHandler = new UserHandler();
		workingExamples m = new workingExamples();
		userHandler.addLoginListener(m);
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
	}
	
	@Override
	public void loginResult(boolean result) {
		// TODO Auto-generated method stub
		System.out.println(result);
	}
}
