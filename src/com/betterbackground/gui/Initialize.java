package com.betterbackground.gui;

import java.net.URISyntaxException;

import com.betterbackground.backgroundManager.BackgroundManager;
import com.betterbackground.userhandler.UserHandler;

public class Initialize {
	
	static UserHandler userHandler;
	static BackgroundManager backgroundManager;
	
	static MainUI mainUI;
	static Login login;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		//Initialize
		login = Login.getInstance();
		mainUI = MainUI.getInstance();
		userHandler = UserHandler.getInstance();
		backgroundManager = new BackgroundManager();
		
		//Add listeners
		userHandler.addGetUrlsListener(backgroundManager);
		userHandler.addLoginListener(login);
		userHandler.addMyChannelsListener(mainUI);
		
		//Create the UI
		login.createLoginUI();
		login.setDefaultCloseOperation(login.DISPOSE_ON_CLOSE);
		login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
		
		//Attempt to connect to ddp client
		userHandler.connect();
	}

	public static void getUrls(String channelId) {
		userHandler.getChannelUrls(channelId);
	}
}
