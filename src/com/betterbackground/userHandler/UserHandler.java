package com.betterbackground.userHandler;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.betterbackground.ddpclient.DDPClient;
import com.betterbackground.ddpclient.DDPClient.DdpMessageField;
import com.betterbackground.ddpclient.DDPListener;
import com.betterbackground.ddpclient.EmailAuth;
import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.ddpclient.Constants;


public class UserHandler {
	public static void main(String[] args){
		UserHandler u = new UserHandler();
		u.login(TestConstants.sMeteorUsername, TestConstants.sMeteorPassword);
		while(!u.loggedIn){
			try {
				Thread.sleep(5000);
				System.out.println("Waiting for response...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	DDPClient ddp = null;
	
	public UserHandler(){
		try {
			ddp = new DDPClient(Constants.sMeteorHost, Constants.sMeteorPort);
			ddp.connect();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	boolean loggedIn = false;
	boolean userCreated = false;
	public boolean login(String email, String password){
        Object[] methodArgs = new Object[1];
        EmailAuth emailpass = new EmailAuth(email, password);
        methodArgs[0] = emailpass;
        ddp.call("login", methodArgs, new DDPListener() {
            @Override
			public void onResult(Map<String, Object> resultFields) {
                if (resultFields.containsKey(DdpMessageField.ERROR)) {
                    Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    String errorReason = (String) error.get("reason");
                    System.err.println("Login failure: " + errorReason);
                } else {
                	loggedIn = true;
                }
            }
        });
	
		return loggedIn;
	}
	
	public boolean createUser(String email, String password){		
		Map<String,Object> options = new HashMap<String,Object>();
		Object[] methodArgs = new Object[1];
        methodArgs[0] = options;
        options.put("email", email);
        options.put("password", password);
        ddp.call("createUser", methodArgs, new DDPListener() {
            @Override
			public void onResult(Map<String, Object> resultFields) {
                if (resultFields.containsKey(DdpMessageField.ERROR)) {
                    Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    String errorReason = (String) error.get("reason");
                    System.err.println("Create account failure: " + errorReason);
                } else {
                	userCreated = true;
                }
            }
        });
        
        return userCreated;
	}
}
