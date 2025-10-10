package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.EmailDTO;
import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.mappers.ReserveMapper;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.repositories.ReserveRepository;
import co.edu.uniquindio.application.service.interfaces.EmailService;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;
    private final EmailService emailService;

    @Override
    public void create(CreateReserveDTO reserveDTO) throws Exception{
        Reservation newReserve = reserveMapper.toEntity(reserveDTO);
        reserveRepository.save(newReserve);
    }
    @Override
    public List<ReserveDTO> listAll(){
        return reserveRepository.findAll().stream().map(reserveMapper::toReserveDTO).toList();
    }

    @Override
    public ReserveDTO get(Long id) throws Exception{
        Optional<Reservation> reservation = reserveRepository.findById(id);
        if (reservation.isEmpty()){
            throw new Exception("Reserva no encontrada.");
        }
        return reserveMapper.toReserveDTO(reservation.get());
    }

    @Override
    public void cancel(Long id) throws Exception{
        Optional<Reservation> reservationOptional = reserveRepository.findById(id);
        if (reservationOptional.isEmpty()){
            throw new Exception("Reserva no encontrada.");
        }
        Reservation reservation = reservationOptional.get();
        reservation.setStatus(ReserveStatus.CANCELLED);
    }

    @Override
    public void sendReminder() throws Exception {
        LocalDate today = LocalDate.now();

        List<Reservation> reservations = reserveRepository.findAllByStatus(ReserveStatus.PENDING);

        for (Reservation reservation: reservations){
            if (reservation.getDateFrom().isBefore(LocalDateTime.now().plusHours(48))){
                EmailDTO emailDTO = new EmailDTO("Recordatorio de reserva","Le recordamos que" +
                        " su reserva del alojamiento: "+ reservation.getAccommodation().getTitle() +
                        " desde la fecha: "+ reservation.getDateTo() + " hasta la fecha: "+ reservation.getDateFrom()+
                        " esta a menos de 48 horas de inicio.",reservation.getUser().getEmail());
                emailService.sendMail(emailDTO);
            }
        }
    }
}
