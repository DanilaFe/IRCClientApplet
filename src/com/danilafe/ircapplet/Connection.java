package com.danilafe.ircapplet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	
	Socket s;
	String hostname;
	int port;
	PrintStream ps;
	String pong;
	BufferedReader r;
	Thread t = new Thread(){
		public void run(){
			String s = null;
			try {
				while((s = r.readLine()) != null){
					System.out.println(s);
					if(s.startsWith("PING :")){
						pong = s.replace("PING :", "");
						ps.println("PONG :" + pong);
					} 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	};
	
	public Connection(int port, String host){
	System.out.println("Initialized connection");
		this.hostname = host;
		this.port = port;
		try {
			s = new Socket(host,port);
			ps = new PrintStream(s.getOutputStream());
			r = new BufferedReader(new InputStreamReader(s.getInputStream()));
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(String message){
		ps.println("PRIVMSG " + message);
	}
	
	public void initIRCConnection(String name, String rname){
		ps.println("USER " + name +  " " + "localhost" + " " + hostname + " " + rname);
		ps.println("NICK " + name);
	}
	
}
