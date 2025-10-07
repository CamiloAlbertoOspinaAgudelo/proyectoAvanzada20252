package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.auth.RecoverDTO;
import co.edu.uniquindio.application.dto.auth.ResetDTO;

public interface PasswordResetService {
    void sendCodeReset(RecoverDTO recoverDTO) throws Exception;
    void resetPassword(ResetDTO resetDTO) throws Exception;
}
