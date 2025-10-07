package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.EmailDTO;
import co.edu.uniquindio.application.dto.auth.RecoverDTO;
import co.edu.uniquindio.application.dto.auth.ResetDTO;
import co.edu.uniquindio.application.model.entity.PasswordResetCode;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.repositories.PasswordResetCodeRepository;
import co.edu.uniquindio.application.repositories.UserRepository;
import co.edu.uniquindio.application.service.interfaces.EmailService;
import co.edu.uniquindio.application.service.interfaces.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetCodeRepository passwordResetCodeRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendCodeReset(RecoverDTO recoverDTO) throws Exception {

        User user = userRepository.findByEmail(recoverDTO.email()).orElseThrow( () -> new Exception("No exisgte el usuario") );
        String code = generateCode();

        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setCode(code);
        passwordResetCode.setUser(user);
        passwordResetCode.setSent(LocalDateTime.now());
        passwordResetCodeRepository.save(passwordResetCode);

        //enviar el correo electronico al usuario con el code
        emailService.sendMail(
                new EmailDTO(
                        "Ha soilicitado un cambio de contraseña",
                        "Para cambiar la .... use este codigo "+code,
                        user.getEmail()
                )
        );

    }

    @Override
    public void resetPassword(ResetDTO resetDTO) throws Exception{
        User user = userRepository.findByEmail(resetDTO.email()).orElseThrow( () -> new Exception("No exisgte el usuario") );
        List<PasswordResetCode> passwordResetCodes = passwordResetCodeRepository.getCodeByUser(user.getId(), Sort.by(Sort.Direction.DESC, "sent"));

        if( passwordResetCodes.isEmpty() ){
            throw new Exception("Usted no ha solicitado restablecer la contraseña");
        }

        if( !passwordResetCodes.getFirst().getCode().equals(resetDTO.code()) ){
            throw new Exception("El codigo es inválido");
        }

        if( passwordResetCodes.getFirst().getSent().plusMinutes(15).isBefore(LocalDateTime.now()) ){
            throw new Exception("El codigo ya venció");
        }

        user.setPassword( passwordEncoder.encode( resetDTO.password() ) );
        userRepository.save(user);

    }

    public String generateCode(){
        String numeros = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        Random random = new Random();

        while(code.length() < 8){
            code += numeros.charAt( random.nextInt(numeros.length()) );
        }

        return code;
    }

}
