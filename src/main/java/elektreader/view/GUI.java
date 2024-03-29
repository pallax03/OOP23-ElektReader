package elektreader.view;

import java.awt.Toolkit;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * this class create the main stage,
 * extends the Application class of JavaFX,
 * that is responsable to create the main application scene.
 */
public final class GUI extends Application {
    /**
     * program name.
     */
    public static final String PROGRAM_NAME = "ElektReader";

    /**
     * constant used for calculate the screen size (80%).
     */
    public static final double SCALE_SCREEN_SIZE_PERCENT = 0.8;

    /**
     * @return a pair of Width and Height,
     * calculate the initial screen size of the window, 
     * that is 80% of the screen size.
     */
    public static Pair<Double, Double> scaleToScreenSize() {
        return new Pair<Double, Double>(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * SCALE_SCREEN_SIZE_PERCENT,
            Toolkit.getDefaultToolkit().getScreenSize().getHeight() * SCALE_SCREEN_SIZE_PERCENT);
    }

    @Override
    public void start(final Stage mainStage) {
        try {
            final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/app.fxml"));
            final Scene scene = new Scene(root);
            mainStage.setTitle(PROGRAM_NAME);
            mainStage.setScene(scene);
            mainStage.setResizable(true);
            mainStage.setOnCloseRequest(event -> {
                event.consume();
                Platform.exit();
                Runtime.getRuntime().exit(0); // NOPMD
            });
            mainStage.show();
        } catch (IOException e) {
            System.out.println(e.toString()); //NOPMD
        }
    }
}
