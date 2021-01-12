/**
 * 
 */
package main;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Playlist.PlaylistBuilder;

/**
 * @author tjjuantong
 *
 */
class MusicPlayerTest extends PlaylistBuilder{

	@Test
	void testMusicPlayer() throws FileNotFoundException {
		try {
			PlaylistBuilder b = new PlaylistBuilder();
			b.songPath("C:\\Users\\Acer\\Documents\\Music")
			 .addSong("Pop Danthology 2013.mp3")
			 .addSong("I Fall Apart - Kodaline (cover).mp3")
			 .addSong("Hello.txt")
			 .addSong("Example")
			 .build();
			
			List<String> finalList = new ArrayList<String>();
			List<String> notAdded = new ArrayList<String>();
			
			
			List<String> expectedNotAdded = new ArrayList<>();
			expectedNotAdded.add("Hello.txt");
			expectedNotAdded.add("Example");
			
			List<String> expectedFinalList = new ArrayList<>();
			expectedFinalList.add("Pop Danthology 2013.mp3");
			expectedFinalList.add("I Fall Apart - Kodaline (cover).mp3");
			
			
			File dir = new File(b.songPath);
			String temp = dir + "\\";
			
			for (String s : b.songs) {
				if ((new File(temp + s).exists()) && (s.endsWith(".mp3"))) {
					finalList.add(s);
				} else {
					notAdded.add(s);
				}
			}
			
			assertArrayEquals(expectedFinalList.toArray(), finalList.toArray());
			assertArrayEquals(expectedNotAdded.toArray(), notAdded.toArray());
			
		}catch (FileNotFoundException e) {
			e.getMessage();
		}
	}
}
