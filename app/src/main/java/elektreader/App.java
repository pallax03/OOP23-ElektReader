/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package elektreader;

import java.nio.file.Path;
import java.nio.file.Paths;

import elektreader.view.GUI;
import javafx.application.Application;

public class App{
    public static final Path TEST_PATH = Paths.get(System.getProperty("user.home"),"elektreaderTEST","Environment");

    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }


    //problema: WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @61e9bc05'
    //soluzione: ABBIAMO BISOGNO DEL MODULE-INFO.JAVA messo in 'src/main/java'

    // qui abbiamo scoperto perchè abbiamo bisogno di un altra classe di appoggio
    // Note when JavaFX is loaded from the unnamed module (i.e., class-path),
    // then the main class cannot be a subtype of javafx.application.Application.
    // You must define a separate main class whose main method simply launches the JavaFX application.
    // This is true for JavaFX 9 up to and including at least JavaFX 21.
}