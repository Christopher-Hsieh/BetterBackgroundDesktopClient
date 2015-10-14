package com.betterbackground.gui;

import java.awt.AWTException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.eclipse.swt.graphics.Image;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton buttonChannel1 = new JButton("Channel 1");
	private JButton buttonChannel2 = new JButton("Channel 2");
	private JButton buttonChannel3 = new JButton("Channel 3");

	SystemTray tray;
	private java.awt.Image img;
	TrayIcon trayIcon;
	private static PopupMenu menu;
	
	public void setupSystemTray() {
		if(SystemTray.isSupported()){
            System.out.println("system tray supported");
            tray = SystemTray.getSystemTray();
		}
		img = Toolkit.getDefaultToolkit().getImage("C:/Users/Chris/BetterBackgroundDesktopClient/src/com/betterbackground/gui/logo.png");
		
		menu = new PopupMenu();
        MenuItem item1 = new MenuItem("Close app");
        item1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        menu.add(item1);
        

        trayIcon = new TrayIcon(img, "Better Background", menu);
        
        try {
        	tray.add(trayIcon);
        } catch(AWTException e) {
        	System.out.println(e.getMessage());
        }
        
        
        
	}
	
	public MainUI() {
		super("Better Background Main UI");

		// Set up the system tray
		setupSystemTray();

		
		// create a new panel w. GridBagLayout
		JPanel newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
	
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(buttonChannel1, constraints);

		constraints.gridx = 1;
		newPanel.add(buttonChannel2, constraints);

		constraints.gridx = 2;
		newPanel.add(buttonChannel3, constraints);
	

		// set border 
		newPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Channel Selection Menu"));

		// add the panel 
		getContentPane().add(newPanel);

		pack();
		setLocationRelativeTo(null);;
	}

	public static void createMainUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		MainUI mainUI = new MainUI();
		mainUI.setVisible(true);
		mainUI.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		// Add show App button
        MenuItem item2 = new MenuItem("Show");
        item2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainUI.setVisible(true);
        	}
        });
        menu.add(item2);

	}
}