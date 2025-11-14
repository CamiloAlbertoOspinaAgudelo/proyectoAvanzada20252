package co.edu.uniquindio.application.controllers;

import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.model.enums.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {

    @GetMapping
    public ResponseEntity<ResponseDTO<String[]>> getServices(){
        String[] services = listServices();
        return ResponseEntity.ok(new ResponseDTO<>(false, services));
    }

    private String[] listServices() {
        Service[] enumValues = Service.values(); // obtiene todos los valores del enum
        String[] services = new String[enumValues.length];
        for (int i = 0; i < enumValues.length; i++) {
            services[i] = enumValues[i].name(); // convierte cada valor del enum a String
        }
        return services;
    }
}
