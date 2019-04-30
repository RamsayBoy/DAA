package primerParcial.sesion1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ciclos {
    private static final int NODO_INI = 1;
    private static final int NINGUNO = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numNodos = input.nextInt();
        int numAristas = input.nextInt();

        List[] grafo = new List[numNodos + NODO_INI];

        for(int i = NODO_INI; i < grafo.length; i++) {
            grafo[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < numAristas; i++) {
            int nodo1 = input.nextInt();
            int nodo2 = input.nextInt();

            // Grafo no dirigido
            grafo[nodo1].add(nodo2);
            grafo[nodo2].add(nodo1);
        }

        System.out.println(tieneCiclos(grafo));
    }

    private static boolean tieneCiclos(List<Integer>[] grafo) {
        int dimensionGrafo = grafo.length;
        boolean[] visitado = new boolean[dimensionGrafo];

        for(int i = NODO_INI; i < dimensionGrafo; i++) {
            if(tieneCiclos(grafo, i, NINGUNO, visitado)) {
                return true;
            }
            // Poner todos los valores como false para el siguiente nodo
            Arrays.fill(visitado, false);
        }

        return false;
    }

    private static boolean tieneCiclos(List<Integer>[] grafo, int nodo, int padre, boolean[] visitado) {
        visitado[nodo] = true;

        for(int adyacente : grafo[nodo]) {
            if(!visitado[adyacente]) {
                if(tieneCiclos(grafo, adyacente, nodo, visitado)) {
                    return true;
                }
            }
            else if(adyacente != padre) {
                return true;
            }
        }

        return false;
    }
}
