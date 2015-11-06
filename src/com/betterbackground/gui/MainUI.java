package com.betterbackground.gui;

import java.awt.AWTException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	ArrayList<ToggableChannels> toggableChannelsList = new ArrayList<>();
	private static MainUI instance;
	
	public static MainUI getInstance(){
		if(instance == null){
			instance = new MainUI();
		}
		return instance;
	}
	
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
	
	public void addToggleBtn(String channelName) {
		JToggleButton channelBtn = new JToggleButton(channelName);
		channelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToggableURL(channelBtn);
			}
		});
		panel.add(channelBtn);

		panel.revalidate();
		validate();
		panel.repaint();
	}
	
	JPanel panel;
	public MainUI() {
		super("Better Background");
		
		// Set up the system tray
		setupSystemTray();

		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3, 3, 3));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Channel Selection"));
		
		add(panel);

		setSize(500, 200);
		setLocationRelativeTo(null);
	}
	
	public void sendToggableURL(JToggleButton jtglbtn) {
		for (int i = 0; i < toggableChannelsList.size(); i++) {
			if (jtglbtn.getText() == toggableChannelsList.get(i).title) {
				Initialize.getUrls(toggableChannelsList.get(i).id);
				break;
			}
		}
	}

	public void createMainUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		// Add show App button
        MenuItem item2 = new MenuItem("Show");
        item2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(true);
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