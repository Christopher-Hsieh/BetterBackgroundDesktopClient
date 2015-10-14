package com.betterbackground.userhandler;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import com.betterbackground.ddpclient.DDPClient;
import com.betterbackground.ddpclient.DDPClient.DdpMessageField;
import com.betterbackground.ddpclient.DDPClientObserver;
import com.betterbackground.ddpclient.DDPClientObserver.DDPSTATE;
import com.betterbackground.ddpclient.DDPListener;
import com.betterbackground.ddpclient.EmailAuth;
import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.ddpclient.Constants;


public class UserHandler extends Observable {
	DDPClient ddp = null;
	DDPClientObserver obs = null;
	public static boolean loggedIn;
	public static boolean accountCreated;
	public Map<String, Object> myChannels;
	
	public static void main(String[] args) throws InterruptedException, URISyntaxException{
		UserHandler userHandler = new UserHandler();
		
		userHandler.addObserver((Observable obj, Object arg) -> { 
			System.out.println(loggedIn);
        });
		
		userHandler.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
	}
	
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

	public void login(String email, String password) throws InterruptedException{
        Object[] methodArgs = new Object[1];
        EmailAuth emailpass = new EmailAuth(email, password);
        methodArgs[0] = emailpass;
        ddp.call("login", methodArgs, new DDPListener(){
        	@Override
        	public void onResult(Map<String, Object> resultFields) {
        		setChanged();
        		if(resultFields.containsKey("error")){
        			@SuppressWarnings("unchecked")
					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    System.err.println("login failure: " + (String) error.get("reason"));
        			loggedIn = false;
        		} else {
        			loggedIn = true;
        		}
        		setChanged();
        		notifyObservers();
        	}
        });
	}
	
	public void createUser(String email, String password) throws InterruptedException{		
		Map<String,Object> options = new HashMap<String,Object>();
		Object[] methodArgs = new Object[1];
        methodArgs[0] = options;
        options.put("email", email);
        options.put("password", password);
        ddp.call("createUser", methodArgs, new DDPListener(){
        	@Override
        	public void onResult(Map<String, Object> resultFields) {
        		if(resultFields.containsKey("error")){
        			@SuppressWarnings("unchecked")
					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    System.err.println("createUser failure: " + (String) error.get("reason"));
        			accountCreated = false;
        		} else {
        			accountCreated = true;
        		}
        		setChanged();
        		notifyObservers();
        	}
        });
	}
	
	public void getMyChannels(){
		ddp.subscribe("myChannels", new Object[] {}, new DDPListener(){
        	@Override
        	public void onResult(Map<String, Object> resultFields) {
        		if(resultFields.containsKey("error")){
        			@SuppressWarnings("unchecked")
					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    System.err.println("myChannels failure: " + (String) error.get("reason"));
        		} else {
        			myChannels = resultFields;
            		setChanged();
            		notifyObservers(resultFields);
        		}
        	}
        });
	}
	
	public void getLatestChannels(int limit){
		Object[] methodArgs = new Object[1];
		methodArgs[0] = limit;
		ddp.subscribe("latestChannels", methodArgs, new DDPListener(){
        	@Override
        	public void onResult(Map<String, Object> resultFields) {
        		if(resultFields.containsKey("error")){
        			@SuppressWarnings("unchecked")
					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    System.err.println("latestChannels failure: " + (String) error.get("reason"));
        		} else {
        			//send resultFields to background changer
        		}
        	}
        });
	}
}
