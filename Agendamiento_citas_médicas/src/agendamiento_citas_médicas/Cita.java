/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamiento_citas_médicas;

/**
 *
 * @author Lenovo
 */
public class Cita {
    private static int contadorNormal = 1;
     private static int contadorUrgente = 1;
     String idCita;
    Paciente paciente;
    Médico medico;
    String horario;
    TipoCita tipo;
  

    public Cita(Paciente paciente, Médico medico, String horario, TipoCita tipo) {
        this.paciente = paciente;
        this.medico = medico;
        this.horario = horario;
        this.tipo = tipo;
        if(tipo == TipoCita.URGENCIAS){
        this.idCita = "U-"+contadorUrgente++;
        }else{
        this.idCita= "N-"+ contadorNormal;
        }
    }
    public boolean isUrgente(){
    return tipo == TipoCita.URGENCIAS;
    }
    
    public String resumenCita(){
     
        return idCita + " | Paciente: " + paciente+ " (ID: " + paciente + ")"
        + " | Médico: " + medico
        + " | Especialidad: " + medico
        + " | Tipo: " + tipo
        + " | Horario: " + horario;
    
    
    }

    @Override
    public String toString() {
        return resumenCita();
    }
    
    
    
    
}
