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
   String nombre;
    String especialidad;

    // Constructor completo
    public Médico(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Constructor solo con nombre
    public Médico(String nombre) {
        this.nombre = nombre;
        this.especialidad = "General"; // puedes ajustarlo según el comboBox
    }

    @Override
    public String toString() {
        return nombre + " - " + especialidad;
    }
    
}
