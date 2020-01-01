package de.MusicManager2.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import de.MusicManager2.elements.Song;
import de.MusicManager2.elements.Songlist;

public class Finder {
	
	public static Songlist getSonglist(String name){
		
		List<Song> songlist = new ArrayList<Song>();
		WebDriver driver = getWebDriver();
		String mainLink = "https://open.spotify.com/user/"+name;

		driver.get(mainLink);
		int playlistNumber = driver.findElements(By.className("mo-info-name")).size();
		
		for(int i=0; i<playlistNumber; i++) {
			driver.get(mainLink);
			WebElement playlist = driver.findElements(By.className("mo-info-name")).get(i);
			songlist.addAll(getSongs(driver, playlist.getAttribute("title"), playlist.getAttribute("href")));
			User.output(String.format("Updated %d/%d playlists.", i+1, playlistNumber));
		}		
		
		IntStream.range(0, songlist.size()).forEach(i -> songlist.get(i).setId(i));

		return new Songlist(songlist);
	}
	
	private static WebDriver getWebDriver() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\wittn\\workspaces\\local workspace\\MusicManager2\\src\\main\\java\\files\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("headless");
		return new ChromeDriver(opt);
	}
	
	private static List<Song> getSongs(WebDriver driver, String playlist, String link){
		
		load(driver, link);

		List<WebElement> tracklist = driver.findElement(By.className("tracklist")).findElements(By.xpath("*"));
		
		List<Song> songlist = new ArrayList<Song>();
		
		tracklist.stream().forEach(t -> songlist.add(
				new Song(
						getTracklistAttributes(t, "tracklist-name"),
						getTracklistAttributes(t, "tracklist-row__artist-name-link"),
						getTracklistAttributes(t, "tracklist-row__album-name-link"),
						playlist
				)));
			
		return songlist;
	}
	
	private static void load(WebDriver driver, String link){
		driver.get(link);
		int formerTracks;
		List<WebElement> tracks = new ArrayList<WebElement>();
		do{
			formerTracks = tracks.size();
			tracks = driver.findElement(By.className("tracklist")).findElements(By.xpath("*"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tracks.get(tracks.size()-1));
			try {
				Thread.sleep(350);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}while(tracks.size()>formerTracks);
	}
	
	private static String getTracklistAttributes(WebElement track, String elementName){
		return track.findElement(By.className(elementName)).getText();
	}
	
}