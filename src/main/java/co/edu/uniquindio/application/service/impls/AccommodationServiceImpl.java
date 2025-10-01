package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.accommodation.AccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.CreateAccommodationDTO;
import co.edu.uniquindio.application.dto.accommodation.EditAccommodationDTO;
import co.edu.uniquindio.application.dto.review.ReviewDTO;
import co.edu.uniquindio.application.dto.user.EditUserDTO;
import co.edu.uniquindio.application.mappers.AccommodationMapper;
import co.edu.uniquindio.application.mappers.AddressMapper;
import co.edu.uniquindio.application.model.entity.Accommodation;
import co.edu.uniquindio.application.model.entity.Address;
import co.edu.uniquindio.application.model.entity.Review;
import co.edu.uniquindio.application.model.enums.Status;
import co.edu.uniquindio.application.repositories.AccommodationRepository;
import co.edu.uniquindio.application.service.interfaces.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {
    private final Map<Long, Accommodation> placeStore = new ConcurrentHashMap<>();
//    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper placeMapper;
    private final AddressMapper addressMapper;

    @Override
    public void create(CreateAccommodationDTO accommodationDTO) throws Exception{

        Accommodation newPlace = placeMapper.toEntity(accommodationDTO);

        placeStore.put(newPlace.getId(), newPlace);
    }

    @Override
    public AccommodationDTO get(String id) throws Exception{
        Accommodation place = placeStore.get(id);

        if (place == null) {
            throw new Exception("Alojamiento no encontrado.");
        }

        return placeMapper.toAccommodationDTO(place);
    }

    @Override
    public void update(String id, EditAccommodationDTO accommodationDTO) throws Exception{
        Accommodation place = placeStore.get(id);

        if (place == null) {
            throw new Exception("Alojamiento no encontrado.");
        }
        Address address = addressMapper.toEntity(accommodationDTO.address());

        place.setTitle(accommodationDTO.title());
        place.setDescription(accommodationDTO.description());
        place.setAddress(address); // ****Preguntar de addres y location mappers****
        place.setPriceNight(accommodationDTO.priceNight());
        place.setCapMax(accommodationDTO.capMax());
        place.setServices(accommodationDTO.services());
    }

    @Override
    public void delete(String id) throws Exception {
        Accommodation place = placeStore.get(id);

        if (place == null) {
            throw new Exception("Alojamiento no encontrado.");
        }

        place.setStatus(Status.INACTIVE);
    }

    @Override
    public List<AccommodationDTO> listAll(){
        List<AccommodationDTO> list = new ArrayList<>();
        for (Accommodation accommodation : placeStore.values()) {
            list.add(placeMapper.toAccommodationDTO(accommodation));
        }
        return list;
    }

    @Override
    public void addImage(String id, String image) throws Exception{
        Accommodation place = placeStore.get(id);

        if (place == null) {
            throw new Exception("Alojamiento no encontrado.");
        }

        List<String> photos = place.getPhotoUrls();

        if (photos.size() >= 10) {
            throw new Exception("El alojamiento no puede tener mas de 10 fotos.");
        }

        photos.add(image);

        place.setPhotoUrls(photos);

    }

    @Override
    public void deleteImage(String id, String imageId) throws Exception{
        Accommodation place = placeStore.get(id);

        if (place == null) {
            throw new Exception("Alojamiento no encontrado.");
        }

        List<String> photos = place.getPhotoUrls();

        if (!photos.contains(imageId)) {
            throw new Exception("Foto no encontrada.");
        }

        photos.removeIf(photoId -> photoId.equals(imageId));

    }

//    @Override
//    public String getMetrics(String id, LocalDate from, LocalDate to) throws Exception{
//        Accommodation place = placeStore.get(id);
//
//        if (place == null) {
//            throw new Exception("Alojamiento no encontrado.");
//        }
//
//        long totalReservas = place.getReservations().stream()
//                .filter(r -> !r.getDate().isBefore(from) && !r.getDate().isAfter(to))
//                .count();
//
//        List<Review> reviews = place.getReviews().stream()
//                .filter(r -> !r.getDate().isBefore(from) && !r.getDate().isAfter(to))
//                .toList();
//
//        double promedioCalificaciones = reviews.isEmpty()
//                ? 0.0
//                : reviews.stream().mapToDouble(Review::getScore).average().orElse(0.0);
//
//        return ("Número de reservas: " + totalReservas + "Promedio de calificaciones: " + promedioCalificaciones);
//    }
//
//    @Override
//    public List<ReviewDTO> getReviews(String id) throws Exception{
//        Accommodation place = placeStore.get(id);
//
//        if (place == null) {
//            throw new Exception("Alojamiento no encontrado.");
//        }
//
//        // Obtener reseñas
//        List<Review> reviews = place.getReviews();
//
//        // Mapear a DTO
//        return reviews.stream()
//                .map(r -> new ReviewDTO(
//                        r.getId(),
//                        r.getUserId(),
//                        r.getScore(),
//                        r.getComment(),
//                        r.getDate()
//                ))
//                .toList();
//    }

}
