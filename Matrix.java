package beebs.matrixcalc;
import java.util.Scanner;
import java.util.Random;

public class Matrix {
   static Scanner scnr = new Scanner(System.in);
   static Random rand = new Random();

    public static int columns;
    public static int rows;
   // private static float determinant;

    public static float[][] initializeMatrix(float[][] matrix, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = rand.nextFloat(11) - 5;
            }
        }
        displayMatrix(matrix, rows, columns);
        return matrix;
    }

    private static void displayMatrix(float[][] matrix, int rows, int columns) {

        System.out.println("---------------");
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {
                System.out.printf("%-6.2f|", matrix[i][j]);
            }
            System.out.println();
            System.out.println("---------------");
        }

    }
    public static Vector transformVector(int matRows, int matCols, float[][] matrix, Vector vector) {
        float[][] vecElements = new float[2][1];
        vecElements[0][0] = vector.x;
        vecElements[1][0] = vector.y;
        vecElements = multiplyMatrix(matRows, matRows, 1, matCols, vecElements, matrix);
        return new Vector(vecElements[0][0], vecElements[1][0]);
    }
    public static float[][] multiplyMatrix(int rowsA, int rowsB, int columnsA, int columnsB, float[][] A, float[][] B) {
       boolean isPossible = true;

       float[][] C = new float[2][1];

       while(isPossible == true) {

        if (rowsA != columnsB) {
            System.out.println("NOT POSSIBLE");
            isPossible = false;
        }
        C = new float[rowsB][columnsA];

            for (int i = 0; i < rowsB; i++) {
                for (int j = 0; j < columnsA; j++) {
                    for (int k = 0; k < rowsA; k++) {
                        C[i][j] += B[i][k] * A[k][j];
                    }
                } 
            }
            isPossible = false;
        }
        System.out.println("\n\n\n\n\n");
        System.out.println("Matrix B X Matrix A:");
        System.out.println();
        displayMatrix(B, rowsB, columnsB);
        System.out.println("X");
        displayMatrix(A, rowsA, columnsA);
        System.out.println();
        System.out.println("||Resultant Matrix:\n");
        displayMatrix(C, rowsB, columnsA);
        return C;
    }
    public static double calcDeterminant(float[][] m) {
        double determinant = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        System.out.println(determinant);
        return determinant;
    }
}
