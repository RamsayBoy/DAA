package segundoParcial;

import java.util.Scanner;

public class CasiSudoku {
    private static final int DIMENSION = 9;
    private static final int VACIO = 0;

    private static int numSoluciones = 0;
    private static int[][] solucionUnica = new int[DIMENSION][DIMENSION];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[][] sudoku = new int[DIMENSION][DIMENSION];

        for(int i = 0; i < sudoku.length; i++) {
            for(int j = 0; j < sudoku[0].length; j++) {
                sudoku[i][j] = input.nextInt();
            }
        }

        resolver(sudoku);

        if(numSoluciones == 0)
            System.out.println("imposible");
        else if(numSoluciones == 1)
            imprimir(solucionUnica);
        else
            System.out.println("casi sudoku");
    }

    private static void resolver(int[][] sudoku) {
        resolver(sudoku, 0, 0);
    }

    private static void resolver(int[][] sudoku, int col, int fila) {
        int sigCol, sigFila;

        if(sudoku[col][fila] != VACIO) {
            if(acabado(col, fila)) {
                numSoluciones++;
                // Copiamos la primera solución para no perderla
                for(int i = 0; i < solucionUnica.length; i++) {
                    System.arraycopy(sudoku[i], 0, solucionUnica[i], 0, solucionUnica[0].length);
                }
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

                resolver(sudoku, sigCol, sigFila);
            }
        }
        else {
            if(numSoluciones <= 1) {
                for(int intento = 1; intento <= DIMENSION; intento++) {
                    if(esFactible(sudoku, intento, col, fila)) {
                        sudoku[col][fila] = intento;

                        if(acabado(col, fila)) {
                            numSoluciones++;
                            if(sudoku[col][fila] != VACIO) {
                                for(int i = 0; i < solucionUnica.length; i++) {
                                    System.arraycopy
                                            (sudoku[i], 0, solucionUnica[i], 0, solucionUnica[0].length);
                                }
                            }
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

                            resolver(sudoku, sigCol, sigFila);

                            sudoku[col][fila] = VACIO;

                        }
                    }
                }
            }
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

