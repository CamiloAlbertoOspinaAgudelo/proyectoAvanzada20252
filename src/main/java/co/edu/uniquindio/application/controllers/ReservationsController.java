package co.edu.uniquindio.application.controllers;


import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {

    //crear reserva
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CreateReserveDTO reserveDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "La reserva ha sido creada"));
    }

    //obtener reservas de usuario
    @GetMapping
    public ResponseEntity<ResponseDTO<List<ReserveDTO>>> listAll(
            @RequestParam int page, @RequestParam int size, @RequestParam(required = false) String priceNight,
            @RequestParam(required = false) String city, @RequestParam(required = false) /*ReserveStatus*/String status,
            @RequestParam(required = false) /*LocalDateTime*/String checkIn, @RequestParam(required = false) /*LocalDateTime*/String checkOut) throws Exception {
        List<ReserveDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    //obtener detalle reserva
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ReserveDTO>> get(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    //eliminarReserva
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> cancel(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "La reserva ha sido cancelada"));
    }
}
