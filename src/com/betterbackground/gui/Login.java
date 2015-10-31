package com.betterbackground.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.Map;
import java.awt.event.ActionEvent;

import com.betterbackground.userhandler.UserHandler;
import com.betterbackground.userhandler.Interfaces.LoginListener;
import com.betterbackground.userhandler.Interfaces.MyChannelsListener;

public class Login extends JFrame implements LoginListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JTextField textUsername = new JTextField(20);
	private JPasswordField fieldPassword = new JPasswordField(20);
	public JButton buttonLogin = new JButton("Login");

	private JLabel status = new JLabel(" ");

	public Login() {
		//super("Better Background Login");

<<<<<<< HEAD
		// create a new panel w. GridBagLayout
		JPanel newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components 
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(labelUsername, constraints);

		constraints.gridx = 1;
		newPanel.add(textUsername, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		newPanel.add(labelPassword, constraints);

		constraints.gridx = 1;
		newPanel.add(fieldPassword, constraints);

		constraints.gridx = 2;
		newPanel.add(status, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;

		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initialize.userHandler.login(textUsername.getText(), fieldPassword.getPassword().toString());
			}
		});
		newPanel.add(buttonLogin, constraints);

		// set border 
		newPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Login Panel"));

		// add the panel 
		getContentPane().add(newPanel);

		pack();
		setLocationRelativeTo(null);;

=======
				// create a new panel w. GridBagLayout
				JPanel newPanel = new JPanel(new GridBagLayout());

				GridBagConstraints constraints = new GridBagConstraints();
				constraints.anchor = GridBagConstraints.WEST;
				constraints.insets = new Insets(10, 10, 10, 10);

				// add components 
				constraints.gridx = 0;
				constraints.gridy = 0;
				newPanel.add(labelUsername, constraints);

				constraints.gridx = 1;
				newPanel.add(textUsername, constraints);

				constraints.gridx = 0;
				constraints.gridy = 1;
				newPanel.add(labelPassword, constraints);

				constraints.gridx = 1;
				newPanel.add(fieldPassword, constraints);
			
				constraints.gridx = 2;
				newPanel.add(status, constraints);
				
				constraints.gridx = 0;
				constraints.gridy = 2;
				constraints.gridwidth = 2;
				constraints.anchor = GridBagConstraints.CENTER;
				
				buttonLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String password = new String(fieldPassword.getPassword());
						Initialize.userhandler.login(textUsername.getText(), password);
					}
				});
				newPanel.add(buttonLogin, constraints);

				// set border 
				newPanel.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(), "Login Panel"));

				// add the panel 
				getContentPane().add(newPanel);

				pack();
				setLocationRelativeTo(null);;
				
>>>>>>> refs/remotes/origin/guiwork
	}

	public void createLoginUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	public void createLoginListener(Login listener, UserHandler userhandler) throws URISyntaxException, InterruptedException {
		userhandler.addLoginListener(listener);
	}


	// Does nothing for login. TODO Take out once login listener is done.
	public void myChannelsResult(JSONObject jsonObject) {
		// TODO Auto-generated method stub
	}

	static MainUI mainUI;

	// Login result is returned. Handle the case accordingly
	@Override
	public void loginResult(boolean result) {
		if(result) {
			dispose();
			mainUI = new MainUI();
			mainUI.createMainUI();
		} else {
			status.setText("Login failed, please try again.");
		}
	}
}