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

import com.betterbackground.userhandler.UserHandler;
import com.betterbackground.userhandler.Interfaces.MyChannelsListener;

import java.awt.event.ActionListener;
import java.net.URISyntaxException;
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
	
	ArrayList<ToggableChannels> toggableChannelsList = new ArrayList<>();
	
	public class ToggableChannels {
		String title;
		String id;
		
		ToggableChannels(String title, String id) {
			this.title = title;
			this.id = id;
		}
	}
	
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
	
	public void addMyChannelsListener(MainUI listener, UserHandler userhandler) throws URISyntaxException, InterruptedException {
		userhandler.addMyChannelsListener(listener);
	}
	
	public void addToggleBtn(String channelName) {
		if (column == 2) {
			row++;
			column = 0;
		}
		
		JToggleButton channelBtn = new JToggleButton(channelName);
		
		channelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToggableURL(channelBtn);
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
		setupSystemTray();
		
		// create a new panel w. GridBagLayout
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[] {200};
		gbl_panel.columnWidths = new int[] {100};
		panel = new JPanel(gbl_panel);

		//panel.setBounds(0, 200, 200, 200);
		
		constraints = new GridBagConstraints();
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(0, 0, 0, 5);
		
		column = 0;
		row = 0;
	
		// set border 
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Channel Selection Menu"));

		//panel.setSize(500, 200);
		
		// add the panel 
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);;
	}
	
	public void sendToggableURL(JToggleButton jtglbtn) {
		for (int i = 0; i < toggableChannelsList.size(); i++) {
			if (jtglbtn.getText() == toggableChannelsList.get(i).title) {
				Initialize.getUrls(toggableChannelsList.get(i).id);
				break;
			}
		}
	}

	public void createMainUI(MainUI mainUI) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
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
	
	@Override
	public void myChannelsResult(Map<String, Object> channelsMap) {
		System.out.println(channelsMap);
		//channels.putAll(channelsMap); 
		for (Entry<String, Object> channel : channelsMap.entrySet()) {
            @SuppressWarnings("unchecked")
			Map<String, Object> channelFields =  (Map<String, Object>) channel.getValue();
			addToggleBtn(channelFields.get("title").toString());
            ToggableChannels tc = new ToggableChannels(channelFields.get("title").toString(), channel.getKey());
            toggableChannelsList.add(tc);
		}
		
	}

}