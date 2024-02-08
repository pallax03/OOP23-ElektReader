package elektreader.controller;


import java.util.List;
import elektreader.api.PlayList;
import elektreader.api.Song;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class SongsController {
    
    private List<VBox> btnSongs;
    private final TilePane songPane;
    private final double CONTAINER_W = 100, CONTAINER_H = 125, BTN_W = 90, BTN_H = 80,
        IMGFIT_W = 75, IMGFIT_H = 85, DEF_SPACING = 5;

    public SongsController(TilePane songContainer) {
        this.songPane = songContainer;
    }
    
    private VBox createButton(final Song song) {
        VBox container = new VBox();
        Button btn = new Button();
        ImageView img = new ImageView(ClassLoader.getSystemResource("icons/Dark/Media/AudioWave.png").toString());
        Label duration = new Label(song.DurationStringRep());
        Label title = new Label(song.getName());
        System.out.println(song.getName());
        container.setSpacing(DEF_SPACING);
        container.setMinSize(CONTAINER_W, CONTAINER_H);
        container.getStyleClass().add("songcontainer");

        btn.setAlignment(Pos.CENTER);
        btn.setPrefSize(BTN_W, BTN_H);
        btn.setStyle("-fx-background-color: transparent;");
        btn.setTextAlignment(TextAlignment.JUSTIFY);
        btn.setOnMouseClicked(event -> {
            var player = GUIController.getReader().getPlayer();
            if(player.isPresent()){
                player.get().setSong(song);
            }
        });

        img.setFitHeight(IMGFIT_W);
        img.setFitWidth(IMGFIT_H);
        img.setPreserveRatio(true);
        btn.setGraphic(img);

        container.getChildren().addAll(btn, duration, title);
        container.setVisible(true);
        return container;
    }

    public void load(PlayList playlist){
        songPane.getChildren().clear();
        this.btnSongs = playlist.getSongs().stream()
            .map(s -> createButton(s))
            .toList();
        songPane.getChildren().addAll(btnSongs);
    }
}
