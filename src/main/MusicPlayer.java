/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tjjuantong
 *
 */

public class MusicPlayer extends Playlist.PlaylistBuilder {

	protected static Playlist.PlaylistBuilder playlist;
	ArrayList<String> finalList = new ArrayList<String>();
	String songPath;
	
	/**
	 * @param songPath
	 * @param songs
	 * @throws FileNotFoundException
	 * Constructor which needs the parameters set in the PlaylistBuilder
	 * will check if the song file is in the given path / directory
	 * will print out files not found in the path
	 */
	MusicPlayer(String songPath, List<String> songs) throws FileNotFoundException {
		File dir = new File(songPath);
		String temp = dir + "\\";
		
		for (String s : songs) {
			if (new File(temp + s).exists() && s.endsWith(".mp3")) {
				finalList.add(s);
			} else {
				System.out.println(s + " is not found on " + songPath);
			}
		}
		
		this.songPath = songPath;
	}
	
	/**
	 * prints the final song list of the playlist
	 */
	public void printList() {
		System.out.println("-- PRINTING FINAL LIST --");

		System.out.println("MUSIC PLAYER SONG PATH: " + this.songPath);
		System.out.println("FINAL SONGS ADDED IN SONG LIST: ");
		
		for (String s : finalList) {
			System.out.println(s);
		}

	}
}
