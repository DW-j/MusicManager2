package de.MusicManager2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import de.MusicManager2.elements.Song;
import de.MusicManager2.elements.Songlist;
import de.MusicManager2.utility.Finder;
import de.MusicManager2.utility.User;
import de.MusicManager2.utility.XMLHandler;

public class Methods {

	static String all(List<Song> songlist) {
		StringBuilder output = new StringBuilder();
		songlist.stream().forEach(s -> output.append(s.toString()+'\n'));
		return output.toString();
	}
	
	static String update() {
		String name = User.input("Provide spotify profile name.");
		User.output("Listing songs. this may take a while.");
		Songlist songlist = Finder.getSonglist(name);
		XMLHandler.writeXML(songlist);
		return "Sucessful updated.";
	}

	static String artist(List<Song> songs) {
		
		Map<String, List<Song>> groups = new HashMap<String, List<Song>>();
		for(Song s: songs) {
			if(!groups.containsKey(s.getArtist())) {
				groups.put(s.getArtist(), new ArrayList<Song>());
			}
			if(!groups.get(s.getArtist()).stream().map(t -> t.getTitle()).collect(Collectors.toList()).contains(s.getTitle())) {
				groups.get(s.getArtist()).add(s);
			}
		}
		
		StringBuilder output = new StringBuilder();
		for(Entry<String, List<Song>> e: groups.entrySet()) {
			output.append(e.getKey()+": "+e.getValue().size()+'\n');
			for(Song s: e.getValue()) {
				output.append(s.toString()+'\n');
			}
			output.append('\n');
		}
		
		return output.toString();
		
	}
	
}