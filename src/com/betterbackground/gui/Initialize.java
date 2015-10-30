package com.betterbackground.gui;

import java.net.URISyntaxException;

import com.betterbackground.userhandler.UserHandler;

public class Initialize {
	
	static UserHandler userhandler;
	
	
	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		// Setup UI
		Login login = new Login();
		
		userhandler = new UserHandler();
		
		// Actually Create the UI
		login.createLoginUI();
		
		// Create listener to check credentials for Login
		try {
			login.createLoginListener(login, userhandler);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
