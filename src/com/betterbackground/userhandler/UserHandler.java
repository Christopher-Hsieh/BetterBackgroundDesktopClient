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
import com.betterbackground.gui.Login;
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
	private Login loginInstance;
	private static UserHandler instance;
	public DDPClient ddp = null;
	public DDPClientObserver obs = null;
	public Map<String, Object> myChannels;
	public Map<String, Object> myUsers;

	public static UserHandler getInstance(){
		if(instance == null){
			instance = new UserHandler();
		}
		return instance;
	}
	
	public UserHandler(){
		loginInstance = Login.getInstance();
		myChannels = new HashMap<String, Object>();
		myUsers = new HashMap<String, Object>();
		try {
			ddp = new DDPClient(Constants.sMeteorHost, Constants.sMeteorPort);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		obs = new DDPClientObserver();
		obs.addUpdateListener(this);
		ddp.addObserver(obs);
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
	
	public void connect(){
		ddp.connect();
	}
	
	/*
	 * This function gets notified of any state change in the observer
	 */
	@Override
	public void observerUpdated(Object msg) {
		//check if channels collection changes. If so then notify listeners.
		if(obs.mCollections.containsKey("channels") && !myChannels.equals(obs.mCollections.get("channels"))){
			//get new channels added
			Map<String, Object> recentlyAdded = getDifference(myChannels, obs.mCollections.get("channels"));
			//update current channels
			myChannels = new HashMap<String, Object>(obs.mCollections.get("channels"));
			for (MyChannelsListener l : myChannelsListeners){
				l.myChannelsResult(recentlyAdded);
			}
		} 
		
		else {
			//Update occurred on Meteor side
			
			@SuppressWarnings("unchecked")
			Map<String, Object> jsonFields = (Map<String, Object>) msg;
			System.out.println(jsonFields);
			if(jsonFields.containsValue("changed")){
				ddp.subscribe("myChannels", new Object[] {}, obs);
//				System.out.println("---------UPDATE----------");
//				System.out.println(msg);
//				System.out.println("--------END UPDATE ------");
			}
		}
	}

	public void login(String username, String password){
		String status;
		
		if(obs.mDdpState != DDPSTATE.Connected){
			//attempt to connect
			connect();
		}
		
		if(obs.mDdpState != DDPSTATE.Connected){
			status = "Error connecting to meteor server";
			loginInstance.updateStatus(status);
			System.err.println(status);
		}
		
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
        
        ddp.call("/channels/getUrls", methodArgs, new DDPListener(){
			@Override
        	public void onResult(Map<String, Object> resultFields) {
        		if(resultFields.containsKey("error")){
					@SuppressWarnings("unchecked")
					Map<String, Object> error = (Map<String, Object>) resultFields.get(DdpMessageField.ERROR);
                    System.err.println("login failure: " + (String) error.get("reason"));
        		} else {
        			JSONObject jsonObject = new JSONObject(resultFields);
        			JSONArray resultArray = jsonObject.getJSONArray(DdpMessageField.RESULT);
        			String[] urls = new String[resultArray.length()];
        			
        			for(int i = 0; i < resultArray.length(); i++){
        				urls[i] = resultArray.getJSONObject(i).getString("unescapedUrl");
        			}
        			
        			for (GetUrlsListener l : getUrlsListeners)
        	            l.getUrlsResult(urls);
        		}
        	}
        });
	}
	
	Map<String, Object> getDifference(Map<String, Object> oldMap, Map<String, Object> newMap){
		Map<String, Object> diffMap = new HashMap<String, Object>();
		
		diffMap.putAll(oldMap);
		diffMap.putAll(newMap);
		
		diffMap.entrySet().removeAll(oldMap.entrySet());

		return diffMap;
	}
}
