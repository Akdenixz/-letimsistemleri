package com.example;

import java.util.Arrays;

public class MatrixMultiplication {
    static final int SIZE = 5;
    static int[][] A = new int[SIZE][SIZE];
    static int[][] B = new int[SIZE][SIZE];
    static int[][] C = new int[SIZE][SIZE];

    public static void main(String[] args) throws InterruptedException {
        MatrixDoldur(A);
        MatrixDoldur(B);

        Thread[] threads = new Thread[SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            final int row = i;
            threads[i] = new Thread(() -> computeRow(row));
            threads[i].start();
        }

        for (int i = 0; i < SIZE; i++) {
            threads[i].join();
        }

        MatrixYaz(C);
    }

    static void MatrixDoldur(int[][] matrix) {
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = value++;
            }
        }
    }

    static void computeRow(int row) {
        for (int j = 0; j < SIZE; j++) {
            C[row][j] = 0;
            for (int k = 0; k < SIZE; k++) {
                C[row][j] += A[row][k] * B[k][j];
            }
        }
    }

    static void MatrixYaz(int[][] matrix) {
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}
