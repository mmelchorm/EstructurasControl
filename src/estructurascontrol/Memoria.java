package estructurascontrol;

/**
 *
 * @author desarrollador02
 */
public class Memoria {
 
    String nombreProceso;
    int tamaño;
    int llegada;
    int duracion;

    Memoria(String nombreProceso, int tamaño, int llegada, int duracion) {
        this.nombreProceso = nombreProceso;
        this.tamaño = tamaño;
        this.llegada = llegada;
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return nombreProceso + " (Tamaño: " + tamaño + ", Llegada: " + llegada + ", Duración: " + duracion + ")";
    }
    
    
}
