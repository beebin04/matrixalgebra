package beebs.matrixcalc;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {
        static ArrayList<Vector> vList = new ArrayList<Vector>();
    public static void main(String[] args) {
        vList.add(new Vector());
        SwingUtilities.invokeLater(() -> new MainFrame(vList.get(vList.size() - 1)));
        
    }
}