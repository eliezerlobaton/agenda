package com.eliezer.agenda.controller;

import com.eliezer.agenda.entity.Appointment;
import com.eliezer.agenda.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "API para gestionar compromisos de agenda")
public class AppointmentController {

  @Autowired
  private AppointmentService appointmentService;

  @GetMapping
  @Operation(summary = "Obtener todos los compromisos", description = "Devuelve una lista de todos los compromisos registrados.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de compromisos obtenida exitosamente")
  })
  public List<Appointment> getAllAppointments() {
    return appointmentService.getAllAppointments();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Obtener un compromiso por ID", description = "Devuelve un compromiso específico basado en su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Compromiso encontrado"),
      @ApiResponse(responseCode = "404", description = "Compromiso no encontrado")
  })
  public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
    return appointmentService.getAppointmentById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @Operation(summary = "Crear un nuevo compromiso", description = "Crea un nuevo compromiso en la agenda.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Compromiso creado exitosamente")
  })
  public Appointment createAppointment(@RequestBody Appointment appointment) {
    return appointmentService.createAppointment(appointment);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Actualizar un compromiso", description = "Actualiza un compromiso existente basado en su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Compromiso actualizado exitosamente"),
      @ApiResponse(responseCode = "404", description = "Compromiso no encontrado")
  })
  public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
    try {
      Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
      return ResponseEntity.ok(updatedAppointment);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Eliminar un compromiso", description = "Elimina un compromiso basado en su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Compromiso eliminado exitosamente"),
      @ApiResponse(responseCode = "404", description = "Compromiso no encontrado")
  })
  public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
    try {
      appointmentService.deleteAppointment(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}