package primerParcial.sesion3;

import java.util.Scanner;

public class PuntosDarkSouls {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numEnemigos = input.nextInt();
        long[] nivelEnemigos = new long[numEnemigos];
        // Puntos acumulados por enemigo vencido
        long[] puntos = new long[numEnemigos];
        long puntosAcumulados = 0;

        for(int i = 0; i < numEnemigos; i++) {
            nivelEnemigos[i] = input.nextLong();
            puntosAcumulados += nivelEnemigos[i];
            puntos[i] = puntosAcumulados;
        }

        int numCasos = input.nextInt();
        long[] nivelCaballero = new long[numCasos];

        for(int i = 0; i < nivelCaballero.length; i++) {
            nivelCaballero[i] = input.nextLong();
        }

        for(long nivel : nivelCaballero) {
            if(nivel < nivelEnemigos[0]) {
                System.out.println(0 + " " + 0);
            }
            else if(nivel > nivelEnemigos[numEnemigos - 1]) {
                System.out.println(numEnemigos + " " + puntos[numEnemigos - 1]);
            }
            else {
                int enemigosVencidos = posicionEnemigoApto(nivelEnemigos, nivel, 0, nivelEnemigos.length - 1);
                System.out.println(enemigosVencidos + 1 + " " + puntos[enemigosVencidos]);
            }
        }
    }

    private static int posicionEnemigoApto(long[] nivelEnemigos, long nivelCaballero, int inicio, int fin) {
        int medio = (fin + inicio) / 2;

        if(inicio == medio) {
            return inicio;
        }
        if(fin == medio) {
            return fin;
        }

        if(nivelCaballero == nivelEnemigos[medio]) {
            return medio;
        }
        else if(nivelCaballero < nivelEnemigos[medio]) {
            return posicionEnemigoApto(nivelEnemigos, nivelCaballero, inicio, medio);
        }
        else {
            return posicionEnemigoApto(nivelEnemigos, nivelCaballero, medio, fin);
        }
    }
}
