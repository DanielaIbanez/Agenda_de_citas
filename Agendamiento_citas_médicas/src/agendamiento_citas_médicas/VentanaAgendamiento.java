/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamiento_citas_médicas;

import javax.swing.*;
import java.util.*;

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
    JTextArea textAreaCitas;
    JComboBox<String> comboBoxEspecialidad;
    JComboBox<String> comboBoxMédico;
    JComboBox<String> comboBoxHorario;

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
        textFieldIdentificacion.setBounds(150, 60, 200, 25);
        frame.add(textFieldIdentificacion);

        JLabel labelMedico = new JLabel("Médico: ");
        labelMedico.setBounds(20, 100, 100, 25);
        frame.add(labelMedico);
        comboBoxMédico = new JComboBox<>(new String[]{"Sara", "Juan", "Laura"});
        comboBoxMédico.setBounds(150, 100, 200, 25);
        frame.add(comboBoxMédico);

        JLabel labelHorario = new JLabel("Horario: ");
        labelHorario.setBounds(20, 140, 120, 25);
        frame.add(labelHorario);
        comboBoxHorario = new JComboBox<>(new String[]{"7:00", "8:00", "9:00", "10:00"});
        comboBoxHorario.setBounds(150, 140, 200, 25);
        frame.add(comboBoxHorario);

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

        JButton btnVerCitas = new JButton("Ver Citas");
        btnVerCitas.setBounds(160, 320, 130, 35); // Ajusta la posición
        frame.add(btnVerCitas);

        textAreaCitas = new JTextArea();
        textAreaCitas.setBounds(20, 370, 400, 150);
        frame.add(textAreaCitas);
        textAreaCitas.setLineWrap(true);
        textAreaCitas.setWrapStyleWord(true);
        textAreaCitas.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textAreaCitas);
        scrollPane.setBounds(20, 370, 400, 150);
        frame.add(scrollPane);

        buttonAgendar.addActionListener(e -> agendarCita());
        buttonAtender.addActionListener(e -> atenderCita());
        buttonCancelar.addActionListener(e -> cancelarCita());
        btnVerCitas.addActionListener(e -> mostrarCitasAgendadas());
        frame.setVisible(true);

    }

    private void agendarCita() {
        String pacienteNombre = textFieldPaciente.getText();
        String medicoNombre = (String) comboBoxMédico.getSelectedItem();
        String horario = (String) comboBoxHorario.getSelectedItem();
        String identificacion = textFieldIdentificacion.getText().trim();
        String tipoCitaSeleccionado = (String) comboBoxTipoCita.getSelectedItem();
        if (pacienteNombre.isEmpty() || medicoNombre.isEmpty() || horario.isEmpty()) {
            textAreaCitas.setText("Por favor complete todos los campos");
            return;
        }
        TipoCita tipoCita = tipoCitaSeleccionado.equals("Urgencias") ? TipoCita.URGENCIAS : TipoCita.NORMAL;

        String especialidad = comboBoxEspecialidad.isEnabled()
                ? (String) comboBoxEspecialidad.getSelectedItem() : "General";

        Paciente paciente = new Paciente(pacienteNombre, identificacion);
        Médico medico = new Médico(medicoNombre, especialidad);

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

    private void mostrarCitasAgendadas() {
        if (citasAgendadas.isEmpty()) {
            textAreaCitas.setText("No hay citas agendadas.");
            return;
        }

        StringBuilder sb = new StringBuilder("Citas agendadas:\n");
        for (Cita cita : citasAgendadas.values()) {
            sb.append(cita.resumenCita()).append("\n");
        }

        textAreaCitas.setText(sb.toString());
    }

}
