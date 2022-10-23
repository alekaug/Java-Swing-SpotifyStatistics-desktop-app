package pl.polsl.lab.cw1.constants;

import java.net.URI;
import java.nio.file.Path;

/**
 *
 * @author Aleksander Augustyniak
 */
public interface GlobalConstants {
    String APPLICATION_WINDOW_TITLE = "Spotify Statistics";
    String CORRELATIONS_WINDOW_TITLE = "Correlations";
    Float FRAME_SCALE_FACTOR = 0.8f;
    
    String RESOURCES_PATH = "src/main/resources/";
    String DATA_SOURCE_PATH = RESOURCES_PATH + "spotify_artist_data.csv";
    String IMAGE_PATH = RESOURCES_PATH + "Spotify-statistics-logo-smaller.png";
}
