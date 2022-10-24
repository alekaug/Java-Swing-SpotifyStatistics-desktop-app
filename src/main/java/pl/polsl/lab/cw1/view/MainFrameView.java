package pl.polsl.lab.cw1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import static pl.polsl.lab.cw1.constants.GlobalConstants.*;
import static java.awt.RenderingHints.*;
import java.util.ArrayList;
import java.util.List;
import static java.lang.String.format;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pl.polsl.lab.cw1.model.CorrelationDTO;

/**
 * One of the <b>MVC</b> classes. It contains GUI objects and methods being used
 * by Controller to make it being able to communicate with model object, which
 * holds the proper data.
 *
 * @author Aleksander Augustyniak
 * @version 1.0
 */
public class MainFrameView {

    private JFrame mainFrame;                   // Main application frame.
    private JFrame correlationsFrame;           // Correlations frame.
    private JTable table;                       // Main frame's table.
    private JPanel background;                  // Main frame's background.
    private JPanel header;                      // Main frame's header.
    private JPanel body;                        // Main frame's body.
    private JPanel footer;                      // Main frame's footer.
    private JButton generateButton;             // Visible under table.
    private List<List<JLabel>> correlationView; // List of correlation elements.
    /**
     * Colors' coefficients range array
     */
    private static final Color[] colors = new Color[]{
        new Color(0, 153, 0),
        new Color(0, 204, 0),
        new Color(102, 153, 0),
        new Color(153, 204, 0),
        new Color(204, 204, 0),
        new Color(255, 255, 0),
        new Color(255, 204, 0),
        new Color(255, 153, 0),
        new Color(255, 102, 0),
        new Color(255, 0, 0)
    };

    /**
     * View component constructor.
     * 
     * @throws UnsupportedLookAndFeelException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public MainFrameView() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initialize();
    }

    /**
     * Main initialization method for View component.
     *
     * @throws UnsupportedLookAndFeelException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void initialize() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFrame = initializeMainFrame();
        correlationsFrame = initializeCorrelationsFrame();
        header = initializeHeader();
        body = initializeBody();
        footer = initializeFooter();
        background = initializeBackground();
        table = initializeTable();

        Container pane = mainFrame.getContentPane();
        pane.add(background);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        background.add(header, gbc);

        // body
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        background.add(body, gbc);
        JTextField searchBar = new JTextField(40);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 6;
        body.add(searchBar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.gridwidth = 6;
        JScrollPane scrollPane = new JScrollPane(table);
        body.add(scrollPane, gbc);
        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        generateButton = initializeGenerateButton();
        body.add(generateButton, gbc);

        // footer
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        background.add(footer, gbc);

//        frame.pack();
        mainFrame.setSize(new Dimension((int) (getEfficientWidth() * FRAME_SCALE_FACTOR), (int) (getEfficientHeight() * (FRAME_SCALE_FACTOR + 0.05))));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     *
     * @return Table with records.
     */
    private JTable initializeTable() {
        JTable table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setResizingAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        return table;
    }

    /**
     *
     * @return Main application frame.
     * @throws UnsupportedLookAndFeelException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private JFrame initializeMainFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame(APPLICATION_WINDOW_TITLE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension((int) (getEfficientWidth() * FRAME_SCALE_FACTOR), (int) (getEfficientHeight() * FRAME_SCALE_FACTOR)));
        frame.setIconImage(new ImageIcon(IMAGE_PATH).getImage());
        return frame;
    }

    /**
     *
     * @return Frame with calculated correlations for each comparable attribute.
     */
    private JFrame initializeCorrelationsFrame() {
        JFrame frame = new JFrame(CORRELATIONS_WINDOW_TITLE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon(IMAGE_PATH).getImage());
        frame.setVisible(false);
        frame.setResizable(false);
        return frame;
    }

