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
    private final Map<String, User> userStore = new ConcurrentHashMap<>();
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
        newUser.setPassword(encode(userDTO.password()));

        // Almacenamiento del usuario
        userStore.put(newUser.getId(), newUser);
    }

    @Override
    public UserDTO get(String id) throws Exception{
        // Recuperación del usuario
        User user = userStore.get(id);

        // Si el usuario no existe, lanzar una excepción
        if (user == null) {
            throw new Exception("Usuario no encontrado.");
        }

        return userMapper.toUserDTO(user);
    }

    @Override
    public void delete(String id) throws Exception {
        // Recuperación del usuario
        User user = userStore.get(id);

        // Si el usuario no existe, lanzar una excepción
        if (user == null) {
            throw new Exception("Usuario no encontrado.");
        }

        // Eliminación del usuario
        user.setStatus(Status.INACTIVE);
    }

    @Override
    public void update(String id, EditUserDTO userDTO) throws Exception{
        // Recuperación del usuario
        User user = userStore.get(id);

        // Si el usuario no existe, lanzar una excepción
        if (user == null) {
            throw new Exception("Usuario no encontrado.");
        }

        user.setName(userDTO.name());
        user.setPhone(userDTO.phone());
        user.setPhotoUrl(userDTO.photoUrl());

    }

    @Override
    public void changePassword(String id, String oldPassword, String newPassword) throws Exception{

        // Recuperación del usuario
        User user = userStore.get(id);

        // Si el usuario no existe, lanzar una excepción
        if (user == null) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new Exception("Usuario no encontrado.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));

    }

    private String encode(String password){
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean emailExist(String email){
        return userStore.values().stream().anyMatch(
                u -> u.getEmail().equalsIgnoreCase(email)
        );
    }
}
