package segundoParcial.examen;

import java.util.Arrays;
import java.util.Scanner;

public class Examen {
    private static final int SIN_MODELO = -1;
    private static int numModelosDados;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numAlumnos = input.nextInt();
        int numRelaciones = input.nextInt();
        int numModelos = input.nextInt();

        boolean[][] relacion = new boolean[numAlumnos][numAlumnos];
        int[] modelo = new int[numAlumnos];

        Arrays.fill(modelo, SIN_MODELO);

        for(int i = 0; i < numRelaciones; i++) {
            int alumno1 = input.nextInt();
            int alumno2 = input.nextInt();

            relacion[alumno1][alumno2] = true;
            relacion[alumno2][alumno1] = true;
        }

        numModelosDados = 0;
        boolean hayModelos = suficientesModelos(numModelos, relacion, modelo, 0);

        if(hayModelos) {
            System.out.println("OK");
        }
        else {
            System.out.println("NO HAY SUFICIENTE");
        }
    }

    private static boolean suficientesModelos(int numModelos, boolean[][] relacion, int[] modelo, int alumno) {
        boolean exito = false;

        if(numModelosDados >= numModelos) {
            return false;
        }
        else {
            for(int intento = 0; intento < numModelos && !exito; intento++) {
                if(sePuedeDar(intento, alumno, relacion, modelo)) {
                    modelo[alumno] = intento;

                    if(alumno == modelo.length - 1) {
                        exito = true;
                    }
                    else {
                        exito = suficientesModelos(numModelos, relacion, modelo, alumno + 1);

                        if(!exito) {
                            modelo[alumno] = SIN_MODELO;
                        }
                    }
                }
            }

            return exito;
        }
    }

    /**
     * Comprueba si es factible dar cierto modelo a un alumno
     *
     * @param intento  - modelo que se intenta dar al alumno
     * @param alumno   - recibe el modelo
     * @param relacion - relaciones entre alumnos
     * @param modelo   - modelos que tiene cada alumno
     * @return true si el modelo a añadir no coincide con el de otro alumno que haya cursado la misma asignatura
     */
    private static boolean sePuedeDar(int intento, int alumno, boolean[][] relacion, int[] modelo) {
        for(int i = 0; i < relacion[0].length; i++) {
            if(relacion[alumno][i]) {
                if(intento == modelo[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
