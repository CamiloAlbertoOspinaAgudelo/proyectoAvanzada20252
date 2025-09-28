package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.service.interfaces.HostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HostServiceImpl implements HostService {

    @Override
    public List<AccommodationDTO> getPlaces(String id){
        List<AccommodationDTO> accommodationDTOS = new ArrayList<>();

        return accommodationDTOS;
    }

    @Override
    public List<ReserveDTO> listReserves(String id) throws Exception{
        List<ReserveDTO> reservesDTOS = new ArrayList<>();

        return  reservesDTOS;
    }
}
