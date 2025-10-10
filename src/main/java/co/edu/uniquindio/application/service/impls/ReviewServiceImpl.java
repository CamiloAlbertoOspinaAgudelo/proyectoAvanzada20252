package co.edu.uniquindio.application.service.impls;

import co.edu.uniquindio.application.dto.review.CreateReviewDTO;
import co.edu.uniquindio.application.dto.review.EditReviewDTO;
import co.edu.uniquindio.application.exceptions.ForbiddenException;
import co.edu.uniquindio.application.exceptions.NotFoundException;
import co.edu.uniquindio.application.exceptions.UnauthorizedException;
import co.edu.uniquindio.application.exceptions.ValueConflictException;
import co.edu.uniquindio.application.mappers.ReviewMapper;
import co.edu.uniquindio.application.model.entity.*;
import co.edu.uniquindio.application.model.enums.ReserveStatus;
import co.edu.uniquindio.application.repositories.AccommodationRepository;
import co.edu.uniquindio.application.repositories.HostRepository;
import co.edu.uniquindio.application.repositories.ReserveRepository;
import co.edu.uniquindio.application.repositories.ReviewRepository;
import co.edu.uniquindio.application.service.interfaces.ReviewService;
import co.edu.uniquindio.application.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AccommodationRepository accommodationRepository;
    private final ReviewMapper reviewMapper;
    private final UserService userService;
    private final ReserveRepository reserveRepository;
    private final HostRepository hostRepository;

    @Override
    public void create(CreateReviewDTO reviewDTO) throws Exception{
        User user = userService.getAuthenticatedUser();
        Reservation reservation = reserveRepository.findReviewable(user.getId(), reviewDTO.placeId()).orElseThrow(() -> new ForbiddenException("Usted no ha completado una reserva en el alojamiento"));
        Optional<Accommodation> place = accommodationRepository.findById(reviewDTO.placeId());

        if (reservation.getStatus() != ReserveStatus.COMPLETED){
            throw new ForbiddenException("Usted no ha completado una reserva en el alojamiento");
        }

        if (place.isEmpty()){
            throw new NotFoundException("El alojamiento no existe");
        }

        Review review = new Review();
        review.setAccommodation(place.get());
        review.setRating(reviewDTO.rating());
        review.setComment(reviewDTO.comment());
        reviewRepository.save(review);

        //Cada que se crea una reseña nueva, se actualiza las métricas del alojamiento
        Accommodation accommodation = review.getAccommodation();

        accommodation.setTotalRatings(accommodation.getTotalRatings()+1 );
        accommodation.setAvgRating( (accommodation.getAvgRating()+review.getRating())/2.0 );

        accommodationRepository.save(accommodation);

    }

    @Override
    public void response(Long id, EditReviewDTO reviewDTO) throws Exception{
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (reviewOptional.isEmpty()){
            throw new NotFoundException("La reseña no existe.");
        }
        User user = userService.getAuthenticatedUser();
        HostProfile host = hostRepository.findByUserId(user.getId()).orElseThrow(() -> new Exception("Usted no es un host"));

        Review review = reviewOptional.get();

        if (review.getAccommodation().getHost().getId().equals(host.getId())){
            throw new UnauthorizedException("Usted no es el host");
        }

        review.setResponse(reviewDTO.response());
    }
}
