package com.betterbackground.userhandler;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.userhandler.Interfaces.GetUrlsListener;
import com.betterbackground.userhandler.Interfaces.LoginListener;
import com.betterbackground.userhandler.Interfaces.MyChannelsListener;

public class WorkingExamples implements LoginListener, MyChannelsListener, GetUrlsListener{
	static UserHandler userHandler;
	
	public static void main(String[] args) throws InterruptedException, URISyntaxException{
		userHandler = new UserHandler();
		WorkingExamples m = new WorkingExamples();
		
		//Add listeners for class
		userHandler.addLoginListener(m);
		userHandler.addMyChannelsListener(m);
		userHandler.addGetUrlsListener(m);
		
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		//subMyChannels is chained to login success in UserHandler
	}
	
	//returns: true/false
	@Override
	public void loginResult(boolean result) {
		System.out.println("Logged in: " + result);
	}

	//returns: map
	@Override
	public void myChannelsResult(Map<String, Object> channelsMap) {
		System.out.println(channelsMap);
		for (Entry<String, Object> channel : channelsMap.entrySet()) {
            @SuppressWarnings("unchecked")
			Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
            System.out.println("ID: " + channel.getKey());
			System.out.println("Title: " + channelFields.get("title"));
			System.out.println("Query: " + channelFields.get("query"));
			System.out.println("Creator: " + channelFields.get("creator"));
			System.out.println("Created At: " + channelFields.get("createdAt"));
			System.out.println("Updated At: " + channelFields.get("updatedAt"));     
			
			userHandler.getChannelUrls(channel.getKey());
		}
	}

	//returns: result=[{width=6637, height=3787, unescapedUrl=https://upload.wikimedia.org/.../galaxy.jpg, url=https://upload.wikimedia.org/.../galaxy.jpg}]
	@Override
	public void getUrlsResult(Object result) {
		//Not implemented
		System.out.println(result);
	}
		
}
