package primerParcial.sesion2;

import java.util.*;

public class EquipoDarkSouls {
    private static final float LIGERO = 0.5f;
    private static final float MEDIO = 0.75f;

    private static class Objeto implements Comparable {
        private String nombre;
        private float peso, defensa, ratio;

        private Objeto(String nombre, float peso, float defensa) {
            this.nombre = nombre;
            this.peso = peso;
            this.defensa = defensa;
            this.ratio = ratioEntre(peso, defensa);
        }

        private float ratioEntre(float peso, float defensa) {
            return (defensa / peso);
        }

        @Override
        public int compareTo(Object o) {
            Objeto that = (Objeto)o;
            return Float.compare(that.ratio, this.ratio);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numObjetos = input.nextInt();
        float pesoMax = input.nextFloat();
        String modo = input.next();

        List<Objeto> objetos = new ArrayList<>();

        for(int i = 0; i < numObjetos; i++) {
            Objeto objeto = new Objeto(input.next(), input.nextFloat(), input.nextFloat());
            objetos.add(objeto);
        }

        Collections.sort(objetos);

        switch(modo) {
            case "ligero":
                elegirEquipo(objetos, pesoMax * LIGERO);
                break;
            case "medio":
                elegirEquipo(objetos, pesoMax * MEDIO);
                break;
            case "pesado":
                elegirEquipo(objetos, pesoMax);
                break;
        }
    }

    private static void elegirEquipo(List<Objeto> objetos, float peso) {
        float pesoEquipo = 0;
        float defensaEquipo = 0;
        List<String> equipo = new ArrayList<>();

        for(int i = 0; i < objetos.size() && (pesoEquipo < peso); i++) {
            Objeto objeto = objetos.get(i);

            if(sePuedeEquipar(objeto, pesoEquipo, peso)) {
                pesoEquipo = pesoEquipo + objeto.peso;
                defensaEquipo = defensaEquipo + objeto.defensa;
                equipo.add(objeto.nombre);
            }
            else {
                defensaEquipo += ((peso - pesoEquipo) / objeto.peso) * objeto.defensa;
                pesoEquipo = peso;
                equipo.add(objeto.nombre);
            }
        }

        System.out.printf("%.2f", defensaEquipo);
        System.out.println();

        Collections.sort(equipo);
        for(String objeto : equipo) {
            System.out.println(objeto);
        }
    }

    private static boolean sePuedeEquipar(Objeto objeto, float pesoActual, float pesoMax) {
        return (pesoActual + objeto.peso <= pesoMax);
    }
}
