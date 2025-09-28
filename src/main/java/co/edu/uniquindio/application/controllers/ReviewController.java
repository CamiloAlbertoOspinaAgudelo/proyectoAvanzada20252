package co.edu.uniquindio.application.controllers;


import co.edu.uniquindio.application.dto.review.CreateReviewDTO;
import co.edu.uniquindio.application.dto.exception.ResponseDTO;
import co.edu.uniquindio.application.service.interfaces.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //crear comentario
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> create(@Valid @RequestBody CreateReviewDTO reviewDTO) throws Exception{
        reviewService.create(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "El comentario ha sido creado"));
    }

    //Responder
    @PostMapping("/{id}/response")
    public ResponseEntity<ResponseDTO<String>> response(@PathVariable String id, @Valid @RequestBody CreateReviewDTO reviewDTO) throws Exception{
        reviewService.response(id, reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(false, "La respuesta ha sido creada"));
    }
}
