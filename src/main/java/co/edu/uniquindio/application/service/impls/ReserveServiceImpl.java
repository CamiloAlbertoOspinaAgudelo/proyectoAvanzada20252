package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.mappers.ReserveMapper;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final Map<String, Reservation> reserveStore = new ConcurrentHashMap<>();
    private final ReserveMapper reserveMapper;

    @Override
    public void create(CreateReserveDTO reserveDTO) throws Exception{
        Reservation newReserve = reserveMapper.toEntity(reserveDTO);
        reserveStore.put(newReserve.id, newReserve);
    }
    @Override
    public List<ReserveDTO> listAll(){
        List<ReserveDTO> list = new ArrayList<>();
        for (Reservation reservation : reserveStore.values()){
            list.add(reserveMapper.toReserveDTO(reservation));
        }
        return list;
    }
}
