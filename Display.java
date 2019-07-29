import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

import java.util.ArrayList;

/**
 * Write a description of class Display here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Display extends JFrame
{
    DisplayPanel displayPanel;
    /**
     * Constructor for objects of class Display
     */
    public Display(int widthPxls, int heightPxls)
    {
        super("Ray Marching Attempt 1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize((width*10), (height*10));
        displayPanel = new DisplayPanel(widthPxls, heightPxls);
        this.add(displayPanel);
        this.pack();
        
        this.setVisible(true);
    }
    public void addPixel(Face face, int x, int y){
        float[] ptClr = face.getColor();
        Color color;
        if(ptClr.length == 0){
            color = Color.BLACK;
        }else{
            color = new Color(ptClr[0], ptClr[1], ptClr[2]);
            if(face.isLit()){
                //System.out.println("brightening");
                color = color.brighter();
            }else{
                //System.out.println("darkening");
                color = color.darker();
            }
        }
        displayPanel.addPixel(color, x, y);
        
    }
    public void clearDisplay(){
        displayPanel.clearDisplay();
    }
    class DisplayPanel extends JPanel{
        private int height;
        private int width;
        private int pixelSize = 5;
        
        private ArrayList<Pixel> points;
        public DisplayPanel(int widthPxls, int heightPxls){
            this.height = heightPxls * pixelSize;
            this.width = widthPxls * pixelSize;
            
            points = new ArrayList<Pixel>();
        }
        public void addPixel(Color color, int x, int y){
            points.add(new Pixel(pixelSize*x, pixelSize*y, color));
            repaint();
        }
        public void clearDisplay(){
            points.clear();
            repaint();
        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension((width), (height));
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            AffineTransform at = new AffineTransform();
            at.translate(0, getHeight() - 1);
            at.scale(1, -1);
            g2d.setTransform(at);
            for(int i=0;i<points.size();i++){
                g2d.setColor(points.get(i).color);
                g2d.fillRect(points.get(i).x, points.get(i).y, pixelSize, pixelSize);
            }
            crossHair(g2d);
        }
        private void crossHair(Graphics2D g2d){
            g2d.setColor(Color.GRAY);
            g2d.fillRect((getWidth()/2-10),(getHeight()/2-2),20,4);
            g2d.fillRect((getWidth()/2-2),(getHeight()/2-10),4,20);
        }
        class Pixel{
            int x;
            int y;
            Color color;
            public Pixel(int x, int y, Color color){
                this.x=x;
                this.y=y;
                this.color=color;
            }
        }
    }
}
