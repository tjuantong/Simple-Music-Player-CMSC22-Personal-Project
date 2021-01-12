/**
 * 
 */
package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author tjjuantong
 *
 */
class MainTest {

	@Test
	void testMain() {
		try {
			Playlist s = Playlist.builder()
								 .songPath("C:\\Users\\Acer\\Documents\\Music")
								 .addSong("Pop Danthology 2013.mp3")
								 .addSong("I Fall Apart - Kodaline (cover).mp3")
								 .addSong("Valentine crush - Blanks.mp3")
								 .addSong("No song without you (livestream) - HONNE.mp3")
								 .addSong("Hello.mp3")
								 .addSong("Yo")
								 .addSong("Hooked - Blanks x Sheppard.mp3")
								 .addSong("Warm on a Cold Night (livestream) - HONNE.mp3")
								 .build();

			MusicPlayerFacade musicPlayer = new MusicPlayerFacade(s.songPath, s.songs);
			
			assertNotNull(s);
			assertNotNull(musicPlayer);
			
		}catch (FileNotFoundException e) {
			e.getMessage();
		}
	}
}
