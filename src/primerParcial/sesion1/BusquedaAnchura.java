package primerParcial.sesion1;

import java.util.*;

public class BusquedaAnchura {
    private static final int NODO_INICIAL = 1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numNodos = input.nextInt();
        int numAristas = input.nextInt();
        int nodoInicio = input.nextInt();

        List[] grafo = new List[numNodos + NODO_INICIAL];

        for(int i = NODO_INICIAL; i < grafo.length; i++) {
            grafo[i] = new ArrayList();
        }

        for(int i = 0; i < numAristas; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();

            grafo[node1].add(node2);
            grafo[node2].add(node1);
        }

        List<Integer> traversal = busquedaAnchura(grafo, nodoInicio);
        imprimir(traversal);
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

    private static void imprimir(List<Integer> recorrido) {
        for(int i = 0; i < recorrido.size(); i++) {
            System.out.print(recorrido.get(i) + " ");
        }
    }
}
