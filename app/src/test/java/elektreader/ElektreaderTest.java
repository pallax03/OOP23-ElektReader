/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package elektreader;

import org.junit.jupiter.api.Test;

import elektreader.api.Reader;
import elektreader.model.ReaderImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;

class ElektreaderTest {

    Reader app = new ReaderImpl();

    /* TEST CONSTANT */

    /* mi raccomando per i test posizionare la cartella nel percorso specificato */
    /* cartella: https://drive.google.com/file/d/1b5JAQ3Hc6FRwvO2BjIb7olaxOApJDrfp/view?usp=sharing */
    final Path TEST_PATH = Paths.get(System.getProperty("user.home"),"elektreaderTEST","Environment");

    final Path TEST_INVALID_PLAYLIST = Paths.get(TEST_PATH.toString(), "GENERI"); 

    final Path TEST_PATH_PLAYLIST1 = Paths.get(TEST_PATH.toString(), "tutta la musica");
    final Path TEST_PATH_SONG1 = Paths.get(TEST_PATH_PLAYLIST1.toString(), "04 - la bomba.mp3");
    
    final Path TEST_PATH_PLAYLIST2 = Paths.get(TEST_PATH.toString(), "GENERI", "MUSICA ROMAGNOLA");
    final Path TEST_PATH_SONG2 = Paths.get(TEST_PATH_PLAYLIST2.toString(), "16 - valzer dell'usignolo.mp3");


    /* TESTS */

    @Test void testEnvironment() {
        /* test environment */
        Assertions.assertTrue(app.setCurrentEnvironment(TEST_PATH));
        Assertions.assertEquals(app.getCurrentEnvironment().get(), TEST_PATH);
        
        /* test playlist - song */ 
        //test valid playlist 1
        Assertions.assertTrue(app.setCurrentPlaylist(app.getPlaylist(TEST_PATH_PLAYLIST1)));
        Assertions.assertEquals(app.getCurrentPlaylist().get().getPath(), TEST_PATH_PLAYLIST1);
        
        //test valid playlist 2
        Assertions.assertTrue(app.setCurrentPlaylist(app.getPlaylist(TEST_PATH_PLAYLIST2)));
        Assertions.assertEquals(app.getCurrentPlaylist().get().getPath(), TEST_PATH_PLAYLIST2);

        // test invalid playlist
        Assertions.assertFalse(app.setCurrentPlaylist(app.getPlaylist(TEST_INVALID_PLAYLIST)));
        Assertions.assertEquals(app.getCurrentPlaylist(), Optional.empty());

        //test valid song
        //Assertions.assertTrue(app.setCurrentSong(app.getCurrentPlaylist().get()));


        //test invalid song
        //Assertions.assertTrue(app.setCurrentPlaylist(app.getPlaylist(TEST_PATH_PLAYLIST2).get().getSong(0)));
    }

    @Test void testPlaylists() {
        // TODO
    }

    @Test void testSongs() {
        //TODO
    }

    @Test void test() {
        //TODO
    }
}