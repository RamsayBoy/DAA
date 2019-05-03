package segundoParcial;

import java.util.Scanner;

public class LaberintoCorto {
    private static final int VACIO = 0;

    private static int numCeldasMin;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int dimension = input.nextInt();
        int[][] laberinto = new int[dimension][dimension];

        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                laberinto[i][j] = input.nextInt();
            }
        }

        numCeldasMin = dimension * dimension + 1;

        resolver(laberinto);

        if(numCeldasMin == dimension * dimension + 1) {
            System.out.println("SIN SOLUCION");
        }
        else {
            System.out.println(numCeldasMin);
        }
    }

    private static void resolver(int[][] laberinto) {
        resolver(laberinto, 0, 0, 1);
    }

    private static void resolver(int[][] laberinto, int col, int fila, int celda) {
        laberinto[col][fila] = celda;

        if(acabado(laberinto, col, fila)) {
            if(celda < numCeldasMin) {
                numCeldasMin = celda;
            }
        }
        else {
            if(esFactible(laberinto, col + 1, fila)) {
                resolver(laberinto, col + 1, fila, celda + 1);
                laberinto[col + 1][fila] = VACIO;
            }
            if(esFactible(laberinto, col, fila + 1)) {
                resolver(laberinto, col, fila + 1, celda + 1);
                laberinto[col][fila + 1] = VACIO;
            }
            if(esFactible(laberinto, col - 1, fila)) {
                resolver(laberinto, col - 1, fila, celda + 1);
                laberinto[col - 1][fila] = VACIO;
            }
            if(esFactible(laberinto, col, fila - 1)) {
                resolver(laberinto, col, fila - 1, celda + 1);
                laberinto[col][fila - 1] = VACIO;
            }
        }
    }

    private static boolean esFactible(int[][] laberinto, int col, int fila) {
        if(col >= 0 && col < laberinto.length && fila >= 0 && fila < laberinto[0].length) {
            return (laberinto[col][fila] == VACIO);
        }

        return false;
    }

    private static boolean acabado(int[][] laberinto, int col, int fila) {
        return (col == laberinto.length - 1 && fila == laberinto[0].length - 1);
    }
}
