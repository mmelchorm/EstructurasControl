package estructurascontrol;

/**
 *
 * @author desarrollador02
 */
public class Cola {
    
    private static Cola instancia;
    public Nodo frente;
    private Nodo fin;
    public int duracionTotal;

    private Cola() {
        frente = fin = null;
    }

    public static Cola obtenerInstancia() {
        if (instancia == null) {
            instancia = new Cola();
        }
        return instancia;
    }

    boolean estaVacia() {
        return frente == null;
    }

    void encolar(Memoria slot) {
        Nodo nuevoNodo = new Nodo(slot);
        if (estaVacia()) {
            frente = fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        duracionTotal += slot.duracion;
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
            return procesoDesencolado;
        }
    }
    
}
