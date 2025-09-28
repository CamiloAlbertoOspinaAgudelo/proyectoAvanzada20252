package co.edu.uniquindio.application.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Location {
    private double lat;
    private double lng;
}
