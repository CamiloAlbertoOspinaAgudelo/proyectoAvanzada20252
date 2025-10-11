package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.MetricsDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.model.enums.Service;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.service.interfaces.AccommodationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;

    // crear alojamiento
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CreateAccommodationDTO accommodationDTO) throws Exception{
        accommodationService.create(accommodationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El alojamiento ha sido creado"));
    }

    // buscar alojamiento
    @GetMapping
    public ResponseEntity<ResponseDTO<List<AccommodationDTO>>> listAll(
            @RequestParam(required = false) String city, @RequestParam(required = false) LocalDateTime dateIn,
            @RequestParam(required = false) LocalDateTime dateOut, @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax, @RequestParam(required = false)
            List<Service> services, @RequestParam int page) throws Exception{
        List<AccommodationDTO> list = accommodationService.listAll(city, dateIn, dateOut, priceMin, priceMax, services, page);
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    //detalle alojamiento
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AccommodationDTO>> get(@PathVariable Long id) throws Exception{
        AccommodationDTO accommodationDTO = accommodationService.get(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, accommodationDTO));
    }

    // editar alojamiento
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable Long id, @Valid @RequestBody EditAccommodationDTO accommodationDTO) throws Exception{
        accommodationService.update(id, accommodationDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El alojamiento ha sido actualizado"));
    }

    //eliminar alojamiento
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable Long id) throws Exception{
        accommodationService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El alojamiento ha sido eliminado"));
    }

    //obtener metricas
    @GetMapping("/{id}/metrics")
    public ResponseEntity<ResponseDTO<MetricsDTO>> getMetric(@PathVariable Long id, @RequestParam(required = false) LocalDate to, @RequestParam(required = false) LocalDate from) throws Exception{
        LocalDateTime fromDate =null;
        LocalDateTime toDate = null;

        if(from != null){
            fromDate = LocalDateTime.of(from.getYear(), from.getMonth(), from.getDayOfMonth(), 0, 0);
        }

        if(to !=null){
            toDate = LocalDateTime.of(to.getYear(), to.getMonth(), to.getDayOfMonth(), 0, 0);
        }

        return ResponseEntity.ok(new ResponseDTO<>(false, accommodationService.getMetrics(id, fromDate, toDate)));
    }

    //obtener comentarios del alojamiento
    @GetMapping("/{id}/reviews")
    public ResponseEntity<ResponseDTO<List<ReviewDTO>>> getReviews(@PathVariable Long id, @RequestParam int page) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, accommodationService.getReviews(id, page)));
    }

    @GetMapping("/{id}/reseves")
    public ResponseEntity<ResponseDTO<List<ReserveDTO>>> getReserves(@PathVariable Long id, @RequestParam(required = false) LocalDateTime to, @RequestParam(required = false) LocalDateTime from, @RequestParam(required = false) ReserveStatus status, @RequestParam int page) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, accommodationService.getReserves(id, from, to, status, page)));
    }
}
