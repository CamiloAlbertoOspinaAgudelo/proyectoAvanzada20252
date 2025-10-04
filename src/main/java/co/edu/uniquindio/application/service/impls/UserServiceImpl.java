package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.dto.user.EditUserDTO;
import co.edu.uniquindio.application.dto.user.UserDTO;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.UserMapper;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.repositories.UserRepository;
import co.edu.uniquindio.application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(CreateUserDTO userDTO) throws Exception{
        //Validación para verificar si el email ya está en uso
        if(emailExist(userDTO.email())){
            throw new ValueConflictException("El correo electrónico ya está en uso.");
        }

        // Transformación del DTO a User
        User newUser = userMapper.toEntity(userDTO);
        newUser.setPassword(passwordEncoder.encode(userDTO.password()));

        // Almacenamiento del usuario
        userRepository.save(newUser);
    }

    @Override
    public UserDTO get(Long id) throws Exception{
        // Recuperación del usuario
        Optional<User> user = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (user.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        return userMapper.toUserDTO(user.get());
    }

    @Override
    public void delete(Long id) throws Exception {
        // Recuperación del usuario
        Optional<User> user = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (user.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        userRepository.delete(user.get());
    }

    @Override
    public void update(Long id, EditUserDTO userDTO) throws Exception{
        // Recuperación del usuario
        Optional<User> user = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (user.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        User newUser = user.get();
        newUser.setName(userDTO.name());
        newUser.setPhone(userDTO.phone());
        newUser.setDateBirth(userDTO.dateBirth());
        newUser.setPhotoUrl(userDTO.photoUrl());

    }

    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) throws Exception{

        // Recuperación del usuario
        Optional<User> userOpt = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (userOpt.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new Exception("La nueva contrase;a no puede ser igual a la contrase;a ya existente.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));

    }

    private String encode(String password){
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean emailExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
