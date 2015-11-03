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
		login = new Login();
		userHandler = new UserHandler();
		backgroundManager = new BackgroundManager();
		
		//Add listeners
		userHandler.addGetUrlsListener(backgroundManager);
		
		// Actually Create the UI
		login.createLoginUI();
		login.setDefaultCloseOperation(login.DISPOSE_ON_CLOSE);
		login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
		
		// Create listener to check credentials for Login
		try {
			login.createLoginListener(login, userHandler);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "static-access"})
	public static void createMainUI() {
		login.destroy(login);
		if (mainUI == null) {
		
			mainUI = new MainUI();
			mainUI.createMainUI(mainUI);
			mainUI.setDefaultCloseOperation(mainUI.DISPOSE_ON_CLOSE);
			mainUI.setDefaultCloseOperation(mainUI.EXIT_ON_CLOSE);
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
	}
	
	public static void getUrls(String channelId) {
		userHandler.getChannelUrls(channelId);
	}
}
