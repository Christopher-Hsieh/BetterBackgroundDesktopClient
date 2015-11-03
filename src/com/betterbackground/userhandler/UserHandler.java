package com.betterbackground.userhandler;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.betterbackground.ddpclient.DDPClient;
import com.betterbackground.ddpclient.DDPClient.DdpMessageField;
import com.betterbackground.ddpclient.DDPClientObserver;
import com.betterbackground.ddpclient.DDPClientObserver.DDPSTATE;
import com.betterbackground.userhandler.Interfaces.GetUrlsListener;
import com.betterbackground.userhandler.Interfaces.LoginListener;
import com.betterbackground.userhandler.Interfaces.MyChannelsListener;
import com.betterbackground.userhandler.Interfaces.UpdateListener;
import com.betterbackground.ddpclient.DDPListener;
import com.betterbackground.ddpclient.UsernameAuth;
import com.betterbackground.ddpclient.Constants;


public class UserHandler implements UpdateListener {
	private ArrayList<LoginListener> loginListeners = new ArrayList<LoginListener>();
	private ArrayList<MyChannelsListener> myChannelsListeners = new ArrayList<MyChannelsListener>();
	private ArrayList<GetUrlsListener> getUrlsListeners = new ArrayList<GetUrlsListener>();
	public DDPClient ddp = null;
	public DDPClientObserver obs = null;
	public Map<String, Object> myChannels;

	public UserHandler() throws URISyntaxException, InterruptedException{
		int connectAttempts = 0;
		myChannels = new HashMap<String, Object>();
		ddp = new DDPClient(Constants.sMeteorHost, Constants.sMeteorPort);
		obs = new DDPClientObserver();
		obs.addUpdateListener(this);
		ddp.addObserver(obs);
		ddp.connect();
			
		//add timeout after so many attempts
		//replace this using state change
		while(obs.mDdpState != DDPSTATE.Connected){
			Thread.sleep(1000);
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
	
	public void addGetUrlsListener(GetUrlsListener listener){
		getUrlsListeners.add(listener);
	}
	
	public void disconnect(){
		ddp.disconnect();
	}
	
	/*
	 * This function gets notified of any state change in the observer
	 */
	@Override
	public void observerUpdated(Object msg) {
		//check if channels collection changes. If so then notify listeners.
		if(obs.mCollections.containsKey("channels") && !myChannels.equals(obs.mCollections.get("channels"))){
			myChannels = obs.mCollections.get("channels");
			for (MyChannelsListener l : myChannelsListeners){
				l.myChannelsResult(myChannels);
			}
		}
	}

	public void login(String username, String password){
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
        			//subscribe to my channels
        			subMyChannels();
        		}
        	}
        });
	}
	
	public void subMyChannels(){
		ddp.subscribe("myChannels", new Object[] {}, obs);
	}
	
	public void getChannelUrls(String channelId) {
        Object[] methodArgs = new Object[1];
        methodArgs[0] = channelId;
        ddp.call("/channels/getUrls", methodArgs, obs);
        
        new Thread(new Runnable() {
            public void run(){
            	try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            	
                JSONObject jsonObject = obs.getUrlResult();
                if(jsonObject != null){
	        		JSONArray resultArray = jsonObject.getJSONArray(DdpMessageField.RESULT);
	        		String[] urls = new String[resultArray.length()];
	        		
	        		for(int i = 0; i < resultArray.length(); i++){
	        			urls[i] = resultArray.getJSONObject(i).getString("unescapedUrl");
	        		}
	        		
	        		for (GetUrlsListener l : getUrlsListeners)
	                    l.getUrlsResult(urls);
                }
            }
        }).start();
	}
}
