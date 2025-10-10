package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.user.CreateUserDTO;
import co.edu.uniquindio.application.dto.user.EditUserDTO;
import co.edu.uniquindio.application.dto.user.UserDTO;
import co.edu.uniquindio.application.model.entity.User;

import java.util.List;

public interface UserService {

    void create(CreateUserDTO userDTO) throws Exception;
    UserDTO get(Long id) throws Exception;
    void delete(Long id) throws Exception;
    void update(Long id, EditUserDTO userDTO) throws Exception;
    void changePassword(Long id, String oldPassword, String newPassword) throws Exception;
    User getAuthenticatedUser() throws Exception;

}
