package com.betterbackground.userhandler;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userhandler.Interfaces.LoginListener;
import com.betterbackground.userhandler.Interfaces.MyChannelsListener;

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
		System.out.println("Logged in: " + result);
	}

	//returns: map/null
	@Override
	public void myChannelsResult(Map<String, Object> channelsMap) {
		for (Entry<String, Object> channel : channelsMap.entrySet()) {
            @SuppressWarnings("unchecked")
			Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
			System.out.println("Title:" + channelFields.get("title"));
			System.out.println("Query:" + channelFields.get("query"));
			System.out.println("Creator:" + channelFields.get("creator"));
			System.out.println("Created At:" + channelFields.get("createdAt"));
			System.out.println("Updated At:" + channelFields.get("updatedAt"));            
		}
	}
	
}
