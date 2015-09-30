package com.betterbackground.userHandler;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import com.betterbackground.ddpclient.DDPClient;
import com.betterbackground.ddpclient.DDPClientObserver;
import com.betterbackground.ddpclient.DDPClientObserver.DDPSTATE;
import com.betterbackground.ddpclient.DDPListener;
import com.betterbackground.ddpclient.EmailAuth;
import com.betterbackground.ddpclient.Constants;


public class UserHandler extends Observable {
	DDPClient ddp = null;
	DDPClientObserver obs = null;
	
	public UserHandler() throws URISyntaxException, InterruptedException{
		ddp = new DDPClient(Constants.sMeteorHost, Constants.sMeteorPort);
		obs = new DDPClientObserver();
		ddp.addObserver(obs);
		ddp.connect();
			
		//add timeout after so many attempts
		while(obs.mDdpState != DDPSTATE.Connected){
			Thread.sleep(500);
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
        			notifyObservers(false);
        		} else {
        			notifyObservers(true);
        		}
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
        			notifyObservers(false);
        		} else {
        			notifyObservers(true);
        		}
        	}
        });
	}
	
	public void getLatestChannels(){
		ddp.subscribe("latestChannels", new Object[] {}, new DDPListener(){
        	@Override
        	public void onResult(Map<String, Object> resultFields) {
        		notifyObservers(resultFields);
        	}
        });
	}
}
