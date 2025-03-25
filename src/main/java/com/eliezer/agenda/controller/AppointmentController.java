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
@Tag(name = "Compromissos", description = "API para gerenciar compromissos da agenda")
public class AppointmentController {

  @Autowired
  private AppointmentService appointmentService;

  @GetMapping
  @Operation(summary = "Obter todos os compromissos", description = "Retorna uma lista de todos os compromissos registrados.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de compromissos obtida com sucesso")
  })
  public List<Appointment> getAllAppointments() {
    return appointmentService.getAllAppointments();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Obter um compromisso por ID", description = "Retorna um compromisso específico baseado em seu ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Compromisso encontrado"),
      @ApiResponse(responseCode = "404", description = "Compromisso não encontrado")
  })
  public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
    return appointmentService.getAppointmentById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @Operation(summary = "Criar um novo compromisso", description = "Cria um novo compromisso na agenda.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Compromisso criado com sucesso")
  })
  public Appointment createAppointment(@RequestBody Appointment appointment) {
    return appointmentService.createAppointment(appointment);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Atualizar um compromisso", description = "Atualiza um compromisso existente baseado em seu ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Compromisso atualizado com sucesso"),
      @ApiResponse(responseCode = "404", description = "Compromisso não encontrado")
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
  @Operation(summary = "Excluir um compromisso", description = "Exclui um compromisso baseado em seu ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Compromisso excluído com sucesso"),
      @ApiResponse(responseCode = "404", description = "Compromisso não encontrado")
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
