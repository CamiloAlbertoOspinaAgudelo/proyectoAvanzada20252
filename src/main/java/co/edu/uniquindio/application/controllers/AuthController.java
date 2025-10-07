package co.edu.uniquindio.application.controllers;


import co.edu.uniquindio.application.dto.auth.LogInDTO;
import co.edu.uniquindio.application.dto.auth.RecoverDTO;
import co.edu.uniquindio.application.dto.auth.ResetDTO;
import co.edu.uniquindio.application.dto.auth.TokenDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.service.interfaces.AuthService;
import co.edu.uniquindio.application.service.interfaces.PasswordResetService;
import co.edu.uniquindio.application.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    //register
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<String>> register(@Valid @RequestBody CreateUserDTO userDTO) throws Exception{
        userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El registro ha sido exitoso"));
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TokenDTO>> login(@Valid @RequestBody LogInDTO logInDTO) throws Exception{
        TokenDTO token = authService.login(logInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(false, token));
    }

    //solicitar contraseña
    @PostMapping("/recover")
    public ResponseEntity<ResponseDTO<String>> recover(@Valid @RequestBody RecoverDTO recoverDTO) throws Exception{
        passwordResetService.sendCodeReset(recoverDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(false, "Se envio el codigo exitosamente"));
    }

    //resetear contraseña
    @PostMapping("/reset")
    public ResponseEntity<ResponseDTO<String>> reset(@Valid @RequestBody ResetDTO resetDTO) throws Exception{
        passwordResetService.resetPassword(resetDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(false, "Se reseteo la contraseña exitosamente"));
    }
}
