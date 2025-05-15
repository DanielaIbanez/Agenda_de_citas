/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamiento_citas_médicas;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Lenovo
 */
public class VentanaAgendamiento {

    HashMap<String, Cita> citasAgendadas;
    Queue<Cita> colaCitas;
    Stack< Cita> pilaCancelaciones;
    JComboBox< String> comboBoxTipoCita;
    JTextField textFieldPaciente;
    JTextField textFieldIdentificacion;
    JTextField textFieldMédico;
    JTextField textFieldHorario;
    JTextArea textAreaCitas;
    JComboBox<String> comboBoxEspecialidad;

    public VentanaAgendamiento() {
        citasAgendadas = new HashMap<>();
        colaCitas = new LinkedList<>();
        pilaCancelaciones = new Stack<>();

        JFrame frame = new JFrame("Agendamiento de Citas");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelPaciente = new JLabel("Paciente: ");
        labelPaciente.setBounds(20, 20, 120, 25);
        frame.add(labelPaciente);
        textFieldPaciente = new JTextField();
        textFieldPaciente.setBounds(150, 20, 200, 25);
        frame.add(textFieldPaciente);
        
        JLabel labelIdentificacion = new JLabel("Identificación:");
        labelIdentificacion.setBounds(20, 60, 120, 25);
        frame.add(labelIdentificacion);
        textFieldIdentificacion = new JTextField();
        textFieldIdentificacion.setBounds(150, 20, 200, 25);
        frame.add(textFieldIdentificacion);

        JLabel labelMedico = new JLabel("Médico: ");
        labelMedico.setBounds(20, 100, 100, 25);
        frame.add(labelMedico);
        textFieldMédico = new JTextField();
        textFieldMédico.setBounds(150, 100, 200, 25);
        frame.add(textFieldMédico);

        JLabel labelHorario = new JLabel("Horario: ");
        labelHorario.setBounds(20, 140, 120, 25);
        frame.add(labelHorario);
        textFieldHorario = new JTextField();
        textFieldHorario.setBounds(150, 140, 200, 25);
        frame.add(textFieldHorario);
        JLabel labelEspecialidad = new JLabel("Especialidad:");
        labelEspecialidad.setBounds(20, 220, 120, 25);
        frame.add(labelEspecialidad);

        String[] especialidades = {"Pediatría", "Cardiología", "Dermatología", "Neurología"};
        comboBoxEspecialidad = new JComboBox<>(especialidades);
        comboBoxEspecialidad.setBounds(150, 220, 200, 25);
        frame.add(comboBoxEspecialidad);

        JLabel labelTipoCita = new JLabel("Tipo de Cita: ");
        labelTipoCita.setBounds(20, 180, 120, 25);
        frame.add(labelTipoCita);
        String[] tipoCita = {"Normal", "Urgencias"};
        comboBoxTipoCita = new JComboBox<>(tipoCita);
        comboBoxTipoCita.setBounds(150, 180, 200, 25);
        frame.add(comboBoxTipoCita);

        comboBoxTipoCita.addActionListener(e -> {
            String tipoSeleccionado = (String) comboBoxTipoCita.getSelectedItem();
            boolean esNormal = tipoSeleccionado.equals("Normal");
            comboBoxEspecialidad.setEnabled(esNormal);
        });
        JButton buttonAgendar = new JButton("Agendar Cita");
        buttonAgendar.setBounds(20, 270, 130, 35);
        frame.add(buttonAgendar);

        JButton buttonAtender = new JButton("Atender Cita");
        buttonAtender.setBounds(170, 270, 130, 35);
        frame.add(buttonAtender);

        JButton buttonCancelar = new JButton("Cancelar Cita");
        buttonCancelar.setBounds(20, 320, 130, 35);
        frame.add(buttonCancelar);

        textAreaCitas = new JTextArea();
        textAreaCitas.setBounds(20, 370, 330, 80);
        frame.add(textAreaCitas);

        buttonAgendar.addActionListener(e -> agendarCita());
        buttonAtender.addActionListener(e -> atenderCita());
        buttonCancelar.addActionListener(e -> cancelarCita());
        frame.setVisible(true);

    }

    private void agendarCita() {
        String pacienteNombre = textFieldPaciente.getText();
        String medicoNombre = textFieldMédico.getText().trim();
        String horario = textFieldHorario.getText().trim();
        String tipoCitaSeleccionado = (String) comboBoxTipoCita.getSelectedItem();
        if (pacienteNombre.isEmpty() || medicoNombre.isEmpty() || horario.isEmpty()) {
            textAreaCitas.setText("Por favor complete todos los campos");
            return;
        }
        TipoCita tipoCita = tipoCitaSeleccionado.equals("Urgencias") ? TipoCita.URGENCIAS : TipoCita.NORMAL;
        String especialidad = comboBoxEspecialidad.isEnabled() ?
        (String) comboBoxEspecialidad.getSelectedItem() : "General";
        
         Paciente paciente = new Paciente(pacienteNombre);
        Médico medico = new Médico(medicoNombre);

        Cita cita = new Cita(paciente, medico, horario, tipoCita);
        citasAgendadas.put(pacienteNombre, cita);
        textAreaCitas.setText("Cita agendada: " + cita.resumenCita());
    }
      private void agendarCitaEnCola(Cita cita) {
        if (cita.isUrgente()) {
           
            colaCitas.offer(cita);
            System.out.println("Cita urgente agendada: " + cita);
        } else {
            
            colaCitas.offer(cita);
            System.out.println("Cita normal agendada: " + cita);
        }
    }
      private void atenderCita() {
        Cita cita = colaCitas.poll();  
        if (cita != null) {
            textAreaCitas.setText("Cita atendida: " + cita.resumenCita());
        } else {
            textAreaCitas.setText("No hay citas en espera.");
        }
    }
       private void cancelarCita() {
        Cita cita = colaCitas.poll();  
        if (cita != null) {
            pilaCancelaciones.push(cita);  
            textAreaCitas.setText("Cita cancelada: " + cita.resumenCita());
        } else {
            textAreaCitas.setText("No hay citas para cancelar.");
        }
       }

}
