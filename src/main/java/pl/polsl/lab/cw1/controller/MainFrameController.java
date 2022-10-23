package pl.polsl.lab.cw1.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pl.polsl.lab.cw1.model.MainFrameModel;
import pl.polsl.lab.cw1.view.MainFrameView;

/**
 *
 * @author Aleksander Augustyniak
 */
public class MainFrameController {
    protected MainFrameView view;
    protected MainFrameModel model;
    
    public MainFrameController() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this(new MainFrameView(), new MainFrameModel());
    }
    
    public MainFrameController(final MainFrameView view, final MainFrameModel model) {
        this.view = view;
        this.model = model;
    }
    
    public void initialize() {
        view.getFrame().setDropTarget(getDropTarget());
        view.getGenerateButton().addActionListener(getClickAction());
    }
    
    private ActionListener getClickAction() {
        return (ActionEvent e) -> {
            view.parseCorrelations(model.getSpearmanCorrelations());
            view.getCorrelationsFrame().setVisible(true);
        };
    }
    
    private DropTarget getDropTarget() {
        return new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                String errorMessage = "Wrong file extension. Should be .csv"; 
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>)
                        evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    boolean isGoodAmount = droppedFiles.size() == 1;
                    String fileName = droppedFiles.get(0).getName();
                    String fileExtension = fileName.substring(fileName.length() - 4);
                    // Successful
                    if (!fileExtension.equals(".csv"))
                        errorMessage = "File should be of .csv extension.";
                    else if (!isGoodAmount) {
                        errorMessage = "There can be imported only one .csv file.";
                    }
                    else {
                        model.readRecordsFromFile(droppedFiles.get(0));
                        TableModel tableModel = new DefaultTableModel(model.getData(), model.getTableHeader()) {
                            @Override
                            public boolean isCellEditable(int row, int column){  
                                return false;  
                            }
                        };
                        view.getTable().setModel(tableModel);
                        view.getGenerateButton().setEnabled(true);
                        model.calculateSpearmanCorrelations();
//                        view.parseCorrelations(model.getSpearmanCorrelations());
                        return;
                    }
                } catch (UnsupportedFlavorException ex) {
                    errorMessage = "Unsupported file extension.";
                } catch (IOException ex) {
                    errorMessage = "Issues with reading file.";
                } catch (ParseException ex) {
                    errorMessage = "Issues with parsing file values.";
                }
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }
}
