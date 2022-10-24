package pl.polsl.lab.cw1.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.table.TableModel;
import static pl.polsl.lab.cw1.constants.GlobalConstants.CSV_EXTENSION;

/**
 * One of the <b>MVC</b> classes. It contains all the data being used in the
 * application. It is communicating with View via Controller object.
 *
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public final class MainFrameModel {

    private List<RecordDTO> records = new ArrayList<>(0);
    private String[] tableHeader;
    private String[][] tableData;
    private TableModel tableModel;
    private List<CorrelationDTO> correlations;

    /**
     * Default Model component constructor.
     *
     */
    public MainFrameModel() {
    }

    /**
     * initialization method for Model component. Reads data from .csv file. Is
     * being initialized after drag&drop action on GUI.
     *
     * @param dataSourceFilePath
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     */
    public MainFrameModel(final String dataSourceFilePath)
            throws FileNotFoundException, IOException, ParseException {
        readRecordsFromFile(new File(dataSourceFilePath));
    }

    /**
     * A class for producing a Spearman's Correlation Coefficient based on
     * comparable columns' attributes – especially numerical values.The value of
     * coefficient is being stored with string values of names of columns.
     *
     * @throws java.text.ParseException
     */
    public void calculateSpearmanCorrelations() throws ParseException {

        List<Integer> comparableIndeces = Arrays.asList(0, 2, 3, 4, 5, 6);
        int listSize = comparableIndeces.size();
        correlations = new ArrayList<>();

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
                Long[] x = arrays[i];
                Long[] y = arrays[j];
                correlations.add(new CorrelationDTO(tableHeader[comparableIndeces.get(i)], tableHeader[comparableIndeces.get(j)], SpearmanCorrelation.of(x, y)));
            }
        }
    }

    /**
     *
     * @return List of correlation data objects.
     */
    public List<CorrelationDTO> getSpearmanCorrelations() {
        return correlations;
    }

    /**
     *
     * @return List of record data objects.
     */
    public List<RecordDTO> getRecords() {
        return records;
    }

    /**
     *
     * @param records Record objects setter.
     */
    public void setRecords(List<RecordDTO> records) {
        this.records = records;
    }

    /**
     *
     * @param file Csv file needed to initialize model.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void readRecordsFromFile(final File file)
            throws FileNotFoundException, IOException {
        if (!CSV_EXTENSION.equals(file.getName()
                .substring(file.getName().lastIndexOf('.')))) {
            return;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            TableParser tableParser = new TableParser(br);

            tableHeader = tableParser.getHeader();
            tableData = tableParser.getData();
        }
    }

    /**
     *
     * @return Table features array.
     */
    public String[] getTableHeader() {
        return tableHeader;
    }

    /**
     *
     * @return Table attributes.
     */
    public String[][] getData() {
        return tableData;
    }

    /**
     *
     * @return Table data model.
     */
    public TableModel getTableModel() {
        return tableModel;
    }

    /**
     *
     * @param tableModel Externally provided table data model.
     */
    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     *
     * @param correlations List of correlation data objects.
     */
    public void setCorrelations(List<CorrelationDTO> correlations) {
        this.correlations = correlations;
    }
}
