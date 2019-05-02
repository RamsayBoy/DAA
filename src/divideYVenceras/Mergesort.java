package divideYVenceras;

import java.util.Arrays;
import java.util.Scanner;

public class Mergesort {
    public static void main(String[] args) {
        int[] vector = {3, 1, 6, 3, 8, 5, 9, 6, 2, 4};

        int[] vectorOrdenado = mergesort(vector);

        for(int elemento : vectorOrdenado) {
            System.out.print(elemento + " ");
        }
    }

    private static int[] mergesort(int[] vector) {
        return mergesort(vector, 0, vector.length);
    }

    private static int[] mergesort(int[] vector, int inicio, int fin) {
        if(fin - inicio < 2) {
            return Arrays.copyOfRange(vector, inicio, fin);
        }

        int medio = (fin + inicio) / 2;

        int[] mitadIzq = mergesort(vector, inicio, medio);
        int[] mitadDer = mergesort(vector, medio, fin);

        return fusionar(mitadIzq, mitadDer);
    }

    private static int[] fusionar(int[] vector1, int[] vector2) {
        int i = 0, j = 0, k = 0;
        int[] vectorFusion = new int[vector1.length + vector2.length];

        while(i < vector1.length && j < vector2.length) {
            if(vector1[i] < vector2[j]) {
                vectorFusion[k] = vector1[i];
                i++;
            }
            else {
                vectorFusion[k] = vector2[j];
                j++;
            }

            k++;
        }

        // En el caso de que i no haya acabado
        while(i < vector1.length) {
            vectorFusion[k] = vector1[i];
            i++;
            k++;
        }

        // En el caso de que j no haya acabado
        while(j < vector2.length) {
            vectorFusion[k] = vector2[j];
            j++;
            k++;
        }

        return vectorFusion;
    }
}
