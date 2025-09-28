package co.edu.uniquindio.application.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Address {
    private String city;
    private String address;
    private Location location;
}
