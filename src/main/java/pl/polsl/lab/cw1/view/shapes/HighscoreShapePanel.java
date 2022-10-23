/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.cw1.view.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Aleksander Augustyniak
 */
public class HighscoreShapePanel extends JPanel {
    
    
    public HighscoreShapePanel() {
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // 1st Rectangle
        RoundRectangle2D rect1 = new RoundRectangle2D.Double(100, 50, 50, 90, 5, 5);
        
        g2d.setColor(Color.YELLOW);
        g2d.fill(rect1);
        
        g2d.setColor(Color.BLACK);
        g2d.draw(rect1);
        
        // 2nd Rectangle
        RoundRectangle2D rect2 = new RoundRectangle2D.Double(30, 50, 50, 90, 5, 5);
        
        g2d.setColor(Color.GRAY);
        g2d.fill(rect2);
        
        g2d.setColor(Color.BLACK);
        g2d.draw(rect2);
        
        // 3rd Rectangle
        RoundRectangle2D rect3 = new RoundRectangle2D.Double(170, 50, 50, 90, 5, 5);
        
        g2d.setColor(new Color(105, 61, 27)); // BROWN
        g2d.fill(rect3);
        
        g2d.setColor(Color.BLACK);
        g2d.draw(rect3);
    }
    
}
