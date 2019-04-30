package RecorridosGrafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BusquedaAnchura {
    private static final int NODO_INICIAL = 1;
    private static final int NUM_NODOS = 9;

    public static void main(String[] args) {
        // Ejemplo de grafo no dirigido para probar búsqueda en anchura
        List[] grafo = new List[NUM_NODOS + NODO_INICIAL];
        inicializar(grafo);

        List[] recorridos = busquedaAnchura(grafo);
        imprimir(recorridos);
    }

    private static List[] busquedaAnchura(List[] grafo) {
        int dimensionGrafo = grafo.length;
        List[] recorridos = new List[dimensionGrafo];

        for(int i = NODO_INICIAL; i < dimensionGrafo; i++) {
            recorridos[i] = busquedaAnchura(grafo, i);
        }

        return recorridos;
    }

    private static List busquedaAnchura(List<Integer>[] grafo, int nodo) {
        List recorrido = new ArrayList();
        Queue<Integer> cola = new LinkedList();
        boolean[] visitado = new boolean[grafo.length];

        cola.add(nodo);
        recorrido.add(nodo);
        visitado[nodo] = true;

        while(!cola.isEmpty()) {
            int nodoDeCola = cola.remove();

            for(int adyacente : grafo[nodoDeCola]) {
                if(!visitado[adyacente]) {
                    cola.add(adyacente);
                    recorrido.add(adyacente);
                    visitado[adyacente] = true;
                }
            }
        }

        return recorrido;
    }

    private static void inicializar(List<Integer>[] grafo) {
        for(int i = NODO_INICIAL; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }

        grafo[1].add(2);
        grafo[2].add(1);
        grafo[1].add(4);
        grafo[4].add(1);
        grafo[1].add(8);
        grafo[8].add(1);
        grafo[2].add(3);
        grafo[3].add(2);
        grafo[2].add(4);
        grafo[4].add(2);
        grafo[3].add(4);
        grafo[4].add(3);
        grafo[3].add(5);
        grafo[5].add(3);
        grafo[4].add(7);
        grafo[7].add(4);
        grafo[5].add(6);
        grafo[6].add(5);
        grafo[6].add(7);
        grafo[7].add(6);
        grafo[7].add(9);
        grafo[9].add(7);
        grafo[8].add(9);
        grafo[9].add(8);
    }

    private static void imprimir(List<Integer>[] grafo) {
        for(int i = NODO_INICIAL; i < grafo.length; i++) {
            System.out.print("Recorrido " + i + ": ");
            for(Object adyacente : grafo[i]) {
                System.out.print(adyacente + " ");
            }
            System.out.println();
        }
    }
}
