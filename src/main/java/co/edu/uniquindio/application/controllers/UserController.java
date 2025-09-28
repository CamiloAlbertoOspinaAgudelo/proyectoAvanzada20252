package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.dto.user.CreateHostDTO;
import co.edu.uniquindio.application.dto.user.EditUserDTO;
import co.edu.uniquindio.application.dto.user.HostDTO;
import co.edu.uniquindio.application.dto.user.UserDTO;
import co.edu.uniquindio.application.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> edit(@PathVariable String id, @Valid @RequestBody EditUserDTO userDTO) throws Exception{
        userService.update(id, userDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> delete(@PathVariable String id) throws Exception{
        userService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> get(@PathVariable String id) throws Exception{
        UserDTO userDTO = userService.get(id);
        return ResponseEntity.ok(new ResponseDTO<>(false, userDTO));
    }

    @PostMapping("/host")
    public ResponseEntity<ResponseDTO<String>> createHost(@Valid @RequestBody CreateHostDTO hostDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }


    // listar alojamientos del host
    @GetMapping("/{id}/accommodations")
    public ResponseEntity<ResponseDTO<HostDTO>> getAccommodations(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, null));
    }

    // Listar las reservas de un alojamiento
    @GetMapping("/{id}/accomodations/reserves")
    public ResponseEntity<ResponseDTO<List<ReserveDTO>>> listReserves(
            @RequestParam int page, @RequestParam int size, @RequestParam(required = false) String priceNight,
            @RequestParam(required = false) String city, @RequestParam(required = false) String status,
            @RequestParam(required = false) String checkIn, @RequestParam(required = false) String checkOut) throws Exception {
        List<ReserveDTO> list = new ArrayList<>();
        return ResponseEntity.ok(new ResponseDTO<>(false, list));
    }
}
