package pl.polsl.lab.cw1.model;

import static java.lang.String.format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.table.TableModel;

/**
 *
 * @author Aleksander Augustyniak
 */
public class MainFrameModel {
    private static final String CSV_EXTENSION = ".csv";
    private List<RecordDTO> records = new ArrayList<>(0);
    private String[] tableHeader;
    private String[][] tableData;
    private TableModel tableModel;
    private List<CorrelationDTO> correlations;
    
    public MainFrameModel() { }

    public MainFrameModel(final String dataSourceFilePath) 
            throws FileNotFoundException, IOException, ParseException {
        readRecordsFromFile(new File(dataSourceFilePath));
    }
    
    public MainFrameModel(final URI dataSourceUri)
            throws IOException, FileNotFoundException, ParseException {
        readRecordsFromFile(new File(dataSourceUri));
    }
    
    // business logic
    public Float calculateAverage() {
        return 0.0f;
    }
    public String getMostLikedArtist() {
        return "";
    }
    public Long calculateMin() {
        return 0L;
    }
    public Long calculateMax() {
        return 0L;
    }
    
    /**
     * A class for producing a Spearman's Correlation Coefficient based on
     * comparable columns' attributes – especially numerical values.
     * The value of coefficient is being stored with string values of names of
     * columns.
     */
    public void calculateSpearmanCorrelations() throws ParseException {
        
        List<Integer> comparableIndeces = Arrays.asList(0, 2, 3, 4, 5, 6);
        int listSize = comparableIndeces.size();
        correlations = new ArrayList<>(tableData.length); // TODO poprawić, bo kombinacja bez powtórzen
        
        Long[][] arrays = new Long[listSize][tableData.length];
        NumberFormat localeFormat = NumberFormat.getInstance(Locale.US);
        
        int currentX = 0;
        for (Integer x : comparableIndeces) {
            for (int y = 0; y < tableData.length; y++) {
                arrays[currentX][y] = localeFormat.parse(tableData[y][x]).longValue();
            }
            currentX++;
        }
        
        for (int i = 0; i < listSize; i++) {
            for (int j = i + 1; j < listSize && j > i; j++) {
                Long [] x = arrays[i];
                Long [] y = arrays[j];
                correlations.add(new CorrelationDTO(tableHeader[comparableIndeces.get(i)], tableHeader[comparableIndeces.get(j)], SpearmanCorrelation.of(x, y)));
            }
        }
    }
    
    public List<CorrelationDTO> getSpearmanCorrelations() {
        return correlations;
    }
    
    private Long[] convertArrayToLong(String[] stringArray) throws NumberFormatException {
        int arrayLength = stringArray.length;
        Long[] longArray = new Long[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            longArray[i] = Long.valueOf(stringArray[i]);
        }
        return longArray;
    }
    
    public List<RecordDTO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordDTO> records) {
        this.records = records;
    }
    
    public void readRecordsFromFile(final File file) 
            throws FileNotFoundException, IOException {
        if (!CSV_EXTENSION.equals(file.getName()
                .substring(file.getName().lastIndexOf('.')))) {
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        TableParser tableParser = new TableParser(br);
        
        tableHeader = tableParser.getHeader();
        tableData = tableParser.getData();
        
        br.close();
    }
    
    public String[] getTableHeader() {
        return tableHeader;
    }
    
    public String[][] getData() {
        return tableData;
    }
    
    public TableModel getTableModel() {
        return tableModel;
    }
    
    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public void setCorrelations(List<CorrelationDTO> correlations) {
        this.correlations = correlations;
    }
}
