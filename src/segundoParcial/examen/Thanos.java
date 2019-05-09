package segundoParcial.examen;

import java.util.Scanner;

public class Thanos {
    private static int rutas;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numPlanetas = input.nextInt();
        int numConexiones = input.nextInt();

        boolean[][] conexion = new boolean[numPlanetas][numPlanetas];

        for(int i = 0; i < numConexiones; i++) {
            int planeta1 = input.nextInt();
            int planeta2 = input.nextInt();

            conexion[planeta1][planeta2] = true;
            conexion[planeta2][planeta1] = true;
        }

        rutas = 0;
        posiblesRutas(conexion);
        System.out.println(rutas);
    }

    private static void posiblesRutas(boolean[][] conexion) {
        boolean[] visitados = new boolean[conexion.length];
        visitados[0] = true;

        posiblesRutas(conexion, visitados, 0);
    }

    private static void posiblesRutas(boolean[][] conexion, boolean[] visitados, int planetaActual) {
        if(visitadosTodos(visitados)) {
            for(int i = 0; i < conexion[0].length; i++) {
                if(conexion[planetaActual][i] && i == 0) {
                    rutas++;
                }
            }
        }
        else {
            for(int intento = 0; intento < conexion[0].length; intento++) {
                if(sePuedeVisitar(conexion, visitados, planetaActual, intento)) {
                    visitados[intento] = true;

                    posiblesRutas(conexion, visitados, intento);

                    visitados[intento] = false;
                }
            }
        }
    }

    /**
     * Comprueba que se han visitado todos los planetas
     *
     * @param visitados - planetas visitados
     * @return true si todos los planetas han sido visitados
     */
    private static boolean visitadosTodos(boolean[] visitados) {
        for(boolean visitado : visitados) {
            if(!visitado) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param conexion      - conexiones entre planetas
     * @param visitados     - planetas visitados
     * @param planetaActual - planeta en el que te encuentras
     * @param intento       - planeta al que intentas ir
     * @return true si el planeta no está visitado y tiene conexión con el planeta actual
     */
    private static boolean sePuedeVisitar(boolean[][] conexion, boolean[] visitados, int planetaActual, int intento) {
        return !visitados[intento] && conexion[planetaActual][intento];
    }
}
