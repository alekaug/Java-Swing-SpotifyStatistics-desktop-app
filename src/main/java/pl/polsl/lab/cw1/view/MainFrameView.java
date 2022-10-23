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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
 *
 * @author Aleksander Augustyniak
 */
public class MainFrameView {
    private JFrame mainFrame;
    private JFrame correlationsFrame;
    private JTable table;    
    private JPanel background;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JButton generateButton;
    private List<List<JLabel>> correlationView;
    
    public MainFrameView() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initialize();
    }
    
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
        background.add(header,gbc);
        
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
        background.add(footer,gbc);
        
//        frame.pack();
        mainFrame.setSize(new Dimension((int)(getEfficientWidth() * FRAME_SCALE_FACTOR), (int)(getEfficientHeight() * (FRAME_SCALE_FACTOR + 0.05))));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
    private JTable initializeTable() {
        JTable table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setResizingAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        return table;
    }
    
    private JFrame initializeMainFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame(APPLICATION_WINDOW_TITLE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension((int)(getEfficientWidth() * FRAME_SCALE_FACTOR), (int)(getEfficientHeight() * FRAME_SCALE_FACTOR)));
        frame.setIconImage(new ImageIcon(IMAGE_PATH).getImage());
        return frame;
    } 
    
    private JFrame initializeCorrelationsFrame() {
        JFrame frame = new JFrame(CORRELATIONS_WINDOW_TITLE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setLayout(new BoxLayout(scrollPane, BoxLayout.PAGE_AXIS));
//        frame.add(scrollPane);
        
        
                
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(IMAGE_PATH).getImage());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(500, 350));
        return frame;
    }
    
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
        
        for (List<JLabel> correlationElement : correlationView) {
            correlationsFrame.getContentPane().add(new JPanelElement(
                    correlationElement.get(0), correlationElement.get(1)));
        }
    }
    
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
                
                GradientPaint gradientPaint = 
                        new GradientPaint(0.f, 0.f, upperColor, 0.f, (float) mainFrame.getHeight(), lowerColor);
                
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
        header.setBackground(new Color(0,0,0,0));
        ImageIcon imageIcon = new ImageIcon(IMAGE_PATH);
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(80, 80,  Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        JLabel applicationNameText = new JLabel("<html><font family=Arial color=black size=20>Spotify Statistics</html>", imageIcon, SwingConstants.LEFT);
        header.add(applicationNameText);
        return header;
    }
    
    private JPanel initializeBody() {
        JPanel body = new JPanel(new GridBagLayout());
        body.setBackground(new Color(0, 0, 0, 0));
        return body;
    }
    
    private JPanel initializeFooter() {
        JPanel footer = new JPanel(new GridBagLayout());
        
        return footer;
    }
    
    public JTable getTable() {
        return table;
    }

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
    
    public JFrame getFrame() {
        return mainFrame;
    }
    
    public JButton getGenerateButton() {
        return generateButton;
    }
    
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
    
    private Color [] colors = new Color[]{
        new Color(204, 16, 16),
        new Color(122, 201, 56),
        new Color(192, 224, 117),
        new Color(226, 240, 175),
        new Color(235, 235, 235),
        new Color(242, 237, 187),
        new Color(224, 203, 117),
        new Color(201, 124, 56),
        new Color(35, 204, 16),
        new Color(219, 65, 37)            
    };
    
    private Color correlationLabelColor(String valueText) {
        Double value = Double.valueOf(valueText);
        
        if (value > 0.8)
            return colors[0];
        else if (value > 0.6)
            return colors[1];
        else if (value > 0.4)
            return colors[2];
        else if (value > 0.2)
            return colors[3];
        else if (value > 0.0)
            return colors[4];
        else if (value > -0.2)
            return colors[5];
        else if (value > -0.4)
            return colors[6];
        else if (value > -0.6)
            return colors[7];
        else if (value > -0.8)
            return colors[8];
        return colors[9];
    }
}
