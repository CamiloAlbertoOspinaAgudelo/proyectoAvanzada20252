package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.auth.LogInDTO;
import co.edu.uniquindio.application.dto.auth.TokenDTO;
import co.edu.uniquindio.application.exceptions.UnauthorizedException;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.repositories.UserRepository;
import co.edu.uniquindio.application.security.JWTUtils;
import co.edu.uniquindio.application.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDTO login(LogInDTO loginDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.email());

        if(optionalUser.isEmpty()){
            throw new UnauthorizedException("Datos incorrectos");
        }


        User user = optionalUser.get();

        // Verificar si la contraseña es correcta usando el PasswordEncoder
        if(!passwordEncoder.matches(loginDTO.password(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Datos incorrectos 2");
        }

        String token = jwtUtils.generateToken(String.valueOf(user.getId()), createClaims(user));

        return new TokenDTO(token);
    }

    private Map<String, String> createClaims(User user){
        return Map.of(
                "email", user.getEmail(),
                "name", user.getName(),
                "role", "ROLE_"+user.getRol().name()
        );
    }

}
