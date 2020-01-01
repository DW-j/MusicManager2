package de.MusicManager2.controller;

import de.MusicManager2.elements.Songlist;

public class Handler {

	static String handle(String command, Songlist songlist) {
		switch(command) {
		case "all":
			return Methods.all(songlist.getSongs());
		case "update":
			return Methods.update();
		case "artist":
			return Methods.artist(songlist.getSongs());
		}
		return "Command unknown";
	}
	
}