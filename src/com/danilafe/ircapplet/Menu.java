package com.danilafe.ircapplet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Menu extends Applet implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean connected = true;
	Container[] settings = new Container[2];
	ArrayList<ChannelContainer> channels= new ArrayList<ChannelContainer>();
	JTabbedPane gui = new JTabbedPane();
	JTabbedPane channelpane = new JTabbedPane();
	JTextField[] confields = new JTextField[3];
	JLabel[] conlabels = new JLabel[]{
			new JLabel("Host:"),
			new JLabel("Nick:"),
			new JLabel("Channel")
	};
	JButton connect = new JButton("Connect.");
	JTextField console = new JTextField();
	JTextArea feedback = new JTextArea();
	
	
	@Override
	public void init(){
		//Add the gui pane here.
		add(gui);
		this.setVisible(true);
		
		//Create the two containers for the main menu and set settings.
		settings[0] = new Container();
		settings[0].setLayout(new GridLayout(0,2));
		settings[1] = new Container();
		settings[1].setLayout(new BorderLayout());
		
		//Init the text fields
		for(int i = 0; i < confields.length; i ++){
			confields[i] = new JTextField();
		}
		
		//Add stuff to the button containers
		settings[0].add(conlabels[0]);
		settings[0].add(confields[0]);
		settings[0].add(conlabels[1]);
		settings[0].add(confields[1]);
		settings[0].add(conlabels[2]);
		settings[0].add(confields[2]);
		
		//Add action listeners
		connect.addActionListener(this);
		
		//Add stuff to other container
		settings[1].add(settings[0], BorderLayout.CENTER);
		settings[1].add(connect, BorderLayout.SOUTH);
		
		gui.addTab("Connection Settings", settings[1]);
		gui.addTab("Channels", channelpane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(connect)){
			if(connected){
				if(! checkChannel(confields[2].getText())){
					ChannelContainer c = new ChannelContainer();
					c.setName(confields[2].getText());
					channelpane.addTab(confields[2].getText(), c);
				}
			}
		}
		
	}
	
	public boolean checkChannel(String checkname){
		for(ChannelContainer c: channels){
			if(c.getName().equals(checkname)){
				return true;
			}
		}
		
		return false;
	}
	
	
}
