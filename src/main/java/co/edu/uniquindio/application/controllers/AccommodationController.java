package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.MetricsDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;
import co.edu.uniquindio.application.service.interfaces.AccommodationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
            @RequestParam(required = false) String city, @RequestParam(required = false) LocalDate dateIn,
            @RequestParam(required = false) LocalDate dateOut, @RequestParam(required = false) double priceMin,
            @RequestParam(required = false) double priceOut, @RequestParam(required = false)
            String[] services, @RequestParam int page, @RequestParam int size) throws Exception{
        List<AccommodationDTO> list = accommodationService.listAll();
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

    //subir imagen
    @PostMapping("/{id}/images")
    public ResponseEntity<ResponseDTO<String>> addImage(@PathVariable Long id, @Valid @RequestBody String imageUrl) throws Exception{
        accommodationService.addImage(id, imageUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "La imagen ha sido añadida"));
    }

    //delete imagen
    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseEntity<ResponseDTO<String>> deleteImage(@PathVariable Long id, @PathVariable String imageId) throws Exception{
        accommodationService.deleteImage(id, imageId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El alojamiento ha sido eliminado"));
    }

    //obtener metricas
    @GetMapping("/{id}/metrics")
    public ResponseEntity<ResponseDTO<MetricsDTO>> getMetric(@PathVariable Long id, @Valid @RequestBody LocalDate to, @Valid @RequestBody LocalDate from) throws Exception{
//        accommodationService.getMetrics(id, from, to);
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    //obtener comentarios del alojamiento
    @GetMapping("/{id}/reviews")
    public ResponseEntity<ResponseDTO<List<ReviewDTO>>> getReviews(@PathVariable Long id) throws Exception{
//        accommodationService.getReviews(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }
}
