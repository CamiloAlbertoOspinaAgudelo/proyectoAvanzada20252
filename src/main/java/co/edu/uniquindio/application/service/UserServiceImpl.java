package co.edu.uniquindio.application.service;

import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.mappers.UserMapper;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(CreateUserDTO userDTO) throws Exception{
        if (emailExist(userDTO.email())){
            throw  new Exception("Email already exists");
        }
        User newUser = userMapper.toEntity(userDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }


    private User getUser(String id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    private String encode(String password){
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean emailExist(String email){

        Optional<User> optionalUser = userRepository.searchEmail(email);
        return  optionalUser.isPresent();
    }
}
