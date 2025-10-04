package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.auth.LogInDTO;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    public TokenDTO login(LogInDTO loginDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.email());

        if(optionalUser.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        User user = optionalUser.get();

        // Verificar si la contraseña es correcta usando el PasswordEncoder
        if(!passwordEncoder.matches(loginDTO.password(), user.getPassword())){
            throw new Exception("El usuario no existe");
        }

        String token = jwtUtils.generateToken(user.getId(), createClaims(user));
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
