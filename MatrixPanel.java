package beebs.matrixcalc;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MatrixPanel extends JPanel implements ActionListener {

        private JLabel vectorName, matrixName, vectorElementX, vectorElementY, matrixElement1, matrixElement2, matrixElement3, matrixElement4;
        private JTextField vectorXField, vectorYField, matrix1Field, matrix2Field, matrix3Field, matrix4Field;
        private JButton tranformButton, resetButton;
        public static float[][] matrix = new float[2][2];
        private TransformAction tAction;

        private void initializeLabels() {
            vectorName = new JLabel("Vector 1");
            matrixName = new JLabel("Matrix Configuration");
            tranformButton = new JButton("Transform");
            resetButton = new JButton("Reset");
            vectorElementX = new JLabel("X-Value: ");
            vectorElementY = new JLabel("Y-Value: ");

            matrixElement1 = new JLabel("[L]11: ");
            matrixElement2 = new JLabel("[L]:12: ");
            matrixElement3  = new JLabel("[L]21: ");
            matrixElement4 = new JLabel("[L]22: ");    

        }

        public void initializeFields(Vector v, float[][] matrix) {
            matrix[0][0] = 1;
            matrix[1][0] = 0;
            matrix[0][1] = 0;
            matrix[1][1] = 1;
            
            vectorXField = new JTextField(4);
            vectorYField = new JTextField(4);
            matrix1Field = new JTextField(4);
            matrix2Field = new JTextField(4);
            matrix3Field = new JTextField(4);
            matrix4Field = new JTextField(4);

            vectorXField.setText(String.format("%.2f", v.x));
            vectorYField.setText(String.format("%.2f", v.y));

            matrix1Field.setText(Float.toString(matrix[0][0]));
            matrix2Field.setText(Float.toString(matrix[0][1]));
            matrix3Field.setText(Float.toString(matrix[1][0]));
            matrix4Field.setText(Float.toString(matrix[1][1]));
            }
         MatrixPanel(Vector v) {

            new JPanel();
            setBackground(Color.GRAY);
            initializeFields(v, matrix);
            initializeLabels();
             
            requestFocus();
            
            addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {

                }

                @Override
                public void focusLost(FocusEvent e) {
                    requestFocus();
                }
                
            });
            setLayout(new GridBagLayout());

            vectorXField.setEditable(true);
            vectorYField.setEditable(true);
            matrix1Field.setEditable(true);
            matrix2Field.setEditable(true);
            matrix3Field.setEditable(true);
            matrix4Field.setEditable(true);

            Action tAction = new TransformAction();

            tranformButton.addActionListener(tAction);
            resetButton.addActionListener(this);

            GridBagConstraints gbc  = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.insets = new Insets(0, 0, 0, 50);
            add(vectorName, gbc);

            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            add(vectorElementX, gbc);

            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 0, 50);
            add(vectorXField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 0, 0, 0);
            add(vectorElementY, gbc);

            gbc.gridx = 1;
            gbc.insets = new Insets(0, 0, 0, 50);
            add(vectorYField, gbc);

            gbc.gridwidth = 4;
            gbc.gridy = 0;
            gbc.gridx = 2;
            add(matrixName, gbc);

            gbc.gridwidth = 1;
            gbc.insets = new Insets(10, 0, 0, 0);
            gbc.gridx = 2;
            gbc.gridy = 1;
            add(matrixElement1, gbc);
            gbc.gridx = 3;
            gbc.insets = new Insets(0, 0, 0, 10);
            add(matrix1Field, gbc);

            gbc.gridx = 4;
            gbc.insets = new Insets(0, 0, 0, 0);
            add(matrixElement2, gbc);

            gbc.gridx = 5;
            gbc.insets = new Insets(0, 0, 0, 10);
            add(matrix2Field, gbc);

            gbc.gridy = 2;
            gbc.gridx = 2;
            gbc.insets = new Insets(0, 0, 0,0);
            add(matrixElement3, gbc);

            gbc.gridx = 3;
            gbc.insets = new Insets(0, 0, 0, 10);
            add(matrix3Field, gbc);

            gbc.gridx = 4;
            gbc.insets = new Insets(0, 0, 0,0);
            add(matrixElement4, gbc);

            gbc.gridx = 5;
            gbc.insets = new Insets(0, 0, 10, 10);
            add(matrix4Field, gbc);
            
            gbc.gridx = 6;
            gbc.gridy = 2;
            add(tranformButton, gbc);

            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(resetButton, gbc);

            addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        transformVector();
                    }
                }
    
                @Override
                public void keyPressed(KeyEvent e) {
                }
    
                @Override
                public void keyReleased(KeyEvent e) {
                }
                
            });
            
        }
        public void grabValues(float[][] m) {

            m[0][0] = Float.parseFloat(matrix1Field.getText());
            m[0][1] = Float.parseFloat(matrix2Field.getText());
            m[1][0] = Float.parseFloat(matrix3Field.getText());
            m[1][1] = Float.parseFloat(matrix4Field.getText());
            MatrixPanel.matrix = m;
            Matrix.calcDeterminant(m);
            Main.vList.get(Main.vList.size() - 1).x = Float.parseFloat(vectorXField.getText());
            Main.vList.get(Main.vList.size() - 1).y = Float.parseFloat(vectorYField.getText());

            
        }
        public void transformVector() {
            float[][] matrix = new float[2][2];
            grabValues(matrix);
            
            //adds a new vector returned by transformation method
            Main.vList.add(Matrix.transformVector(2, 2, matrix, Main.vList.get(Main.vList.size() - 1)));
            Main.vList.remove(Main.vList.get(Main.vList.size() - 2));

            System.out.println(Main.vList.get(Main.vList.size() - 1).x);
            System.out.println(Main.vList.get(Main.vList.size() - 1).y);
        
            vectorXField.setText(String.format("%.2f", Main.vList.get(Main.vList.size() - 1).x));
            vectorYField.setText(String.format("%.2f", Main.vList.get(Main.vList.size() - 1).y));

            MainFrame.gPanel.repaint();
        }
        public class TransformAction extends AbstractAction {
            public TransformAction() {
                super("Transform");
            }
            public void actionPerformed(ActionEvent e) {
                transformVector();
                
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            vectorXField.setText("1.00");
            vectorYField.setText("1.00");

            Main.vList.get(Main.vList.size() - 1).x = (float) 1.00;
            Main.vList.get(Main.vList.size() - 1).y = (float) 1.00;

            matrix1Field.setText("1.0");
            matrix2Field.setText("0.0");
            matrix3Field.setText("0.0");
            matrix4Field.setText("1.0");
            
            
            MainFrame.gPanel.repaint();
            MainFrame.gPanel.resetScale();
        }
        
}