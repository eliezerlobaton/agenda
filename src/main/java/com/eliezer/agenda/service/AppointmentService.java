package com.eliezer.agenda.service;

import com.eliezer.agenda.entity.Appointment;
import com.eliezer.agenda.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
  }

  public Optional<Appointment> getAppointmentById(Long id) {
    return appointmentRepository.findById(id);
  }

  public Appointment createAppointment(Appointment appointment) {
    return appointmentRepository.save(appointment);
  }

  public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
    Appointment appointment = appointmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Appointment not found"));
    appointment.setTitle(appointmentDetails.getTitle());
    appointment.setStartDate(appointmentDetails.getStartDate());
    appointment.setEndDate(appointmentDetails.getEndDate());
    appointment.setDescription(appointmentDetails.getDescription());
    return appointmentRepository.save(appointment);
  }

  public void deleteAppointment(Long id) {
    Appointment appointment = appointmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Appointment not found"));
    appointmentRepository.delete(appointment);
  }
}
