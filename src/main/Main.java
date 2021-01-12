/**
 * 
 */
package main;
 
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author tjjuantong
 *
 */

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		
		Playlist s = Playlist.builder().songPath("C:\\Users\\Acer\\Documents\\Music")
					.addSong("Pop Danthology 2013.mp3")
					.addSong("I Fall Apart - Kodaline (cover).mp3")
					.addSong("Valentine crush - Blanks.mp3")
					.addSong("No song without you (livestream) - HONNE.mp3")
					.addSong("Hello.mp3")
					.addSong("Yo")
					.addSong("Hooked - Blanks x Sheppard.mp3")
					.addSong("Warm on a Cold Night (livestream) - HONNE.mp3")
					.build();

		System.out.println("SONG PATH: " + s.songPath);
		System.out.println("SONGS TO BE ADDED:" + s.songs);

		System.out.println();

		MusicPlayer m = new MusicPlayer(s.songPath, s.songs);
		m.printList();

		System.out.println();
		System.out.println("==== SIMPLE MUSIC PLAYER ====");

		MusicPlayerFacade musicPlayer = new MusicPlayerFacade(s.songPath, s.songs);
		boolean isExit = true;

		while (isExit) {

			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.println("Command: ");
			String nextLine = input.next();

			switch (nextLine) {

			case "s":
			case "S":
				musicPlayer.playSong();
				break;

			case "p":
			case "P":
				musicPlayer.pauseSong();
				break;

			case "n":
			case "N":
				musicPlayer.nextSong();
				break;

			case "b":
			case "B":
				musicPlayer.prevSong();
				break;

			case "k":
			case "K":
				musicPlayer.skipSong();
				break;

			case "l":
			case "L":
				musicPlayer.printSongList();
				break;

			case "c":
			case "C":
				musicPlayer.printCommands();
				break;

			case "e":
			case "E":
				musicPlayer.exitPlayer();
				isExit = false;
				break;

			default:
				System.out.println("INVALID COMMAND");
			}
		}
	}
}