    /**
     * Correlation frame content setter using known field in class.
     *
     * @see correlationView
     */
    private void setCorrelationsFrameContent() {
        class JPanelElement extends JPanel {

            public JPanelElement(JLabel left, JLabel right) {
                super(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.ipadx = 20;
                gbc.ipady = 5;
                gbc.gridx = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                this.add(left, gbc);
                gbc.gridx = 1;
                this.add(right, gbc);
            }
        }

        correlationsFrame.dispose();
        correlationsFrame = initializeCorrelationsFrame();// reinitialization
        for (List<JLabel> correlationElement : correlationView) {
            correlationsFrame.getContentPane().add(new JPanelElement(
                    correlationElement.get(0), correlationElement.get(1)));
        }
        correlationsFrame.pack();
    }

    /**
     *
     * @return frame of feature correlation coefficients
     */
    public JFrame getCorrelationsFrame() {
        return correlationsFrame;
    }

    private JPanel initializeBackground() {
        return new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);

                Color upperColor = new Color(250, 187, 255);
                Color lowerColor = new Color(208, 238, 255);

                GradientPaint gradientPaint
                        = new GradientPaint(0.f, 0.f, upperColor, 0.f, (float) mainFrame.getHeight(), lowerColor);

                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
            }
        };
    }

    private JButton initializeGenerateButton() {
        JButton generateButton = new JButton("Generate Correlations");
        generateButton.setEnabled(false);
        return generateButton;
    }

    private JPanel initializeHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        header.setBackground(new Color(0, 0, 0, 0));
        ImageIcon imageIcon = new ImageIcon(IMAGE_PATH);
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        JLabel applicationNameText = new JLabel("<html><font family=Arial color=black size=20>Spotify Statistics</html>", imageIcon, SwingConstants.LEFT);
        header.add(applicationNameText);
        return header;
    }

    /**
     *
     * @return Initialized application's body.
     */
    private JPanel initializeBody() {
        JPanel body = new JPanel(new GridBagLayout());
        body.setBackground(new Color(0, 0, 0, 0));
        return body;
    }

    private JPanel initializeFooter() {
        JPanel footer = new JPanel(new GridBagLayout());

        return footer;
    }

    /**
     *
     * @return Data table, visible in the main frame.
     */
    public JTable getTable() {
        return table;
    }

    /**
     * The method used to set the table data. Is used by model object.
     *
     * @param table Table, where data is visible in the main frame.
     */
    public void setTable(JTable table) {
        this.table = table;
    }

    private int getEfficientWidth() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration().getBounds().width;
    }

    private int getEfficientHeight() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration().getBounds().height;
    }

    /**
     *
     * @return Main application's frame.
     */
    public JFrame getFrame() {
        return mainFrame;
    }

    /**
     *
     * @return button of coefficients generator view.
     */
    public JButton getGenerateButton() {
        return generateButton;
    }

    /**
     * The method <i>translates</i> data object to the object being shown in the
     * GUI.
     *
     * @param correlations List of correlation objects
     */
    public void parseCorrelations(List<CorrelationDTO> correlations) {
        correlationView = new ArrayList<>(correlations.size());
        correlations.forEach(c -> {
            ArrayList<JLabel> labels = new ArrayList<>(2);
            JLabel leftText = new JLabel(c.getSecondFeatureName() + " : " + c.getFirstFeatureName());
            JLabel rightText = new JLabel(format("%.2f", c.getCorrelationValue()));
            rightText.setBackground(correlationLabelColor(rightText.getText()));
            rightText.setOpaque(true);
            labels.add(leftText);
            labels.add(rightText);
            correlationView.add(labels);
        });
        setCorrelationsFrameContent();
    }

    /**
     *
     * @param valueText A value of correlation, represented as a String object.
     * @return A color representing correlation level.
     */
    private Color correlationLabelColor(String valueText) {
        Double value = Double.valueOf(valueText);

        if (value > 0.8) {
            return colors[0];
        } else if (value > 0.6) {
            return colors[1];
        } else if (value > 0.4) {
            return colors[2];
        } else if (value > 0.2) {
            return colors[3];
        } else if (value > 0.0) {
            return colors[4];
        } else if (value > -0.2) {
            return colors[5];
        } else if (value > -0.4) {
            return colors[6];
        } else if (value > -0.6) {
            return colors[7];
        } else if (value > -0.8) {
            return colors[8];
        }

        return colors[9];
    }
}
