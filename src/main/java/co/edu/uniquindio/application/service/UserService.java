package co.edu.uniquindio.application.service;

import co.edu.uniquindio.application.dto.CreateUserDTO;
import co.edu.uniquindio.application.dto.UserDTO;

public interface UserService {

    void create(CreateUserDTO userDTO) throws Exception;
    UserDTO get(String id) throws Exception;
    void delete(String id) throws Exception;
    UserDTO edit(String id, UserDTO userDTO) throws Exception;
    void changePassword(String id, String oldPassword, String newPassword) throws Exception;

}
