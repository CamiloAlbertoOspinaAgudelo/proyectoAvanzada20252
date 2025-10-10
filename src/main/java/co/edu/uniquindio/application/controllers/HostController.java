package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.dto.user.*;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.service.interfaces.HostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/host")
@RequiredArgsConstructor
public class HostController {

    private final HostService hostService;

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> createHost(@Valid @RequestBody CreateHostDTO hostDTO) throws Exception{
        hostService.create(hostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<HostDTO>> get(@PathVariable Long id) throws Exception{
        HostDTO hostDTO = hostService.get(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, hostDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable Long id, @Valid @RequestBody EditHostDTO hostDTO) throws Exception{
        hostService.update(id, hostDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable Long id) throws Exception{
        hostService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El host ha sido eliminado"));
    }

    // listar alojamientos del host
    @GetMapping("/{id}/accommodations")
    public ResponseEntity<ResponseDTO<List<AccommodationDTO>>> getAccommodations(@PathVariable Long id, @RequestParam int page) throws Exception{
        List<AccommodationDTO> places = hostService.getPlaces(id, page);
        return ResponseEntity.ok(new ResponseDTO<>(false, places));
    }

    // Listar las reservas de un alojamiento
    @GetMapping("/{id}/accomodations/reserves")
    public ResponseEntity<ResponseDTO<List<ReserveDTO>>> listReserves(
            @PathVariable Long id, @RequestParam int page, @RequestParam(required = false) String city,
            @RequestParam(required = false) ReserveStatus status, @RequestParam(required = false) LocalDateTime checkIn,
            @RequestParam(required = false) LocalDateTime checkOut) throws Exception {
        List<ReserveDTO> list = hostService.listReserves(id, city, status, checkIn, checkOut, page);
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}
