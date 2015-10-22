package com.betterbackground.userHandler;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import org.json.simple.JSONObject;

import com.betterbackground.ddpclient.DDPClient;
import com.betterbackground.ddpclient.DDPClient.DdpMessageField;
import com.betterbackground.ddpclient.DDPClientObserver;
import com.betterbackground.ddpclient.DDPClientObserver.DDPSTATE;
import com.betterbackground.userHandler.Interfaces.LoginListener;
import com.betterbackground.userHandler.Interfaces.MyChannelsListener;
import com.betterbackground.ddpclient.DDPListener;
import com.betterbackground.ddpclient.UsernameAuth;
import com.betterbackground.ddpclient.Constants;


public class UserHandler extends Observable {
	private ArrayList<LoginListener> loginListeners = new ArrayList<LoginListener>();
	private ArrayList<MyChannelsListener> myChannelsListeners = new ArrayList<MyChannelsListener>();
	DDPClient ddp = null;
	DDPClientObserver obs = null;
	public Map<String, Object> myChannels;

	public UserHandler() throws URISyntaxException, InterruptedException{
		int connectAttempts = 0;
		ddp = new DDPClient(Constants.sMeteorHost, Constants.sMeteorPort);
		obs = new DDPClientObserver();
		ddp.addObserver(obs);
		ddp.connect();
			
		//add timeout after so many attempts
		while(obs.mDdpState != DDPSTATE.Connected){
			Thread.sleep(1000);
			System.err.println("Attempting to connect...");
			if(connectAttempts++ == 20){
				System.err.println("Too many failed attempts");
				break;
			}
		}
	}
	
	public void addLoginListener(LoginListener listener){
		loginListeners.add(listener);
	}
	
	public void addMyChannelsListener(MyChannelsListener listener){
		myChannelsListeners.add(listener);
	}

	public void login(String username, String password) throws InterruptedException{
//		DDPClientObserver mObs = new DDPClientObserver();
        Object[] methodArgs = new Object[1];
        UsernameAuth userpass = new UsernameAuth(username, password);
        methodArgs[0] = userpass;
        
        ddp.call("login", methodArgs, new DDPListener(){
        	@Override
        	public void onResult(Map<String, Object> resultFields) {
        		if(resultFields.containsKey("error")){
        			@SuppressWarnings("unchecked")
					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    System.err.println("login failure: " + (String) error.get("reason"));
        			for (LoginListener l : loginListeners)
        	            l.loginResult(false);
        		} else {
        			for (LoginListener l : loginListeners)
        	            l.loginResult(true);
        		}
        	}
        });
	}
	
	//JSON: Channel Objects
	//Title
	//Person Created
	//Query
	//Store entire JSON object to pass back later when toggled
	public void getMyChannels(){
			ddp.subscribe("myChannels", new Object[] {}, new DDPListener(){
	        	@Override
	        	public void onResult(Map<String, Object> resultFields) {
	        		if(resultFields.containsKey("error")){
	        			@SuppressWarnings("unchecked")
						Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
	                    System.err.println("myChannels failure: " + (String) error.get("reason"));
	                    for (MyChannelsListener l : myChannelsListeners)
	        	            l.myChannelsResult(null);
	        		} else {
	        			for (MyChannelsListener l : myChannelsListeners)
	        	            l.myChannelsResult(new JSONObject((Map<String, Object>) resultFields));
	        		}
	        	}
			});
	}
	
//	public void getLatestChannels(int limit){
//		Object[] methodArgs = new Object[1];
//		methodArgs[0] = limit;
//		ddp.subscribe("latestChannels", methodArgs, new DDPListener(){
//        	@Override
//        	public void onResult(Map<String, Object> resultFields) {
//        		if(resultFields.containsKey("error")){
//        			@SuppressWarnings("unchecked")
//					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
//                    System.err.println("latestChannels failure: " + (String) error.get("reason"));
//        		} else {
//        			//send resultFields to background changer
//        		}
//        	}
//        });
//	}
}
