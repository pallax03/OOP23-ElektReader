package elektreader.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import elektreader.api.PlayList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/* this class represent the controller to the Playlist view, which is controlled
 * by the GUI controller class
 */
public class PlayListsController {
    /* button generated by the constructor */
    private final List<Button> btnPlaylists;
    private final SongsController songsController;
    private final VBox plistContainer;
    private final ScrollPane pane;

    /**
     * Gets the playlist list from the static method getReader of GUI Controller
     * @param playlistsList the playlists panel in which the class grafts the buttons
     * @param songsPane the songs panel to build the the new song controller
     */
    public PlayListsController(final ScrollPane playlistsPane, final ScrollPane songsPane) {

        this.btnPlaylists = new ArrayList<>(Collections.emptyList());
        FlowPane songContainer = new FlowPane();
        this.songsController = new SongsController(songContainer, songsPane);
        this.plistContainer = new VBox();
        this.pane = playlistsPane;

        songContainer.setPrefWidth(songsPane.getWidth());

        plistContainer.setPrefWidth(playlistsPane.getWidth());
        plistContainer.setSpacing(15);
    
        GUIController.getReader().getPlaylists().stream()
            .map(playlist -> createButton(playlist,songContainer))
            .forEach(button -> {
                plistContainer.getChildren().add(button);
                btnPlaylists.add(button);
            });

        this.btnPlaylists.stream()
            .forEach(btn -> btn.setPrefWidth(playlistsPane.getWidth()));
        
        playlistsPane.setContent(plistContainer);
        songsPane.setContent(songContainer);
    }

    private Button createButton(final PlayList p, final FlowPane songsList) {
        Button btnPlaylist = new Button("#"+p.getName());
        btnPlaylist.getStyleClass().add("playlistbtn");
        btnPlaylist.setOnMouseClicked(event -> {
            this.btnPlaylists.stream()
                .forEach(btn -> {
                    btn.getStyleClass().removeIf(style -> style.equals("selected"));
                });
            var btn = (Button)event.getSource();
            btn.getStyleClass().add("selected");
			if(GUIController.getReader().setCurrentPlaylist(Optional.of(p))); {
                songsController.load(p);
                responsive();
            }
		});
        return btnPlaylist;
    }

    public List<Button> getBtnPlaylists() {
        return this.btnPlaylists;
    }

    public void responsive(){
        this.plistContainer.setPrefSize(this.pane.getWidth(), this.pane.getHeight());

        this.btnPlaylists.stream()
            .forEach(btn -> btn.setPrefWidth(this.pane.getWidth()));
        
        this.songsController.responsive();
    }
}
