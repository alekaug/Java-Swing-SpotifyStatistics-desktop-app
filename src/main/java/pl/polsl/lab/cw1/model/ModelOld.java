package pl.polsl.lab.cw1.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Aleksander Augustyniak
 */
public class ModelOld {
//    private static final String CSV_EXTENSION = ".csv";
//    
//    private ModelOld() {
//        throw new UnsupportedOperationException();
//    }
//    
//    public ModelOld(final String dataSourceFilePath) 
//            throws FileNotFoundException, IOException, ParseException {
//        loadRecordsFromFile(dataSourceFilePath);
//    }
//    
//    private List<RecordDTO> records;
//    
//    // business logic
//    public Float calculateAverage() {
//        return 0.0f;
//    }
//    public String getMostLikedArtist() {
//        return "";
//    }
//    public Long calculateMin() {
//        return 0L;
//    }
//    public Long calculateMax() {
//        return 0L;
//    }
//    public Long calculateSpearmanCorrelation() {
//        return 0L;
//    }
//    
//    // data source
//    public void loadRecordsFromFile(final String filePath) 
//            throws FileNotFoundException, IOException, ParseException {
//        File file = new File(filePath);
//        if (!CSV_EXTENSION.equals(file.getName()
//                .substring(file.getName().lastIndexOf('.')))) {
//            return;
//        }
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        
//        CSVParser csvParser = new CSVParser();
//        String[] lines = (String[]) br.lines().toArray();
//        this.records = new ArrayList<>(lines.length);
//        for (String line : lines) {
//            String[] values = csvParser.parseLine(line);
//            if("".equals(values[0])) continue;
//            
//            NumberFormat localeFormat = NumberFormat.getInstance(Locale.FRANCE);
//            RecordDTO record = new RecordDTO();
//            record.setId(Long.parseLong(values[0]));
//            record.setArtistName(values[1]);
//            record.setLeadStreams(localeFormat.parse(values[2]).longValue());
//            record.setFeatStreams(localeFormat.parse(values[3]).longValue());
//            record.setTracks(localeFormat.parse(values[4]).longValue());
//            record.setOneBillion(localeFormat.parse(values[5]).longValue());
//            record.setOneMillion(localeFormat.parse(values[6]).longValue());
//            record.setLastUpdated(new SimpleDateFormat("dd.MM.yy").parse(values[7]));
//        
//            this.records.add(record);
//        }
//    }
}
