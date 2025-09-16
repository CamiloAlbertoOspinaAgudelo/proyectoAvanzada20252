package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.CreateHostDTO;
import co.edu.uniquindio.application.dto.CreateUserDTO;
import co.edu.uniquindio.application.dto.EditUserDTO;
import co.edu.uniquindio.application.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public void create(CreateUserDTO userDTO) throws Exception{
    }

    @PutMapping
    public void edit(EditUserDTO userDTO) throws Exception{
    }

    @DeleteMapping("/{id}")
    public void delete(String id) throws Exception{
    }

    @GetMapping("/{id}")
    public UserDTO get(String id) throws Exception{
        return null;
    }

    @PostMapping("/host")
    public void createHost(CreateHostDTO hostDTO) throws Exception{

    }
}
