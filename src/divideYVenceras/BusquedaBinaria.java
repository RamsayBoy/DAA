package divideYVenceras;

public class BusquedaBinaria {
    private static final int NO_ENCONTRADO = -1;

    public static void main(String[] args) {
        int vector[] = {1, 2, 2, 3, 3, 4, 5, 6, 8, 9};
        int elemento = 2;

        int indice = busquedaBinaria(vector, elemento);
        if(indice < 0)
            System.out.println("No se ha encontrado el índice del elemento");
        else
            System.out.println("El elemento " + elemento + " se encuentra en la posición " + indice);
    }

    private static int busquedaBinaria(int[] vector, int elemento) {
        return busquedaBinaria(vector, elemento, 0, vector.length - 1);
    }

    private static int busquedaBinaria(int[] vector, int elemento, int inicio, int fin) {
        if(inicio > fin) {
            return NO_ENCONTRADO;
        }

        int medio = (fin + inicio) / 2;

        if(elemento == vector[medio]) {
            return medio;
        }
        else if(elemento <= vector[medio]) {
            return busquedaBinaria(vector, elemento, inicio, medio - 1);
        }
        else {
            return busquedaBinaria(vector, elemento, medio + 1, fin);
        }
    }
}
