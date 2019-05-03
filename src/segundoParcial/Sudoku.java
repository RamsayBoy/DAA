package segundoParcial;

import java.util.Scanner;

public class Sudoku {
    private static final int DIMENSION = 9;
    private static final int VACIO = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[][] sudoku = new int[DIMENSION][DIMENSION];

        for(int i = 0; i < sudoku.length; i++) {
            for(int j = 0; j < sudoku[0].length; j++) {
                sudoku[i][j] = input.nextInt();
            }
        }

        boolean resuelto = resolver(sudoku);

        if(resuelto) {
            imprimir(sudoku);
        }
        else {
            System.out.println("\n[ERROR] El sudoku no tiene solución");
        }
    }

    private static boolean resolver(int[][] sudoku) {
        return resolver(sudoku, 0, 0);
    }

    private static boolean resolver(int[][] sudoku, int col, int fila) {
        boolean exito = false;
        int sigCol, sigFila;

        if(sudoku[col][fila] != VACIO) {
            if(acabado(col, fila)) {
                return true;
            }
            else {
                if(col == DIMENSION - 1) {
                    sigCol = 0;
                    sigFila = fila + 1;
                }
                else {
                    sigCol = col + 1;
                    sigFila = fila;
                }

                return resolver(sudoku, sigCol, sigFila);
            }
        }
        else {
            for(int intento = 1; intento <= DIMENSION && !exito; intento++) {
                if(esFactible(sudoku, intento, col, fila)) {
                    sudoku[col][fila] = intento;

                    if(acabado(col, fila)) {
                        exito = true;
                    }
                    else {
                        if(col == DIMENSION - 1) {
                            sigCol = 0;
                            sigFila = fila + 1;
                        }
                        else {
                            sigCol = col + 1;
                            sigFila = fila;
                        }

                        exito = resolver(sudoku, sigCol, sigFila);

                        if(!exito) {
                            sudoku[col][fila] = VACIO;
                        }
                    }
                }
            }
            return exito;
        }
    }

    private static boolean acabado(int col, int fila) {
        return (col == DIMENSION - 1 && fila == DIMENSION - 1);
    }

    private static boolean esFactible(int[][] sudoku, int intento, int col, int fila) {
        return (!estaEnFila(sudoku, intento, fila) &&
                !estaEnColumna(sudoku, intento, col) &&
                !estaEnCaja(sudoku, intento, col, fila));
    }

    private static boolean estaEnFila(int[][] sudoku, int intento, int fila) {
        for(int i = 0; i < DIMENSION; i++) {
            if(sudoku[i][fila] == intento) {
                return true;
            }
        }
        return false;
    }

    private static boolean estaEnColumna(int[][] sudoku, int intento, int col) {
        for(int i = 0; i < DIMENSION; i++) {
            if(sudoku[col][i] == intento) {
                return true;
            }
        }
        return false;
    }

    private static boolean estaEnCaja(int[][] sudoku, int intento, int col, int fila) {
        int colCaja = col - col % 3;
        int filaCaja = fila - fila % 3;

        for(int i = colCaja; i < colCaja + 3; i++) {
            for(int j = filaCaja; j < filaCaja + 3; j++) {
                if(sudoku[i][j] == intento) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void imprimir(int[][] sudoku) {
        for(int[] fila : sudoku) {
            for(int numero : fila) {
                System.out.print(numero + " ");
            }
            System.out.println();
        }
    }
}
