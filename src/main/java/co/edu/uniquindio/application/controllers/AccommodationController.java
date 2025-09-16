package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.AccommodationDTO;
import co.edu.uniquindio.application.dto.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.ResponseDTO;
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

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CreateAccommodationDTO accommodationDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El alojamiento ha sido creado"));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<AccommodationDTO>>> listAll(
            @RequestParam(required = false) String city, @RequestParam(required = false) LocalDate dateIn,
            @RequestParam(required = false) LocalDate dateOut, @RequestParam(required = false) double priceMin,
            @RequestParam(required = false) double priceOut, @RequestParam(required = false)
            String[] services) throws Exception{
        List<AccommodationDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}
