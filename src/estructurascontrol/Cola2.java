package estructurascontrol;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author desarrollador02
 */
public class Cola2 {

    private static Cola2 instancia;

    Nodo frente;
    private Nodo fin;
    private int tamañoMaximo; /// este es el limite de lo que puede contener la memoria luego de quitarle el espacio para el SO
    public int tamañoActual; // esto es lo que se lleva actualmente utilizando
    private LinkedList<Memoria> cola;

    private Cola2() {
        frente = fin = null;
    }

    private void Cola2(int tamaño) {
        this.tamañoMaximo = tamaño;
        this.tamañoActual = tamañoMaximo;
    }

    public static Cola2 obtenerInstancia(int tamañoMaximo) {
        if (instancia == null) {
            instancia = new Cola2();
            instancia.Cola2(tamañoMaximo);
        }
        return instancia;
    }

    boolean estaVacia() {
        return frente == null;
    }

    boolean otroMas(int otro) {
        return this.tamañoActual >= otro;
    }

    boolean estaLlena() {
        return tamañoActual >= tamañoMaximo;
    }

    void encolar(Memoria slot) {
        if (slot.tamaño <= tamañoActual) {
            Nodo nuevoNodo = new Nodo(slot);
            if (estaVacia()) {
                frente = fin = nuevoNodo;
            } else {
                fin.siguiente = nuevoNodo;
                fin = nuevoNodo;
            }
            this.tamañoActual -= slot.tamaño;
        } else {
            System.out.println("No hay suficiente memoria para el proceso " + slot.nombreProceso + ". Esperando...");
        }
    }

    Memoria desencolar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return null;
        } else {
            Memoria procesoDesencolado = frente.slot;
            frente = frente.siguiente;
            if (frente == null) {
                fin = null;
            }
            tamañoActual -= procesoDesencolado.tamaño;
            return procesoDesencolado;
        }
    }

    //Memoria recorrer
    void reducirDuracion() {
        Nodo nodoActual = frente;
        Nodo nodoAnterior = null;

        while (nodoActual != null) {
            System.out.println(nodoActual.slot);
            nodoActual.slot.duracion--; // Reducir duración del proceso en 1
            // Si la duración del proceso llega a cero, eliminar el nodo de la cola
            if (nodoActual.slot.duracion == 0) {
                this.tamañoActual += nodoActual.slot.tamaño;
                if (nodoAnterior == null) {
                    frente = nodoActual.siguiente;
                } else {
                    nodoAnterior.siguiente = nodoActual.siguiente;
                }
            }
            // Avanzar al siguiente nodo
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
            try {
                Thread.sleep(1000); // Pausa durante 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void realizarProceso() {
        // Caso base: la segunda cola está vacía
        if (estaVacia()) {
            return;
        }
        reducirDuracion(); // Reduce la duración de los procesos en la segunda cola
        // Imprimir el estado de la segunda cola después de reducir la duración
        //System.out.println(vuelta + " vuelta");
        Nodo nodoActual = frente;
        while (nodoActual != null) {
            System.out.println(nodoActual.slot);
            nodoActual = nodoActual.siguiente;
        }
        realizarProceso(); // Llamada recursiva para procesar la siguiente vuelta
    }

    public void mostrarCola() {
        Nodo actual = frente;
        if (actual == null) {
            System.out.println("La cola está vacía");
            return;
        }
        System.out.println("Elementos en la cola:");
        while (actual != null) {
            System.out.println("Nombre del proceso: " + actual.slot.nombreProceso
                    + ", Tamaño: " + actual.slot.tamaño
                    + ", Duración: " + actual.slot.duracion);
            actual = actual.siguiente;
        }
    }

    void reducirDuracion3() {
        Nodo nodoActual = frente;
        Nodo nodoAnterior = null;

        while (nodoActual != null) {
            System.out.println(nodoActual.slot);
            nodoActual.slot.duracion--;
            sacarNodo();
            if (nodoActual.siguiente == null && nodoAnterior != null) {
                nodoActual = frente;
            } else {
                nodoActual = nodoActual.siguiente;
            }
        }

    }

    //eliminar nodo si su duracion llega a cero
    void sacarNodo() {
        Nodo nodoActual = frente;
        Nodo nodoAnterior = null;
        while (nodoActual != null) {
            // Si la duración del proceso llega a cero, eliminar el nodo de la cola
            if (nodoActual.slot.duracion == 0) {
                this.tamañoActual += nodoActual.slot.tamaño;
                if (nodoAnterior == null) {
                    frente = nodoActual.siguiente;
                } else {
                    nodoAnterior.siguiente = nodoActual.siguiente;
                }
            }
            // Avanzar al siguiente nodo
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }
    }

}
