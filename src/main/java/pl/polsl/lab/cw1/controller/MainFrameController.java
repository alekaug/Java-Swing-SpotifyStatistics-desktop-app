package pl.polsl.lab.cw1.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static pl.polsl.lab.cw1.constants.GlobalConstants.CSV_EXTENSION;
import static pl.polsl.lab.cw1.constants.GlobalConstants.DialogMessages.ONE_FILE_ALLOWED;
import static pl.polsl.lab.cw1.constants.GlobalConstants.DialogMessages.PARSING_FILE_ISSUE;
import static pl.polsl.lab.cw1.constants.GlobalConstants.DialogMessages.UNSUPPORTED_FLAVOR;
import static pl.polsl.lab.cw1.constants.GlobalConstants.DialogMessages.WRONG_EXTENSION;
import pl.polsl.lab.cw1.model.MainFrameModel;
import pl.polsl.lab.cw1.view.MainFrameView;
import static pl.polsl.lab.cw1.constants.GlobalConstants.DialogMessages.READING_FILE_ISSUE;

/**
 * One of the <b>MVC</b> classes. It is used to control the program flow, where
 * data are being exchanged from/to View and/or Model objects.
 *
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public class MainFrameController {

    private static final Logger LOG = Logger.getLogger(MainFrameController.class.getName());
    private boolean isReady = false;
    private MainFrameView view;
    private MainFrameModel model;

    /**
     * Main Controller component constructor.
     */
    public MainFrameController() {
        try {
            this.view = new MainFrameView();
            this.model = new MainFrameModel();
            isReady = true;
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            LOG.log(Level.SEVERE, ex.toString());
        }
    }

    public MainFrameController(final MainFrameView view, final MainFrameModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Main initialization method for Controller component.
     */
    public void initialize() {
        view.getFrame().setDropTarget(getDropTarget());
        view.getGenerateButton().addActionListener(getClickAction());
    }

    /**
     *
     * @return Logic for click event for Spearman correlations generator.
     */
    private ActionListener getClickAction() {
        return (ActionEvent e) -> {
            view.parseCorrelations(model.getSpearmanCorrelations());
            view.getCorrelationsFrame().setVisible(true);
        };
    }

    /**
     *
     * @return Logic for drop target event.
     */
    private DropTarget getDropTarget() {
        return new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    boolean isGoodAmount = droppedFiles.size() == 1;
                    String fileName = droppedFiles.get(0).getName();
                    String fileExtension = fileName.substring(fileName.length() - 4);

                    if (!CSV_EXTENSION.equals(fileExtension)) {
                        showMessageDialog(WRONG_EXTENSION);
                    } else if (!isGoodAmount) {
                        showMessageDialog(ONE_FILE_ALLOWED);
                    } else {
                        model.readRecordsFromFile(droppedFiles.get(0));
                        TableModel tableModel = new DefaultTableModel(model.getData(), model.getTableHeader()) {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        view.getTable().setModel(tableModel);
                        view.getGenerateButton().setEnabled(true);
                        model.calculateSpearmanCorrelations();
                        LOG.log(Level.INFO, "Drop target method initialization success.");
                    }
                } catch (UnsupportedFlavorException ex) {
                    showMessageDialog(UNSUPPORTED_FLAVOR);
                } catch (IOException ex) {
                    showMessageDialog(READING_FILE_ISSUE);
                } catch (ParseException ex) {
                    showMessageDialog(PARSING_FILE_ISSUE);
                }
                LOG.log(Level.SEVERE, "Drop target method ended unsuccessfully.");
            }
        };
    }

    /**
     *
     * @param errorMessage Message being displayed in a dialog window.
     */
    private void showMessageDialog(String errorMessage) {
        JOptionPane.showMessageDialog(view.getFrame(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * The method is used in the main application's class to check if the
     * program is able to be used by the user.
     *
     * @return current state of the controller object.
     */
    public boolean getReadyState() {
        return isReady;
    }
}
