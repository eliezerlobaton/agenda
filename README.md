#API RESTFul para agenda pessoal

```mermaid
classDiagram
    class Appointment {
        -Long id
        -String title
        -LocalDateTime startDate
        -LocalDateTime endDate
        -String description
        +getId() Long
        +setId(Long id)
        +getTitle() String
        +setTitle(String title)
        +getStartDate() LocalDateTime
        +setStartDate(LocalDateTime startDate)
        +getEndDate() LocalDateTime
        +setEndDate(LocalDateTime endDate)
        +getDescription() String
        +setDescription(String description)
    }

    class AppointmentRepository {
        <<interface>>
        +findAll() List~Appointment~
        +findById(Long id) Optional~Appointment~
        +save(Appointment appointment) Appointment
        +delete(Appointment appointment)
    }

    class AppointmentService {
        -AppointmentRepository appointmentRepository
        +getAllAppointments() List~Appointment~
        +getAppointmentById(Long id) Optional~Appointment~
        +createAppointment(Appointment appointment) Appointment
        +updateAppointment(Long id, Appointment appointmentDetails) Appointment
        +deleteAppointment(Long id)
    }

    class AppointmentController {
        -AppointmentService appointmentService
        +getAllAppointments() List~Appointment~
        +getAppointmentById(Long id) ResponseEntity~Appointment~
        +createAppointment(Appointment appointment) Appointment
        +updateAppointment(Long id, Appointment appointment) ResponseEntity~Appointment~
        +deleteAppointment(Long id) ResponseEntity~Void~
    }

    AppointmentController o--> AppointmentService : depends on
    AppointmentService o--> AppointmentRepository : depends on
    AppointmentRepository o--> Appointment : operates on
    ```