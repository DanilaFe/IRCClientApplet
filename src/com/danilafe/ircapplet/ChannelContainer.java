package com.danilafe.ircapplet;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChannelContainer extends Container{
	
	private JTextArea log = new JTextArea();
	private JTextField input = new JTextField();
	private String name;
	private JList<String> names = new JList<String>();
	
	public ChannelContainer(){
		super();
		
		//Add some gui here.
		this.setLayout(new BorderLayout());
		add(log, BorderLayout.CENTER);
		add(input, BorderLayout.SOUTH);
		add(names, BorderLayout.EAST);
		
		
	}
	public void setName(String newname){
		name = newname;
	}
	
	public String getName(){
		return name;
	}
	
}
