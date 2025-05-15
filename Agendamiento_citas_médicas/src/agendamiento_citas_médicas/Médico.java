/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamiento_citas_médicas;

/**
 *
 * @author Lenovo
 */
public class Médico {
    String id;
    String nombre;
    String especialidad;

    public Médico(String id, String nombre, String especialidad) {
        this(id);
    }

    public Médico(String id) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return  nombre + " - " + especialidad ;
    }
    
    
}
