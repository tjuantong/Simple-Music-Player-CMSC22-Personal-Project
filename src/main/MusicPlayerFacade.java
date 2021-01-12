/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import jaco.mp3.player.*; 	//External JAR which gives the functionality to play, pause, or stop mp3 files

/**
 * @author Acer
 *
 */

public class MusicPlayerFacade {
	
	//class variables
	MusicPlayer musicPlayer;
	MP3Player player;

	private ListIterator<String> playlistIterator;
	private ArrayList<String> songList = new ArrayList<String>();
	private String songPath;
	private int songNumber;
	private boolean playing;
	private boolean paused;
	
	//constructor that has parameters of songPath and list of songs
	protected MusicPlayerFacade(String songPath, List<String> songs) throws FileNotFoundException {
		this.songPath = songPath;
		File directory = new File(songPath);
		String temp = directory + "\\";
		for (String s : songs) {
			//to check if the song file exists in the given path
			if (new File(temp + s).exists() && s.endsWith(".mp3")) {
				songList.add(s);
			}
		}
		
		//will print the commands for the player
		printCommands();
	}
	
	/**
	 * @param songNumber the songNumber to set
	 */
	protected void setSongNumber(int songNumber) {
		this.songNumber = songNumber;
	}
	
	/**
	 * @return the songNumber
	 */
	protected int getSongNumber() {
		return songNumber;
	}
	
	/**
	 * @param playing the playing to set
	 */
	protected void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	/**
	 * @return the playing
	 */
	protected boolean isPlaying() {
		return playing;
	}
	
	/**
	 * @param paused the paused to set
	 */
	protected void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	/**
	 * @return the paused
	 */
	protected boolean isPaused() {
		return paused;
	}
	
	/**
	 * 
	 * @return songList
	 */
	protected ArrayList<String> getList(){
		return songList;
	}
	
	/**
	 * set up conditional statements to be able to determine if the song is played or resumed
	 * initializes the MP3player to play the file
	 * 
	 * updates the boolean values, playing and paused, after calling the music player
	 * prints out the name of song if a new song has been played without the ".mp3" extension
	 * 
	 * uses removeExtension and toString method
	 */
	public void playSong() {
		if ((isPlaying() == false) && (isPaused() == false)) {
			player = new MP3Player(new File(toString(songPath, songNumber)));
			player.play();

			setPlaying(true);

			System.out.println("NOW PLAYING: " + removeExtension(songList.get(songNumber)));

		} else if ((isPlaying() == false) && (isPaused() == true)) {	//for resuming the song
			player.play();

			setPlaying(true);
			setPaused(false);

			System.out.println("== SONG RESUMED ==");
		}
	}
	
	/**
	 * checks if song is playing to invoke the pause method
	 * updates the boolean values, playing and paused, after calling the pause function of the player
	 * 
	 * prints out a statement if song is paused
	 */
	public void pauseSong() {
		if (isPlaying() == true) {
			player.pause();

			setPaused(true);
			setPlaying(false);

			System.out.println("== SONG PAUSED ==");
		}
	}
	
	/**
	 * will stop the player before playing the next song
	 * updates the song number, could loop forward if song position will be greater than the size
	 * updates the boolean values, playing and paused, after calling the play function of the player
	 * 
	 * prints out a statement if next song is played
	 * prints out the current song without the ".mp3" extension 
	 * 
	 * uses removeExtension and toString method
	 */
	public void nextSong() {
		player.stop();
		setSongNumber((songNumber + 1) % songList.size());

		player = new MP3Player(new File(toString(songPath, songNumber)));
		player.play();

		setPlaying(true);
		setPaused(false);

		System.out.println("== NEXT SONG ==");
		System.out.println("NOW PLAYING: " + removeExtension(songList.get(songNumber)));
	}
	
	/**
	 * will stop the player before playing the previous song
	 * set up conditional statements to check the current position  in the playlist
	 * will be able to loop backward if song position is at index 0, else will decrease song position by 1
	 * 
	 * updates the boolean values, playing and paused, after calling the play function of the player
	 * prints out a statement if previous song is played
	 * prints out the current song without the ".mp3" extension 
	 * 
	 * uses removeExtension and toString method
	 */
	public void prevSong() {
		if (getSongNumber() == 0) {
			setSongNumber(songList.size() - 1);
			if ((isPlaying() == true) && (isPaused() == false)) {
				player.stop();
			}
		} else {
			setSongNumber(--songNumber);
			if ((isPlaying() == true) && (isPaused() == false)) {
				player.stop();
			}
		}

		player = new MP3Player(new File(toString(songPath, songNumber)));
		player.play();

		setPaused(false);
		setPlaying(true);

		System.out.println("== PREVIOUS SONG ==");
		System.out.println("NOW PLAYING: " + removeExtension(songList.get(songNumber)));
	}
	
