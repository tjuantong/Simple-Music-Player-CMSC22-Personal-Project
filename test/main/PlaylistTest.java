/**
 * 
 */
package main;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Playlist.PlaylistBuilder;

/**
 * @author tjjuantong
 *
 */
class PlaylistTest {

	PlaylistBuilder b = new PlaylistBuilder();
	String expectedSongPath = "C:\\Users\\Acer\\Documents\\Music";
	String invalidSongPath = "C:\\Users\\Acer\\Documents\\M";
	
	List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
							  				   "I Fall Apart - Kodaline (cover).mp3");
	
	String toString =  "Song Path: " + "C:\\Users\\Acer\\Documents\\Music" + 
					   " || Songs: " + expectedSongs;
	
	@Test
	void testPlaylistBuilder(){
		try {
			b.songPath("C:\\Users\\Acer\\Documents\\Music")
			.addSong("Pop Danthology 2013.mp3")
			.addSong("I Fall Apart - Kodaline (cover).mp3")
			.build();
			
			b.songPath(invalidSongPath).build();
			
			assertEquals(expectedSongPath, b.songPath);
			assertEquals(expectedSongs, b.songs);
			
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}


	@Test
	void testSongPath() throws FileNotFoundException {

		try {
			b.songPath(invalidSongPath).build();
			
		} catch (NullPointerException d) {
			d.getMessage();
		}
		
		Assertions.assertThrows(NullPointerException.class, 
				() -> {if (new File (b.songPath).isDirectory());}, "didnt throw NullPointerException");

		FileNotFoundException exception = Assertions.assertThrows(FileNotFoundException.class, 
				() -> {throw new FileNotFoundException("Song path is not a directory");});
		
		assertEquals("Song path is not a directory", exception.getMessage());
	}

	@Test
	void testBuild() {
		Assertions.assertThrows(NullPointerException.class, () -> {
		      b.songPath(null);
		    });
		
		NullPointerException exception = Assertions.assertThrows(NullPointerException.class, 
				() -> {throw new NullPointerException ("Must input song path for song list");});
		
		NullPointerException exception2 = Assertions.assertThrows(NullPointerException.class, 
				() -> {throw new NullPointerException ("Must input songs for song list");});
		
		assertEquals("Must input song path for song list", exception.getMessage());
		assertEquals("Must input songs for song list", exception2.getMessage());
	}

	@Test
	void testToString() throws FileNotFoundException {
		PlaylistBuilder b = new PlaylistBuilder();
		
		assertEquals(toString, b.songPath("C:\\Users\\Acer\\Documents\\Music")
								.addSong("Pop Danthology 2013.mp3")
								.addSong("I Fall Apart - Kodaline (cover).mp3")
								.build()
								.toString());
		
	}

}
