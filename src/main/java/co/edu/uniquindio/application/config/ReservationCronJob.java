package co.edu.uniquindio.application.config;

import co.edu.uniquindio.application.service.impls.ReserveServiceImpl;
import co.edu.uniquindio.application.service.interfaces.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationCronJob {
    private final ReserveService reservationService;
    private final ReserveServiceImpl reserveServiceImpl;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateExpiredReservations() throws Exception {
        System.out.println("⏰ Ejecutando cron job: actualizar reservas vencidas...");
        reserveServiceImpl.sendReminder();
    }
}
