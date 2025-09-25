package beebs.matrixcalc;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GraphPanel extends JPanel {
    private float scaleFac = (float) 1.0;
    public GraphPanel(ArrayList<Vector> l) {
        
        setPreferredSize(new Dimension(675, 675));
        setBorder(new LineBorder(Color.lightGray, 10));
        setBackground(Color.DARK_GRAY);
        addMouseWheelListener(e -> {
            int n = e.getWheelRotation();
            if (n < 0) {
                scaleFac *= (float) (Math.pow(1.1, n));
            }
            else if (n > 0) {
                scaleFac *= (float) (Math.pow(1.1, n));
            }
            
            repaint();
        });
    }
    // x-axis coord constants
    public static final int X_AXIS_FIRST_X_COORD = 50;
    public static final int X_AXIS_SECOND_X_COORD = 650;
    public static final int X_AXIS_Y_COORD = 350;

    public static final int PANEL_WIDTH = 700;
    public static final int PANEL_HEIGHT = 700;

    // y-axis coord constants
    public static final int Y_AXIS_FIRST_Y_COORD = 50;
    public static final int Y_AXIS_SECOND_Y_COORD = 650;
    public static final int Y_AXIS_X_COORD = 350;

    public static final int SECOND_LENGHT = 5;
    public static final int FIRST_LENGHT = 10;
    // size of start coordinate lenght
    public static final int ORIGIN_COORDINATE_LENGHT = 6;

    // distance of coordinate strings from axis
    public static final int AXIS_STRING_DISTANCE = 20;

    public int xAxisStart = (int) (X_AXIS_FIRST_X_COORD / scaleFac);
    public int xAxisEnd = (int) (X_AXIS_SECOND_X_COORD * scaleFac);

    public int yAxisStart = (int) (Y_AXIS_FIRST_Y_COORD / scaleFac);
    public int yAxisEnd = (int) (Y_AXIS_SECOND_Y_COORD * scaleFac);
    public void resetScale() {
        scaleFac = (float) 1.0;
    }
    //converts vector coordinates to pixels
    private void drawVectorOnPanel(Vector vector, Graphics g2) {
        final int x = 350 + (int) ((vector.x) * ((X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / 10) * scaleFac); 
        final int y = 350 - (int) ((vector.y) * ((Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / 10) * scaleFac);
        g2.setColor(Color.RED);
        g2.drawLine(350, 350, x, y);
        g2.drawString(" Matrix Determinant: ", 20, 40);
        g2.drawString(Double.toString(Matrix.calcDeterminant(MatrixPanel.matrix)), 130, 41);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //x-axis
        g2.setColor(Color.WHITE);
        g2.drawLine(xAxisStart, X_AXIS_Y_COORD, xAxisEnd, X_AXIS_Y_COORD);
        //y-axis
        g2.drawLine(Y_AXIS_X_COORD, yAxisStart, Y_AXIS_X_COORD, yAxisEnd);

        // x-axis arrow
        g2.drawLine(xAxisEnd - FIRST_LENGHT, X_AXIS_Y_COORD - SECOND_LENGHT, xAxisEnd, X_AXIS_Y_COORD);
        g2.drawLine(xAxisEnd - FIRST_LENGHT, X_AXIS_Y_COORD + SECOND_LENGHT, xAxisEnd, X_AXIS_Y_COORD);

        // y-axis arrow 
        g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, yAxisStart + FIRST_LENGHT, Y_AXIS_X_COORD, yAxisStart);           
        g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, yAxisStart + FIRST_LENGHT, Y_AXIS_X_COORD, yAxisStart);
        
        g2.drawString("X", xAxisEnd - AXIS_STRING_DISTANCE / 2, X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, yAxisEnd + AXIS_STRING_DISTANCE / 2);
        
        Main.vList.forEach(v -> drawVectorOnPanel(v, g2));
  
        // numerate axis
        int xCoordNumbers = 10;
        int yCoordNumbers = 10;

        int xLength = (xAxisEnd - xAxisStart) / xCoordNumbers;
        int yLength = (yAxisEnd - yAxisStart) / yCoordNumbers;
        
        // draw x-axis numbers
        g2.setColor(Color.white);
        for(int i = 0; i < xCoordNumbers / 2; i++) {
            g2.drawLine(xAxisStart + (i * xLength), X_AXIS_Y_COORD - SECOND_LENGHT, xAxisStart + (i * xLength), X_AXIS_Y_COORD + SECOND_LENGHT);
            g2.drawString(String.format("%.2f",i - 5 / scaleFac),  xAxisStart + (i * xLength) - 3, X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
            g2.drawLine(((xAxisStart + xAxisEnd) / 2) + (i * xLength), X_AXIS_Y_COORD - SECOND_LENGHT, ((xAxisStart + xAxisEnd) / 2) + (i * xLength), X_AXIS_Y_COORD + SECOND_LENGHT);
            g2.drawString(String.format("%.2f",i / scaleFac),  ((xAxisStart + xAxisEnd) / 2) + (i * xLength) - 3, X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        }
        //draw y-axis numbers
        for(int i = 0; i < yCoordNumbers / 2; i++) {
            g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, yAxisEnd - (i * yLength),  Y_AXIS_X_COORD + SECOND_LENGHT, yAxisEnd - (i * yLength));
            g2.drawString(String.format("%.2f", i - 5 / scaleFac),  Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,  yAxisEnd - (i * yLength));
            g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, ((yAxisEnd + yAxisStart) / 2) - (i * yLength), Y_AXIS_X_COORD + SECOND_LENGHT,  ((yAxisEnd + yAxisStart) / 2) - (i * yLength));
            g2.drawString(String.format("%.2f", i / scaleFac), Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, ((yAxisEnd + yAxisStart) / 2) - (i * yLength));
        }
    }
}
