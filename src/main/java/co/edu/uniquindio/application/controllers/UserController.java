package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.CreateUserDTO;
import co.edu.uniquindio.application.dto.EditUserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public void create(CreateUserDTO userDTO) throws Exception{
    }

    public void edit(EditUserDTO userDTO) throws Exception{
    }

    public void delete(String id) throws Exception{
    }

    public UserDTO get(String id) throws Exception{
    }

    public List<UserDTO> listAll(){
    }
}
