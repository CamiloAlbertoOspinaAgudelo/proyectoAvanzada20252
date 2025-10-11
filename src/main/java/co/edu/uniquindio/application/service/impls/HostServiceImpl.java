package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.user.*;
import co.edu.uniquindio.application.exceptions.UnauthorizedException;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.AccommodationMapper;
import co.edu.uniquindio.application.mappers.HostMapper;
import co.edu.uniquindio.application.mappers.ReserveMapper;
import co.edu.uniquindio.application.mappers.UserMapper;
import co.edu.uniquindio.application.model.entity.Accommodation;
import co.edu.uniquindio.application.model.entity.HostProfile;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.repositories.AccommodationRepository;
import co.edu.uniquindio.application.repositories.ReserveRepository;
import co.edu.uniquindio.application.repositories.HostRepository;
import co.edu.uniquindio.application.repositories.UserRepository;
import co.edu.uniquindio.application.service.interfaces.HostService;
import co.edu.uniquindio.application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final HostMapper hostMapper;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;
    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void create(CreateHostDTO hostDTO) throws Exception{
        //Validación para verificar si el email ya está en uso
        if(emailExist(hostDTO.email())){
            throw new ValueConflictException("El correo electrónico ya está en uso.");
        }

        // Transformación del DTO a User
        HostProfile host = hostMapper.toEntity(hostDTO);

        User user = new User();
        user.setEmail(hostDTO.email());
        user.setPassword(passwordEncoder.encode(hostDTO.password()));
        user.setName(hostDTO.name());
        user.setPhone(hostDTO.phone());
        user.setDateBirth(hostDTO.dateBirth());
        user.setPhotoUrl(hostDTO.photoUrl());

        host.setUser(user);

        hostRepository.save(host);
    }

    @Override
    public HostDTO get(Long id) throws Exception{
        User user = userService.getAuthenticatedUser();
        HostProfile host = hostRepository.findByUserId(user.getId()).orElseThrow(() -> new UnauthorizedException("Usted no es un host"));

        return hostMapper.toHostDTO(host);
    }

    @Override
    public void update(Long id, EditHostDTO hostDTO) throws Exception{
        User user = userService.getAuthenticatedUser();
        HostProfile host = hostRepository.findByUserId(user.getId()).orElseThrow(() -> new UnauthorizedException("Usted no es un host"));

        user.setName(hostDTO.name());
        user.setPhone(hostDTO.phone());
        user.setDateBirth(hostDTO.dateBirth());
        user.setPhotoUrl(hostDTO.photoUrl());
        host.setDescription(hostDTO.description());
        host.setDocuments(hostDTO.documents());

        userRepository.save(user);
        hostRepository.save(host);
    }

    @Override
    public void delete(Long id) throws Exception {
        User user = userService.getAuthenticatedUser();
        HostProfile host = hostRepository.findByUserId(user.getId()).orElseThrow(() -> new UnauthorizedException("Usted no es un host"));

        hostRepository.delete(host);
    }

    @Override
    public List<AccommodationDTO> getPlaces(Long id, int page) throws  Exception{
        User user = userService.getAuthenticatedUser();
        HostProfile host = hostRepository.findByUserId(user.getId()).orElseThrow(() -> new UnauthorizedException("Usted no es un host"));
        Pageable pageable = PageRequest.of(page, 10);
        Page<Accommodation> places = accommodationRepository.findByHost_Id(host.getId(), pageable);

        return places.getContent().stream().map(accommodationMapper::toAccommodationDTO).toList();
    }

    @Override
    public List<ReserveDTO> listReserves(Long id, String city, ReserveStatus status, LocalDateTime checkIn, LocalDateTime checkOut, int page) throws Exception{
        User user = userService.getAuthenticatedUser();
        HostProfile host = hostRepository.findByUserId(user.getId()).orElseThrow(() -> new UnauthorizedException("Usted no es un host"));
        Pageable pageable = PageRequest.of(page, 10);
        Page<Reservation> reservations = reserveRepository.findAllByAccommodation_Host_Id(host.getId(), city, status, checkIn, checkOut, pageable);

        return  reservations.getContent().stream().map(reserveMapper::toReserveDTO).toList();
    }

    public boolean emailExist(String email){
        return hostRepository.findByUser_Email(email).isPresent() || userRepository.findByEmail(email).isPresent();
    }
}
