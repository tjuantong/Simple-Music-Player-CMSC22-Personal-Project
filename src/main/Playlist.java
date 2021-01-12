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

public class Playlist {

	protected String songPath;
	protected List<String> songs;
	
	
	/**
	 * @param songPath
	 * @param songs
	 * @throws FileNotFoundException when song path isn't a directory
	 * @throws NullPointerException when song path is null
	 * constructor for Playlist
	 */
	Playlist(String songPath, List<String> songs) throws FileNotFoundException {

		if (songPath == null) {
			throw new NullPointerException("The property of song path is null. A valid song path is required.");
		}

		if (!new File(songPath).isDirectory()) {
			throw new FileNotFoundException("The property of song path is not a directory");
		}

		this.songPath = songPath;
		this.songs = songs;
	}
	
	/**
	 * 
	 * @return a new instance of PlaylistBuilder
	 */
	public static PlaylistBuilder builder() {
		return new PlaylistBuilder();
	}
	
	/**
	 * 
	 * create an inner static PlaylistBuilder class to avoid making an instance of the Playlist class
	 *
	 */
	public static class PlaylistBuilder {

		protected String songPath;
		protected List<String> songs = new ArrayList<String>();

		PlaylistBuilder() {
		}
		
		/**
		 * @param songPath for the songlist
		 * @return PlaylistBuilder
		 * @throws NullPointerException is songPath is null
		 * checks if songPath is an existing directory or not
		 */
		public PlaylistBuilder songPath(String songPath) {
			File dir = new File(songPath);
			try {
				if (dir.isDirectory()) {
					this.songPath = songPath;
					
				} else {
					throw new FileNotFoundException("Song path is not a directory");
				}
			} catch (FileNotFoundException e) {
				e.getMessage();
			} catch (NullPointerException d) {
				d.getMessage();
			}
			return PlaylistBuilder.this;
		}
		
		/**
		 * @param songs that will be added
		 * @return PlaylistBuilder
		 */
		public PlaylistBuilder songs(List<String> songs) {
			this.songs = songs;
			return PlaylistBuilder.this;
		}
		
		/**
		 * @param songs that will be added to the playlist
		 * @return PlaylistBuilder
		 */
		public PlaylistBuilder addSong(String songs) {
			this.songs.add(songs);
			return PlaylistBuilder.this;
		}
		
		/**
		 * 
		 * @return Playlist with constructors song path and songs
		 * @throws FileNotFoundException if file is not an existing directory
		 * @throws NullPointerException if song path / songs is null
		 */
		public Playlist build() throws FileNotFoundException {
			if (this.songPath == null) {
				throw new NullPointerException("Must input song path for song list");
			} else if (!new File(this.songPath).isDirectory()) {
				throw new FileNotFoundException("Song path is not a directory");
			}

			if (this.songs == null) {
				throw new NullPointerException("Must input songs for song list");
			}

			return new Playlist(this.songPath, this.songs);

		}
		
		/**
		 * will return a String of the song path and list of songs under PlaylistBuilder
		 */
		@Override
		public String toString() {
			return "Playlist.PlaylistBuilder SONG PATH: " + this.songPath + " || songs =" + this.songs + ")";
		}

	}
	
	/**
	 * will return a String of the song path and list of songs after it has been built
	 */
	@Override
	public String toString() {
		return "Song Path: " + this.songPath + " || Songs: " + this.songs;
	}
}