	/**
	 * @throws InputMismatchException (thrown when a user inputs a character or symbol instead of a int number)
	 * will print the song list to let user choose which song position they would like to skip to
	 * Suppress warnings for leaked resource due to the scanner
	 * 
	 * will check if entered input is in range of the playlist size
	 * set up conditional statements to check if song is curreently playing or not
	 * stops the current song playing and plays the song being skipped to
	 * 
	 * prints out a statement if skipped song is played
	 * prints out the current song without the ".mp3" extension 
	 * uses removeExtension and toString method
	 */
	public void skipSong() throws InputMismatchException {
		printSongList();

		@SuppressWarnings("resource")
		Scanner num = new Scanner(System.in);

		System.out.println("Which song would you like to skip to?");

		boolean validNumber = false;
		int songSkip = 1;

		while (!validNumber) {
			try {
				songSkip = num.nextInt();
				validNumber = (songSkip > 0) && (songSkip <= songList.size());
				
			} catch (InputMismatchException e) {
				System.out.println("INVALID INPUT! Please input a song number.");
				num.nextLine();
				continue;
			}
			
			if (!validNumber) {
				System.out.println("INVALID SONG NUMBER! Please input again.");

			} else {
				if ((isPaused() == false) && (isPlaying() == true)) {
					player.stop();
					setPlaying(false);
				}

				setSongNumber(--songSkip);

				player = new MP3Player(new File(toString(songPath, songNumber)));
				player.play();

				setPaused(false);
				setPlaying(true);

				System.out.println("== SONG SKIP ==");
				System.out.println("NOW PLAYING: " + removeExtension(songList.get(songNumber)));

				validNumber = true;
			}
		}
	}
	
	/**
	 * prints the commands of the playlist
	 */
	public void exitPlayer() {
		System.out.println("== EXIT PLAYER ==");
	}
	
	/**
	 * prints the song list of the playlist
	 * uses listIterator method to traverse and print the elements of the song list
	 * 
	 */
	public void printSongList() {
		try {
			if (songList.size() == 0) {
				System.out.println("There is no songs in the playlist.");
			} else {
				int num = 1;
				playlistIterator = songList.listIterator();
			
				System.out.println("--------- SONG LIST ---------");

				while (playlistIterator.hasNext()) {
					System.out.println("#" + num + ": " + removeExtension(playlistIterator.next()));
					num++;
				}
				if ((!playlistIterator.hasNext()) && (songList.size() == 0)) {
					System.out.println("No songs in the folder!");
				}

				System.out.println("-----------------------------");
				System.out.println("Total number of songs: " + songList.size());
				System.out.println("-----------------------------");
			}
			
		} catch (NullPointerException e) {
			System.out.println("Please create a music playlist.");
		}
	}
	
	/**
	 * prints the commands of the playlist
	 */
	public void printCommands() {
		System.out.println("---------- COMMANDS ---------");
		System.out.println("s - play/resume song");
		System.out.println("p - pause song");
		System.out.println("n - next song");
		System.out.println("b - previous song");
		System.out.println("k - skip to song");
		System.out.println("l - print song list");
		System.out.println("c - print commands");
		System.out.println("e - exit music player");
		System.out.println("-----------------------------");
	}
	
	/**
	 * @param fileName of the song file
	 * @return String
	 * 
	 * removes the ".mp3" extension of the songs
	 */
	public String removeExtension(String fileName) {
		if (fileName.indexOf(".") > 0) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		return fileName;
	}
	
	/**
	 * @param songPath that is the directory
	 * @param songNumber which is the current song
	 * @return String
	 * 
	 * joins the song path and the song file into one string 
	 * will be used to play the songs using the MP3Player class
	 */
	public String toString(String songPath, int songNumber) {
		return songPath + "\\" + songList.get(songNumber);
	}

}
