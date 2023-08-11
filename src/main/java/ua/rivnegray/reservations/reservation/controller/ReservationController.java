package ua.rivnegray.reservations.reservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.rivnegray.reservations.reservation.model.Reservation;
import ua.rivnegray.reservations.reservation.model.ReservationDto;
import ua.rivnegray.reservations.reservation.model.ReservationRequest;
import ua.rivnegray.reservations.reservation.service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> getAvailableReservations(@RequestParam Optional<Boolean> available) {
        return available.map((x) -> x
                        ? reservationService.getAvailableReservations()
                        : reservationService.getUnavailableReservations())
                .orElse(reservationService.getAllReservations());
    }

    @GetMapping(path = "/{reservationId}")
    public Optional<Reservation> getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Reservation createReservation(@RequestBody ReservationRequest reservationRequest,
                                         Authentication authentication) {
        String userEmail = authentication.getName();
        return reservationService.createReservation(reservationRequest, userEmail);
    }

    @DeleteMapping(path = "/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservationById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
    }

    @PatchMapping(path = "/{reservationId}", consumes = "application/json")
    public Reservation patchReservation(@PathVariable Long reservationId,
                                        @RequestBody ReservationRequest patchReservation) {
        return reservationService.patchReservationById(reservationId, patchReservation);
    }

    @GetMapping(path = "/user")
    public Collection<ReservationDto> getUserReservations(Authentication authentication) {
        return reservationService.getUserReservations(authentication.getName());
    }
}
