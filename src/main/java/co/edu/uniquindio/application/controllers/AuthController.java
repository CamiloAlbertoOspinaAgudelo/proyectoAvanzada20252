package co.edu.uniquindio.application.controllers;


import co.edu.uniquindio.application.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //register ****esta igual a crear usuario****
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<String>> register(@Valid @RequestBody CreateUserDTO createUserDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> login(@Valid @RequestBody LogInDTO logInDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El login ha sido exitoso"));
    }

    //solicitar contraseña
    @PostMapping("/recover")
    public ResponseEntity<ResponseDTO<String>> recover(@Valid @RequestBody UserDTO userDTO/*como cojo solo el email?*/) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "Se envio el codigo exitosamente"));
    }

    //resetear contraseña
    @PostMapping("/reset")
    public ResponseEntity<ResponseDTO<String>> reset(@Valid @RequestBody RecoverDTO recoverDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "Se reseteo la contraseña exitosamente"));
    }
}
