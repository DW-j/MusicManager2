package de.MusicManager2.controller;

import java.util.Arrays;

import de.MusicManager2.utility.User;
import de.MusicManager2.utility.XMLHandler;

public class Main {

	public static void main(String[] args) {

		while(true) {
			String command = User.input("Enter a command. Available commands: "+Arrays.toString(new String[] {"exit", "all", "update", "artist"})+".");
			if(command.equals("exit")) {
				User.output("Program exited");
				break;
			}
			User.output(Handler.handle(command, XMLHandler.readXML()));
		}
		
		User.sc.close();
	}

}