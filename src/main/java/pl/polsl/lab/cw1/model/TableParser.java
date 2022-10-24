package pl.polsl.lab.cw1.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Method for translating data based on .csv file extension to java-based 
 * data structure.
 * 
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public class TableParser {

    String[] header;
    String[][] data;

    private TableParser() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * The class' constructor.
     * 
     * @param bufferedReader
     * @throws IOException 
     */
    public TableParser(final BufferedReader bufferedReader) throws IOException {
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withHeader());

        List<CSVRecord> csvRecords = csvParser.getRecords();

        int rowsCount = csvRecords.size() - 1;
        int columnsCount = csvRecords.get(0).size();

        header = new String[columnsCount];
        data = new String[rowsCount][columnsCount];
        csvParser.getHeaderMap().keySet().toArray(header);
//        header = csvRecords.get(0).toMap().values().toArray(header);
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
            CSVRecord currentRecord = csvRecords.get(rowIndex);
            data[rowIndex] = parseLine(currentRecord);
        }
    }

    /**
     * 
     * @return Table columns' names.
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * 
     * @return Table data.
     */
    public String[][] getData() {
        return data;
    }

    /**
     * 
     * @param csvRecord A csv record that is being currently processed.
     * @return Array of row values represented by String objects.
     */
    private static String[] parseLine(final CSVRecord csvRecord) {
        int recordSize = csvRecord.size();
        String[] parsedLine = new String[recordSize];

        for (int i = 0; i < recordSize; i++) {
            parsedLine[i] = csvRecord.get(i);
        }
        return parsedLine;
    }
}
