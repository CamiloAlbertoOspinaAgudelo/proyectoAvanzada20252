package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.auth.LogInDTO;
import co.edu.uniquindio.application.dto.auth.TokenDTO;

public interface AuthService {
    TokenDTO login(LogInDTO loginDTO) throws Exception;
}
