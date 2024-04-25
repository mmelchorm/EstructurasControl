package estructurascontrol;

public class EstructurasControl {

    public static void main(String[] args) {

        int totalMemoria = 20;
        int tamañoSO = 5;
        int memoriaDisponible = totalMemoria - tamañoSO;
        
         // Crear y llenar la primera cola
        Cola primeraCola = Cola.obtenerInstancia();
        primeraCola.encolar(new Memoria("Proceso A", 10, 1, 5));
        primeraCola.encolar(new Memoria("Proceso B", 5, 3, 7));
        primeraCola.encolar(new Memoria("Proceso C", 12, 2, 6));
        primeraCola.encolar(new Memoria("Proceso D", 3, 4, 4));
        // Crear la segunda cola con un tamaño máximo
        Cola2 segundaCola = Cola2.obtenerInstancia(memoriaDisponible);
        // Mover procesos de la primera cola a la segunda mientras haya espacio
        while (!primeraCola.estaVacia()) {
            Memoria proceso = primeraCola.desencolar();
            // System.out.println(proceso);
            if (proceso != null && segundaCola.otroMas(proceso.tamaño)) {
                segundaCola.encolar(proceso);
            } else {
                System.out.println("Proceso " + proceso.nombreProceso + " no puede entrar, esperara hasta que tenga espacio");
                primeraCola.encolar(proceso);
            }
            segundaCola.reducirDuracion3();
            try {
                Thread.sleep(1000); // Pausa durante 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Realizar acciones en la segunda cola hasta que esté vacía
        while (!segundaCola.estaVacia()) {
            Memoria proceso = primeraCola.desencolar();
            // System.out.println(proceso);
            if (proceso != null && segundaCola.otroMas(proceso.tamaño)) {
                segundaCola.encolar(proceso);
            }
            segundaCola.reducirDuracion3();
            try {
                Thread.sleep(1000); // Pausa durante 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       
    }

}
