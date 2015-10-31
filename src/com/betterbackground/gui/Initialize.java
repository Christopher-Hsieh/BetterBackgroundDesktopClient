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
	
	public static void disposeLogin() {
		login.dispose();
	}
}
