package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    // crear alojamiento
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CreateAccommodationDTO accommodationDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El alojamiento ha sido creado"));
    }

    // buscar alojamiento
    @GetMapping
    public ResponseEntity<ResponseDTO<List<AccommodationDTO>>> listAll(
            @RequestParam(required = false) String city, @RequestParam(required = false) LocalDate dateIn,
            @RequestParam(required = false) LocalDate dateOut, @RequestParam(required = false) double priceMin,
            @RequestParam(required = false) double priceOut, @RequestParam(required = false)
            String[] services, @RequestParam int page, @RequestParam int size) throws Exception{
        List<AccommodationDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }

    //detalle alojamiento
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AccommodationDTO>> get(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    // editar alojamiento
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<AccommodationDTO>> edit(@PathVariable String id, @Valid @RequestBody EditAccommodationDTO accommodationDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    //eliminar alojamiento
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "El alojamiento ha sido eliminado"));
    }

    //subir imagen
    @PostMapping("/{id}/images")
    public ResponseEntity<ResponseDTO<String>> addImage(@PathVariable String id) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "La imagen ha sido añadida"));
    }

    //delete imagen
    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseEntity<ResponseDTO<String>> deleteImage(@PathVariable String id, @PathVariable String imageId) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "El alojamiento ha sido eliminado"));
    }

    //obtener metricas
    @GetMapping("/{id}/metrics")
    public ResponseEntity<ResponseDTO<AccommodationDTO>> getMetric(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    //obtener comentarios del alojamiento ****confirmar****
    @GetMapping("/{id}/reviews")
    public ResponseEntity<ResponseDTO<List<ReviewDTO>>> getReviews(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }
}
