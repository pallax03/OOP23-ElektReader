package elektreader.api;

import java.nio.file.Path;
import java.util.Optional;
import java.util.List;

/**
 * A class which represents the concept of a playlist of songs.
 */
public interface PlayList {

    /**
     * @return the path of the playlist location (the directory location)
     */
    Path getPath();

    /**
     * @param index the position of the song in the queue
     * @return the the song at the specified position
     */
    Optional<Song> getSong(int index);

    /**
     * @return the list of songs contained in the playlist, this is not used in
     * the actual reproduction
     */
    List<Song> getSongs();

    /**
     * @param path the path of the song to searched in this playlist
     * @return an Optional of the song is present, Optional.empty otherwise
     */
    Optional<Song> getSong(Path path);

    /**
     * @return the name of the current playlist, which is also the directory name
     */
    String getName();

    /**
     * @return the complessive duration of all the songs in this playlist in the 
     * time stamp format h:mm:ss
     */
    String getTotalDuration();

    /**
     * @return the number of songs contained in this playlist
     */
    int getSize();
 
}
