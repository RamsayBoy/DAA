package primerParcial.sesion2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Actividades {
    private static class Actividad implements Comparable {
        private int inicio, fin;

        private Actividad(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public int compareTo(Object o) {
            Actividad that = (Actividad)o;
            return Integer.compare(this.fin, that.fin);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCasos = input.nextInt();
        List<Actividad>[] actividades = new List[numCasos];

        for(int i = 0; i < numCasos; i++) {
            actividades[i] = new ArrayList<>();
        }

        for(int i = 0; i < numCasos; i++) {
            int numActividades = input.nextInt();
            for(int j = 0; j < numActividades; j++) {
                Actividad actividad = new Actividad(input.nextInt(), input.nextInt());
                actividades[i].add(actividad);
            }
        }

        for(int i = 0; i < numCasos; i++) {
            Collections.sort(actividades[i]);
            numActividadesMax(actividades[i]);
        }
    }

    /**
     * Imprime el número de actividades que se pueden hacer como máximo en cada caso
     */
    private static void numActividadesMax(List<Actividad> actividades) {
        int numActividadesHechas = 0;
        int ultimoFin = 0;

        for(Actividad actividad : actividades) {
            if(actividad.inicio >= ultimoFin) {
                ultimoFin = actividad.fin;
                numActividadesHechas++;
            }
        }

        System.out.println(numActividadesHechas);
    }
}
