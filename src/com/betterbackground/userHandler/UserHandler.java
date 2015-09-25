package com.betterbackground.userHandler;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.betterbackground.ddpclient.DDPClient;
import com.betterbackground.ddpclient.EmailAuth;
import com.betterbackground.ddpclient.test.TestConstants;
import com.betterbackground.ddpclient.Constants;


public class UserHandler {
	DDPClient ddp = null;
	
	public UserHandler(){
		try {
			ddp = new DDPClient(Constants.sMeteorHost, Constants.sMeteorPort);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean login(String email, String password){
        Object[] methodArgs = new Object[1];
        EmailAuth emailpass = new EmailAuth(email, password);
        methodArgs[0] = emailpass;
        ddp.call("login", methodArgs);
		
        //add callback
        
		return true;
	}
	
	public boolean createUser(String email, String password){		
		Map<String,Object> options = new HashMap<String,Object>();
		Object[] methodArgs = new Object[1];
        methodArgs[0] = options;
        options.put("email", email);
        options.put("password", password);
        ddp.call("createUser", methodArgs);
        
        //add callback
        
        return true;
	}
}
