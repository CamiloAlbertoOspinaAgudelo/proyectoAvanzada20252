package co.edu.uniquindio.application.dto.accommodation;

import lombok.Builder;

@Builder
public record MetricsDTO(
        Long bookings,
        Double avg,
        Integer totalRatings
) {
    public MetricsDTO(Long bookings, Double avg, Integer totalRatings) {
        this.bookings = bookings;
        this.avg = avg;
        this.totalRatings = totalRatings;
    }
}
