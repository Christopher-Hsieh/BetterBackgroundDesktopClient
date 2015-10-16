package com.betterbackground.userhandlertemp;

import java.net.URISyntaxException;

import org.json.simple.JSONObject;

import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userhandlertemp.Interfaces.LoginListener;
import com.betterbackground.userhandlertemp.Interfaces.MyChannelsListener;

public class WorkingExamples implements LoginListener, MyChannelsListener{
	static UserHandler userHandler;
	
	public static void main(String[] args) throws InterruptedException, URISyntaxException{
		userHandler = new UserHandler();
		WorkingExamples m = new WorkingExamples();
		
		//Add listeners for class
		userHandler.addLoginListener(m);
		userHandler.addMyChannelsListener(m);
		
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		
		Thread.sleep(500);
		
		userHandler.getMyChannels();
	}
	
	//returns: true/false
	@Override
	public void loginResult(boolean result) {
		System.out.println(result);
	}

	//returns: jsonObject/null
	@Override
	public void myChannelsResult(JSONObject jsonObject) {
		//Not currently working on meteor end
		System.out.println(jsonObject);
	}
	
}
