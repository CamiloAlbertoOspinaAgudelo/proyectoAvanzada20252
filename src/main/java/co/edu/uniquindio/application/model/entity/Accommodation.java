package co.edu.uniquindio.application.model.entity;

import co.edu.uniquindio.application.dto.accommodation.AddressDTO;
import co.edu.uniquindio.application.model.enums.Service;
import co.edu.uniquindio.application.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Accommodation {
    private String id;
    private String title;
    private String description;
    private Address address;
    private double PriceNight;
    private int capMax;
    private List<Service> services;
    private Status status;
    private List<String> photoUrls;
}
