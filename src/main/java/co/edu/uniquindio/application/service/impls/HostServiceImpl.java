package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.booking.ReserveDTO;
import co.edu.uniquindio.application.dto.user.*;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.AccommodationMapper;
import co.edu.uniquindio.application.mappers.HostMapper;
import co.edu.uniquindio.application.mappers.ReserveMapper;
import co.edu.uniquindio.application.mappers.UserMapper;
import co.edu.uniquindio.application.model.entity.Accommodation;
import co.edu.uniquindio.application.model.entity.HostProfile;
import co.edu.uniquindio.application.model.entity.Reservation;
import co.edu.uniquindio.application.model.entity.User;
import co.edu.uniquindio.application.repositories.AccommodationRepository;
import co.edu.uniquindio.application.repositories.ReserveRepository;
import co.edu.uniquindio.application.repositories.HostRepository;
import co.edu.uniquindio.application.repositories.UserRepository;
import co.edu.uniquindio.application.service.interfaces.HostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        HostDTO authenticatedHost = getAuthenticatedHost();

        // Recuperación del usuario
        Optional<HostProfile> hostOpt = hostRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (hostOpt.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!authenticatedHost.id().equals(id)) {
            throw new Exception("No tienes permiso para ver este usuario.");
        }

        return hostMapper.toHostDTO(hostOpt.get());
    }

    @Override
    public void update(Long id, EditHostDTO hostDTO) throws Exception{
        HostDTO hostAuth = getAuthenticatedHost();

        // Recuperación del usuario
        Optional<HostProfile> hostOpt = hostRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (hostOpt.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!hostAuth.id().equals(id)) {
            throw new Exception("No tienes permiso para modificar este usuario.");
        }

        User user = hostOpt.get().getUser();
        user.setName(hostDTO.name());
        user.setPhone(hostDTO.phone());
        user.setDateBirth(hostDTO.dateBirth());
        user.setPhotoUrl(hostDTO.photoUrl());
        hostOpt.get().setDescription(hostDTO.description());
        hostOpt.get().setDocuments(hostDTO.documents());

        userRepository.save(user);
        hostRepository.save(hostOpt.get());
    }

    @Override
    public void delete(Long id) throws Exception {
        HostDTO hostAuth = getAuthenticatedHost();

        // Recuperación del usuario
        Optional<HostProfile> host = hostRepository.findById(id);

        // Si el usuario no existe, lanzar una excepción
        if (host.isEmpty()) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!hostAuth.id().equals(id)) {
            throw new Exception("No tienes permiso para eliminar este usuario.");
        }

        hostRepository.delete(host.get());
    }

    @Override
    public List<AccommodationDTO> getPlaces(Long id) throws  Exception{
        HostDTO hostAuth = getAuthenticatedHost();
        List<Accommodation> places = accommodationRepository.findByHost_Id(hostAuth.id());

        return places.stream()
                .map(accommodationMapper::toAccommodationDTO)
                .toList();
    }

    @Override
    public List<ReserveDTO> listReserves(Long id) throws Exception{
        HostDTO hostAuth = getAuthenticatedHost();
        List<Reservation> reservations = reserveRepository.findAllByAccommodation_Host_Id(hostAuth.id());

        if (!hostAuth.id().equals(id)){
            throw new Exception("El host no esta autorizado.");
        }
        if (reservations.isEmpty()) {
            throw new Exception("El host no tiene reservas registradas.");
        }

        return  reservations.stream()
                .map(reserveMapper::toReserveDTO)
                .toList();
    }

    public HostDTO getAuthenticatedHost() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idUser = Long.parseLong(userDetails.getUsername());
        HostProfile host = hostRepository.findByUserId(idUser)
                .orElseThrow(() -> new Exception("No se encontró un host asociado al usuario autenticado."));
        return hostMapper.toHostDTO(host);
    }

    public boolean emailExist(String email){
        return hostRepository.findByUser_Email(email).isPresent() || userRepository.findByEmail(email).isPresent();
    }
}
