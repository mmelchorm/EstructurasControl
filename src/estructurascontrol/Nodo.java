/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurascontrol;

/**
 *
 * @author desarrollador02
 */
public class Nodo {
    
    Memoria slot;
    Nodo siguiente;

    Nodo(Memoria slot) {
        this.slot = slot;
        this.siguiente = null;
    }
    
}
