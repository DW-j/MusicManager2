package de.MusicManager2.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@XmlRootElement(name="Song")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class Song {

	@XmlAttribute
	@Setter
	private int id;
	@XmlAttribute
	private String title, artist, album, playlist;
	
	public Song(String title, String artist, String album, String playlist) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.playlist = playlist;
	}
	
	public String toString() {
		return "Song [ID = " + id + ", Title = " + title + ", Artist = " + artist + ", Album = " + album + ", Playlist = " + playlist + "]";
	}
	
}