package pl.polsl.lab.cw1.model;

import java.util.Date;

/**
 * 
 * @author Aleksander Augustyniak
 */
public class RecordDTO {
    private Long id;
    private String artistName;
    private Long leadStreams;
    private Long featStreams;
    private Long tracks;
    private Long oneBillion;
    private Long hundredMillion;
    private Date lastUpdated;
    
    public RecordDTO() {}
    
    public RecordDTO(Long id, String artistName, Long leadStreams, Long featStreams,
            Long tracks, Long oneBillion, Long oneMillion, Date lastUpdated) {
        this.id = id;
        this.artistName = artistName;
        this.leadStreams = leadStreams;
        this.featStreams = featStreams;
        this.tracks = tracks;
        this.oneBillion = oneBillion;
        this.oneBillion = oneMillion;
        this.lastUpdated = lastUpdated;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getLeadStreams() {
        return leadStreams;
    }

    public void setLeadStreams(Long leadStreams) {
        this.leadStreams = leadStreams;
    }

    public long getFeatStreams() {
        return featStreams;
    }

    public void setFeatStreams(Long featStreams) {
        this.featStreams = featStreams;
    }

    public long getTracks() {
        return tracks;
    }

    public void setTracks(Long tracks) {
        this.tracks = tracks;
    }

    public long getOneBillion() {
        return oneBillion;
    }

    public void setOneBillion(Long oneBillion) {
        this.oneBillion = oneBillion;
    }

    public long getOneMillion() {
        return hundredMillion;
    }

    public void setOneMillion(Long oneMillion) {
        this.hundredMillion = oneMillion;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    
}
