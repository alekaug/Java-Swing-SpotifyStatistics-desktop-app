package pl.polsl.lab.cw1.constants;

/**
 * Global constants being used in the project for paths, messages and repeatable
 * String arrays.
 *
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public interface GlobalConstants {

    /**
     * Interface containing dialog message String values.
     */
    public interface DialogMessages {

        String WRONG_EXTENSION = "Wrong file extension. Should be .csv.";
        String ONE_FILE_ALLOWED = "Only one .csv file allowed.";
        String UNSUPPORTED_FLAVOR = "Unsupported file flavor.";
        String READING_FILE_ISSUE = "Issues reading file";
        String PARSING_FILE_ISSUE = "Issues with parsing file values.";
    }
    String APPLICATION_WINDOW_TITLE = "Spotify Statistics";
    String CORRELATIONS_WINDOW_TITLE = "Correlations";
    Float FRAME_SCALE_FACTOR = 0.8f;

    String CSV_EXTENSION = ".csv";
    String RESOURCES_PATH = "";
    String DATA_SOURCE_PATH = RESOURCES_PATH + "spotify_artist_data" + CSV_EXTENSION;
    String IMAGE_PATH = RESOURCES_PATH + "Spotify-statistics-logo-smaller.png";
}