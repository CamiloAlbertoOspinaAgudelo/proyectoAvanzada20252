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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserDTO authenticatedUser = getAuthenticatedUser();

        // Recuperación del usuario
        Optional<User> userOpt = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (userOpt.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!authenticatedUser.id().equals(id)) {
            throw new Exception("No tienes permiso para ver este usuario.");
        }

        return userMapper.toUserDTO(userOpt.get());
    }

    @Override
    public void delete(Long id) throws Exception {
        UserDTO authenticatedUser = getAuthenticatedUser();

        // Recuperación del usuario
        Optional<User> user = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (user.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!authenticatedUser.id().equals(id)) {
            throw new Exception("No tienes permiso para eliminar este usuario.");
        }

        userRepository.delete(user.get());
    }

    @Override
    public void update(Long id, EditUserDTO userDTO) throws Exception{
        UserDTO authenticatedUser = getAuthenticatedUser();

        // Recuperación del usuario
        Optional<User> user = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (user.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!authenticatedUser.id().equals(id)) {
            throw new Exception("No tienes permiso para modificar este usuario.");
        }

        User newUser = user.get();
        newUser.setName(userDTO.name());
        newUser.setPhone(userDTO.phone());
        newUser.setDateBirth(userDTO.dateBirth());
        newUser.setPhotoUrl(userDTO.photoUrl());

        userRepository.save(newUser);
    }

    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) throws Exception{
        UserDTO authenticatedUser = getAuthenticatedUser();

        // Recuperación del usuario
        Optional<User> userOpt = userRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (userOpt.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!authenticatedUser.id().equals(id)) {
            throw new Exception("No tienes permiso para modificar este usuario.");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new Exception("La contraseña no es correcta.");
        }

        // Verificar que la nueva contraseña no sea igual a la anterior
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new Exception("La nueva contraseña no puede ser igual a la anterior.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean emailExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public UserDTO getAuthenticatedUser() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idUser = Long.parseLong(userDetails.getUsername());
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new Exception("Usuario autenticado no encontrado."));
        return userMapper.toUserDTO(user);
    }
}
