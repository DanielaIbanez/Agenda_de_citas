/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamiento_citas_médicas;

/**
 *
 * @author Lenovo
 */
public class Paciente {
    String identificacion;
    String nombre;

    public Paciente(String identificacion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }
    public String toString(){
    return nombre + "(ID: " + identificacion + ")";
    }
    
}
