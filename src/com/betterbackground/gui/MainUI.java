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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import com.betterbackground.userhandler.Interfaces.MyChannelsListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;


public class MainUI extends JFrame implements MyChannelsListener {
	
	private static final long serialVersionUID = 1L;


	SystemTray tray;
	private java.awt.Image img;
	TrayIcon trayIcon;
	private static PopupMenu menu;
	
	// List of current channels we display.
	ArrayList<Map<String, Object>> currentChannels = new ArrayList<>();
	
	Map<String, Object> channels;
	
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
	
	public void createChannelListener() {
		Initialize.userHandler.addMyChannelsListener(Login.mainUI);
	}
	
	public void addToggleBtn(String channelName) {
		if (column == 2) {
			row++;
			column = 0;
		}
		
		JToggleButton channelBtn = new JToggleButton(channelName);
		
		channelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Channel Button is toggled ON
				if (channelBtn.isSelected()) {
					for (Entry<String, Object> channel : channels.entrySet()) {
						@SuppressWarnings("unchecked")
						Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
						if (channelBtn.getText() == channelFields.get("title").toString()) {
							Initialize.userHandler.getChannelUrls(channel.getKey());
							break;
						}
					}
				}
				
				// Else the Channel Button is toggled OFF
				else {
					
				}
			}
		});
		
		constraints.gridx = column++;
		constraints.gridy = row;
		panel.add(channelBtn, constraints);
		
		panel.revalidate();
		validate();
		panel.repaint();
		
	}
	
	JPanel panel;
	GridBagConstraints constraints; 
	int column;
	int row;
	
	public MainUI() {
		super("Better Background Main UI");

		// Set up the system tray
		//setupSystemTray();
		
		// create a new panel w. GridBagLayout
		panel = new JPanel(new GridBagLayout());

		constraints = new GridBagConstraints();
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		column = 0;
		row = 0;
	
		// set border 
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Channel Selection Menu"));

		// add the panel 
		getContentPane().add(panel);
		
		GridBagConstraints gbc_tglbtn1 = new GridBagConstraints();
		gbc_tglbtn1.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtn1.gridx = 0;
		gbc_tglbtn1.gridy = 0;
		panel.add(tglbtn1, gbc_tglbtn1);
		GridBagConstraints gbc_tglbtn2 = new GridBagConstraints();
		gbc_tglbtn2.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtn2.gridx = 1;
		gbc_tglbtn2.gridy = 0;
		panel.add(tglbtn2, gbc_tglbtn2);
		GridBagConstraints gbc_tglbtn3 = new GridBagConstraints();
		gbc_tglbtn3.gridx = 2;
		gbc_tglbtn3.gridy = 0;
		panel.add(tglbtn3, gbc_tglbtn3);

		tglbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Channel Button is toggled ON
				if (tglbtn1.isSelected()) {
					for (Entry<String, Object> channel : channels.entrySet()) {
						@SuppressWarnings("unchecked")
						Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
						if (tglbtn1.getText() == channelFields.get("title").toString()) {
							Initialize.userHandler.getChannelUrls(channel.getKey());
							break;
						}
					}
				}
			}
		});
		
		tglbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Channel Button is toggled ON
				if (tglbtn2.isSelected()) {
					for (Entry<String, Object> channel : channels.entrySet()) {
						@SuppressWarnings("unchecked")
						Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
						if (tglbtn2.getText() == channelFields.get("title").toString()) {
							Initialize.userHandler.getChannelUrls(channel.getKey());
							break;
						}
					}
				}
			}
		});
		
		tglbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Channel Button is toggled ON
				if (tglbtn3.isSelected()) {
					for (Entry<String, Object> channel : channels.entrySet()) {
						@SuppressWarnings("unchecked")
						Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
						if (tglbtn3.getText() == channelFields.get("title").toString()) {
							Initialize.userHandler.getChannelUrls(channel.getKey());
							break;
						}
					}
				}
			}
		});
		pack();
		setLocationRelativeTo(null);;
	}

	public void createMainUI() {
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
	
	int button = 1;
	private final JToggleButton tglbtn1 = new JToggleButton("New toggle button");
	private final JToggleButton tglbtn2 = new JToggleButton("New toggle button");
	private final JToggleButton tglbtn3 = new JToggleButton("New toggle button");
	
	public void setChannelBtns(String channel) {
		if (button == 1) {
			tglbtn1.setText(channel);
			button++;
		} else if (button == 2) {
			tglbtn2.setText(channel);
			button++;
		} else {
			tglbtn3.setText(channel);
		}
		panel.revalidate();
		validate();
		panel.repaint();
	}

	@Override
	public void myChannelsResult(Map<String, Object> channelsMap) {
		// Clear out the list to enter the new ones
		currentChannels.clear();
		
		channels = channelsMap;
				
		for (Entry<String, Object> channel : channelsMap.entrySet()) {
            @SuppressWarnings("unchecked")
			Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
			//addToggleBtn(channelFields.get("title").toString());  
            setChannelBtns(channelFields.get("title").toString());
			currentChannels.add(channelFields);
		}
		
	}
}