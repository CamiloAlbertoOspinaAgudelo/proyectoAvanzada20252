package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.MetricsDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;
import co.edu.uniquindio.application.dto.user.EditUserDTO;
import co.edu.uniquindio.application.dto.user.HostDTO;
import co.edu.uniquindio.application.dto.user.UserDTO;
import co.edu.uniquindio.application.mappers.*;
import co.edu.uniquindio.application.model.entity.*;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.repositories.*;
import co.edu.uniquindio.application.service.interfaces.AccommodationService;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper placeMapper;
    private final AddressMapper addressMapper;
    private final HostMapper hostMapper;
    private final HostRepository hostRepository;
    private final ReserveRepository reserveRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public void create(CreateAccommodationDTO accommodationDTO) throws Exception{

        HostDTO authenticatedHost = getAuthenticatedHost();

        Accommodation newPlace = placeMapper.toEntity(accommodationDTO);

        accommodationRepository.save(newPlace);
    }

    @Override
    public AccommodationDTO get(Long id) throws Exception{
        Optional<Accommodation> place = accommodationRepository.findById(id);

        if (place.isEmpty()) {
            throw new Exception("Alojamiento no encontrado.");
        }

        return placeMapper.toAccommodationDTO(place.get());
    }

    @Override
    public void update(Long id, EditAccommodationDTO accommodationDTO) throws Exception{
        Optional<Accommodation> placeOption = accommodationRepository.findById(id);

        if (placeOption.isEmpty()) {
            throw new Exception("Alojamiento no encontrado.");
        }
        Address address = addressMapper.toEntity(accommodationDTO.address());
        Accommodation place = placeOption.get();

        place.setTitle(accommodationDTO.title());
        place.setDescription(accommodationDTO.description());
        place.setAddress(address); // ****Preguntar de addres y location mappers****
        place.setPriceNight(accommodationDTO.priceNight());
        place.setCapMax(accommodationDTO.capMax());
        place.setServices(accommodationDTO.services());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Accommodation> placeOpt = accommodationRepository.findById(id);

        if (placeOpt.isEmpty()) {
            throw new Exception("Alojamiento no encontrado.");
        }

        Accommodation place = placeOpt.get();

        place.setStatus(Status.INACTIVE);
    }

    @Override
    public List<AccommodationDTO> listAll(){
        return accommodationRepository.findAll().stream().map(placeMapper::toAccommodationDTO).toList();
    }

    @Override
    public MetricsDTO getMetrics(Long id, LocalDateTime from, LocalDateTime to) throws Exception{
        return reserveRepository.getMetrics(id, from, to);
    }

    @Override
    public List<ReviewDTO> getReviews(Long id) throws Exception{
        List<Review> reviews = reviewRepository.findByAccommodation_ID(id);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(reviewMapper.toReviewDTO(review));
        }
        return reviewDTOs;
    }

    public HostDTO getAuthenticatedHost() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idUser = Long.parseLong(userDetails.getUsername());
        HostProfile host = hostRepository.findByUserId(idUser)
                .orElseThrow(() -> new Exception("No se encontró un host asociado al usuario autenticado."));
        return hostMapper.toHostDTO(host);
    }

}
