/**
 * 
 */
package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @author tjjuantong
 *
 */
class MusicPlayerFacadeTest {
	
	@Test
	void testPlaySong(){
		try {
			String expectedSongPath = "C:\\Users\\Acer\\Documents\\Music";
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
					 									"I Fall Apart - Kodaline (cover).mp3", 
					  									"Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade(expectedSongPath, expectedSongs);
			
			assertTrue("NOW PLAYING: " + m.removeExtension(m.toString(expectedSongPath,0)), ((m.isPlaying() == false) && (m.isPaused() == false)));
			
			m.setPlaying(false);
			m.setPaused(true);
			
			assertTrue("== SONG RESUMED ==", ((m.isPlaying() == false) && (m.isPaused() == true)));
			
		}catch(IndexOutOfBoundsException e ) {
			e.getMessage();
			
		}catch(FileNotFoundException d) {
			d.getMessage();
		}
		
	}

	@Test
	void testPauseSong(){
		try {
			String expectedSongPath = "C:\\Users\\Acer\\Documents\\Music";
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
														"I Fall Apart - Kodaline (cover).mp3", 
					  									"Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade(expectedSongPath, expectedSongs);
			m.setPlaying(true); 
			
			assertTrue("== SONG PAUSED ==", (m.isPlaying()==true));
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	@Test
	void testNextSong(){
		try {
			String expectedSongPath = "C:\\Users\\Acer\\Documents\\Music";
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
														"I Fall Apart - Kodaline (cover).mp3", 
					  									"Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade(expectedSongPath, expectedSongs);

			int songNumber = 2;
			m.setSongNumber((songNumber + 1) % 3);
			
			assertEquals(0, m.getSongNumber());
		
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	
	@Test
	void testPrevSong(){
		try {
			int songNum = 0;
			int songNum2 = 2;
			String expectedSongPath = "C:\\Users\\Acer\\Documents\\Music";
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
														"I Fall Apart - Kodaline (cover).mp3", 
					  									"Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade(expectedSongPath, expectedSongs);
			m.setSongNumber(songNum);
			
			if(m.getSongNumber()==0) {
				m.setSongNumber(m.getList().size()-1);
			}else {
				m.setSongNumber(--songNum);
			}

			assertEquals(2 , m.getSongNumber());
			
			m.setSongNumber(songNum2);
			if(m.getSongNumber()==0) {
				m.setSongNumber(m.getList().size()-1);
			}else {
				m.setSongNumber(--songNum2);
			}
			
			assertEquals(1,m.getSongNumber());
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}

	}

	@Test
	void testSkipSong(){
		try {
			int num = 5;
			int num2 = 2;
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
												       "I Fall Apart - Kodaline (cover).mp3", 
													   "Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade("C:\\Users\\Acer\\Documents\\Music", expectedSongs);
			
			boolean check = (num > 0) && (num <= m.getList().size());
			boolean check2 = (num2 >0) && (num2 <= m.getList().size());
			
			assertFalse(check, "Must be false");
			assertTrue(check2, "Must be true");
			
		}catch(FileNotFoundException e) {
			e.getMessage();
			
		}catch (InputMismatchException d) {
			d.getMessage();
		}
		
		InputMismatchException exception = Assertions.assertThrows(InputMismatchException.class, 
				() -> {throw new InputMismatchException ("INVALID INPUT! Please input a song number.");});
		
		assertEquals("INVALID INPUT! Please input a song number.", exception.getMessage());
		
	}
	
	@Test
	void testRemoveExtension() {
		try {
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
													   "I Fall Apart - Kodaline (cover).mp3", 
						 							   "Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade("C:\\Users\\Acer\\Documents\\Music", expectedSongs);

			assertEquals("Pop Danthology 2013", m.removeExtension((String)expectedSongs.get(0)));
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

	@Test
	void testToString() {
		try {
			String expectedSongPath = "C:\\Users\\Acer\\Documents\\Music";
			List<String> expectedSongs = Arrays.asList("Pop Danthology 2013.mp3", 
													   "I Fall Apart - Kodaline (cover).mp3", 
													    "Valentine crush - Blanks.mp3");
			
			MusicPlayerFacade m = new MusicPlayerFacade(expectedSongPath, expectedSongs);
			m.setSongNumber(0);
			
			assertEquals("C:\\Users\\Acer\\Documents\\Music\\Pop Danthology 2013.mp3", m.toString(expectedSongPath, 0));
			
		}catch(FileNotFoundException e) {
			e.getMessage();
		}
	}

}
