package beebs.matrixcalc;

import java.awt.BorderLayout;

import javax.swing.JFrame;



public class MainFrame extends JFrame {
    
    final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 850;
    public static GraphPanel gPanel;
    public static MatrixPanel mPanel;

    public MainFrame(Vector v) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Vector Transformation");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        gPanel = new GraphPanel(Main.vList);
        mPanel = new MatrixPanel(v);
        add(gPanel, BorderLayout.NORTH);
        add(mPanel, BorderLayout.SOUTH);
        setFocusable(true);
        mPanel.setFocusable(true);
        pack();
    }
}
