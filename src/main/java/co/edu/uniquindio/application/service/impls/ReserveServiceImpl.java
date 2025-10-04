package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.mappers.ReserveMapper;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.repositories.BookingRepository;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final BookingRepository bookingRepository;
    private final ReserveMapper reserveMapper;

    @Override
    public void create(CreateReserveDTO reserveDTO) throws Exception{
        Reservation newReserve = reserveMapper.toEntity(reserveDTO);
        bookingRepository.save(newReserve);
    }
    @Override
    public List<ReserveDTO> listAll(){
        return bookingRepository.findAll().stream().map(reserveMapper::toReserveDTO).toList();
    }

    @Override
    public ReserveDTO get(Long id) throws Exception{
        Optional<Reservation> reservation = bookingRepository.findById(id);
        if (reservation.isEmpty()){
            throw new Exception("Reserva no encontrada.");
        }
        return reserveMapper.toReserveDTO(reservation.get());
    }

    @Override
    public void cancel(Long id) throws Exception{
        Optional<Reservation> reservationOptional = bookingRepository.findById(id);
        if (reservationOptional.isEmpty()){
            throw new Exception("Reserva no encontrada.");
        }
        Reservation reservation = reservationOptional.get();
        reservation.setStatus(Status.INACTIVE);
    }
}
