package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.EmailDTO;
import co.edu.uniquindio.application.dto.booking.CreateReserveDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.exceptions.BadRequestException;
import co.edu.uniquindio.application.exceptions.NotFoundException;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.ReserveMapper;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.repositories.AccommodationRepository;
import co.edu.uniquindio.application.repositories.ReserveRepository;
import co.edu.uniquindio.application.repositories.UserRepository;
import co.edu.uniquindio.application.service.interfaces.EmailService;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import co.edu.uniquindio.application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final UserService userService;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    @Override
    public void create(CreateReserveDTO reserveDTO) throws Exception{
        User user = userService.getAuthenticatedUser();
        Reservation newReserve = reserveMapper.toEntity(reserveDTO);

        if (newReserve.getDateFrom().isBefore(newReserve.getDateTo()) | newReserve.getDateFrom().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("La fecha no es valida");
        }

        if (reserveRepository.existsOverlappingReserve(reserveDTO.accomodationId(), reserveDTO.checkIn(), reserveDTO.checkOut())){
            throw new ValueConflictException("El alojamiento esta ocupado en la fecha seleccionada");
        }

        if (accommodationRepository.findById(reserveDTO.accomodationId()).isEmpty()){
            throw new NotFoundException("El alojamiento no existe");
        }

        newReserve.setUser(user);
        reserveRepository.save(newReserve);
    }

    @Override
    public List<ReserveDTO> listAll() throws Exception{
        User user = userService.getAuthenticatedUser();
        Pageable pageable = PageRequest.of(0, 10);
        return reserveRepository.findAllByUser_Id(user.getId(), pageable).stream().map(reserveMapper::toReserveDTO).toList();
    }

    @Override
    public ReserveDTO get(Long id) throws Exception{
        User user = userService.getAuthenticatedUser();
        Optional<Reservation> reservation = reserveRepository.findByIdAndUser_Id(id, user.getId());
        if (reservation.isEmpty()){
            throw new NotFoundException("Reserva no encontrada.");
        }
        return reserveMapper.toReserveDTO(reservation.get());
    }

    @Override
    public void cancel(Long id) throws Exception{
        User user = userService.getAuthenticatedUser();
        Optional<Reservation> reservationOptional = reserveRepository.findByIdAndUser_Id(id, user.getId());
        if (reservationOptional.isEmpty()){
            throw new NotFoundException("Reserva no encontrada.");
        }
        Reservation reservation = reservationOptional.get();
        reservation.setStatus(ReserveStatus.CANCELLED);
    }

    @Override
    public void sendReminder() throws Exception {
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
