package primerParcial.sesion2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* Hay casos que fallan por poco */
public class FinalStory {
    private static class Enemigo implements Comparable {
        private int ataque, vida, ratio;

        @Override
        public int compareTo(Object o) {
            Enemigo that = (Enemigo)o;

            if(this.ratio == that.ratio) {
                if(this.ataque == that.ataque) {
                    return Double.compare(this.vida, that.vida);
                }
                else {
                    return Double.compare(this.ataque, that.ataque);
                }
            }
            else {
                return Integer.compare(that.ratio, this.ratio);
            }
        }
    }

    private static class Combate {
        private int atqJugador, atqPorTurno;
        private List<Enemigo> enemigos;

        private Combate(int atqJugador, int numEnemigos) {
            this.atqJugador = atqJugador;
            this.enemigos = new ArrayList<>();
            for(int i = 0; i < numEnemigos; i++) {
                this.enemigos.add(new Enemigo());
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numCombates = input.nextInt();
        List<Combate> combates = new ArrayList<>();

        for(int i = 0; i < numCombates; i++) {
            int atqJugador = input.nextInt();
            int numEnemigos = input.nextInt();

            Combate combate = new Combate(atqJugador, numEnemigos);

            for(int j = 0; j < numEnemigos; j++) {
                Enemigo enemigo = combate.enemigos.get(j);
                enemigo.ataque = input.nextInt();
                combate.atqPorTurno += enemigo.ataque;
            }

            for(int j = 0; j < numEnemigos; j++) {
                Enemigo enemigo = combate.enemigos.get(j);
                enemigo.vida = input.nextInt();

                int ataquesNecesarios = enemigo.vida / combate.atqJugador;
                if(ataquesNecesarios == 0) {
                    ataquesNecesarios = 1;
                }
                enemigo.ratio = enemigo.ataque / ataquesNecesarios;
            }

            Collections.sort(combate.enemigos);
            combates.add(combate);
        }

        for(Combate combate : combates) {
            algoritmo(combate);
        }
    }

    private static void algoritmo(Combate combate) {
        int vidaPerdida = 0;
        int atqPorTurno = combate.atqPorTurno;

        for(int i = 0; i < combate.enemigos.size(); i++) {
            Enemigo enemigo = combate.enemigos.get(i);

            while(enemigo.vida > 0) {
                vidaPerdida += atqPorTurno;
                enemigo.vida -= combate.atqJugador;
            }

            atqPorTurno -= enemigo.ataque;
        }

        System.out.println(vidaPerdida);
    }
}
