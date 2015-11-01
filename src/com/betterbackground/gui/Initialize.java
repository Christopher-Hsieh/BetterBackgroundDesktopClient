package com.betterbackground.gui;

import java.net.URISyntaxException;

import com.betterbackground.backgroundManager.BackgroundManager;
import com.betterbackground.userhandler.UserHandler;

public class Initialize {
	
	static UserHandler userHandler;
	static BackgroundManager backgroundManager;
	
	static Login login;
	
	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		//Initialize
		login = new Login();
		userHandler = new UserHandler();
		backgroundManager = new BackgroundManager();
		
		//Add listeners
		userHandler.addGetUrlsListener(backgroundManager);
		
		// Actually Create the UI
		login.createLoginUI();
		
		// Create listener to check credentials for Login
		try {
			login.createLoginListener(login, userHandler);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createMainUI() {
		login.setVisible(false);
		MainUI mainUI = new MainUI();
		mainUI.createMainUI();
		mainUI.setVisible(true);
		try {
			mainUI.addMyChannelsListener(mainUI, userHandler);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getUrls(String channelId) {
		userHandler.getChannelUrls(channelId);
	}
}
