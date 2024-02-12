package elektreader.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import elektreader.api.PlayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private final Label desc;
    /* to implement the switch between song views */
    private PlayList current;
    private boolean onIcons = true;

    /**
     * Gets the playlist list from the static method getReader of GUI Controller
     * @param playlistsList the playlists panel in which the class grafts the buttons
     * @param songsPane the songs panel to build the the new song controller
     * @param desc the description label on the song panel, it lists the number of songs in the playlist and,
     * also, the name of the playlist
     */
    public PlayListsController(final ScrollPane playlistsPane, final ScrollPane songsPane, final Label desc, final MediaControlsController mediaControl) {

        this.btnPlaylists = new ArrayList<>(Collections.emptyList());
        this.desc = desc;
        FlowPane songContainer = new FlowPane();
        this.songsController = new SongsController(songContainer, songsPane, mediaControl);
        this.plistContainer = new VBox();
        /* now the playlist container will keep its children resized to its current width */
        this.plistContainer.setFillWidth(true);

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
        songsPane.setOnMouseEntered(event -> responsive());
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
                /* i should probably alert the MediaControlsController class to get the playlist, still to decide... */
                this.current = p;
                songsController.load(p, onIcons);
                this.desc.setText(" - "+p.getSize()+" - "+p.getName());
                responsive();
            }
		});
        return btnPlaylist;
    }

    public List<Button> getBtnPlaylists() {
        return this.btnPlaylists;
    }

    /**
     * adjusts the size of panels and relative content, also call responsive for the songs controller
     */
    public void responsive(){
        this.plistContainer.fillWidthProperty();
        this.songsController.responsive();
    }

    /**
     * switches and reloads the type of view, from icons to list or viceversa
     */
    public void switchView(){
        onIcons = !onIcons;
        songsController.load(current, onIcons);
    }
}
