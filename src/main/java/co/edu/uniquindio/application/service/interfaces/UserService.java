package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.dto.user.EditUserDTO;
import co.edu.uniquindio.application.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    void create(CreateUserDTO userDTO) throws Exception;
    UserDTO get(String id) throws Exception;
    void delete(String id) throws Exception;
    void update(String id, EditUserDTO userDTO) throws Exception;
    void changePassword(String id, String oldPassword, String newPassword) throws Exception;
}
