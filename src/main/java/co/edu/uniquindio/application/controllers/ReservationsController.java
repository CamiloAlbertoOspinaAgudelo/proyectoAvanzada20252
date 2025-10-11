package co.edu.uniquindio.application.controllers;


import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationsController {

    private final ReserveService reserveService;

    //crear reserva
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CreateReserveDTO reserveDTO) throws Exception{
        reserveService.create(reserveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "La reserva ha sido creada"));
    }

    //obtener reservas de usuario
    @GetMapping
    public ResponseEntity<ResponseDTO<List<ReserveDTO>>> listAll(
            @RequestParam int page, @RequestParam(required = false) String priceNight,
            @RequestParam(required = false) String city, @RequestParam(required = false) ReserveStatus status,
            @RequestParam(required = false) LocalDateTime checkIn, @RequestParam(required = false) LocalDateTime checkOut) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(false, reserveService.listAll(page)));
    }

    //obtener detalle reserva
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ReserveDTO>> get(@PathVariable Long id) throws Exception{
        ReserveDTO reserveDTO = reserveService.get(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, reserveDTO));
    }

    //eliminarReserva
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> cancel(@PathVariable Long id) throws Exception{
        reserveService.cancel(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "La reserva ha sido cancelada"));
    }
}
