package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable String id, @Valid @RequestBody EditUserDTO userDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> get(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    @PostMapping("/host")
    public ResponseEntity<ResponseDTO<String>> createHost(@Valid @RequestBody CreateHostDTO hostDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }

    @GetMapping("/{id}/accommodations")
    public ResponseEntity<ResponseDTO<HostDTO>> getAccommodations(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    @GetMapping("/{id}/accomodations/reserves")
    public ResponseEntity<ResponseDTO<List<ReserveDTO>>> listReserves(
            @RequestParam int page, @RequestParam int size, @RequestParam(required = false) String priceNight,
            @RequestParam(required = false) String city, @RequestParam(required = false) String status,
            @RequestParam(required = false) String checkIn, @RequestParam(required = false) String checkOut) throws Exception {
        List<ReserveDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}
