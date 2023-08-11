package ua.rivnegray.reservations.reservation.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.reservations.reservation.mapper.ReservationMapper;
import ua.rivnegray.reservations.reservation.model.Reservation;
import ua.rivnegray.reservations.reservation.model.ReservationDto;
import ua.rivnegray.reservations.reservation.model.ReservationRequest;
import ua.rivnegray.reservations.reservation.repository.ReservationRepository;
import ua.rivnegray.reservations.timeslot.model.Timeslot;
import ua.rivnegray.reservations.timeslot.repository.TimeslotRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private ReservationRepository reservationRepository;
    private TimeslotRepository timeslotRepository;
    private UserProfileRepository userRepository;

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(ReservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationDto> getAvailableReservations() {
        return reservationRepository.findAvailableAppointments().stream()
                .map(ReservationMapper::toDto).collect(Collectors.toList());
    }

    public List<ReservationDto> getUnavailableReservations() {
        return reservationRepository.findUnavailableAppointments().stream()
                .map(ReservationMapper::toDto).collect(Collectors.toList());
    }

    public Optional<Reservation> getReservationById(Long reservationId) {
        return Optional.ofNullable(reservationRepository.findById(reservationId).orElseThrow(
                () -> new HttpServerErrorException(HttpStatus.NOT_FOUND)));
    }

    public Reservation createReservation(ReservationRequest reservationRequest, String userEmail) {
        Reservation reservation;

        Timeslot timeslot = timeslotRepository.findById(reservationRequest.getTimeslotId())
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
        if (userEmail != null) {
            UserProfile user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));

            reservation = new Reservation(user, timeslot, reservationRequest.getComment());
        } else {
            reservation = new Reservation(timeslot, reservationRequest.getComment());
        }

        reservationRepository.save(reservation);

        return reservation;
    }

    public void deleteReservationById(Long reservationId) {
        reservationRepository.delete(reservationRepository.findById(reservationId).orElseThrow(
                () -> new HttpServerErrorException(HttpStatus.NOT_FOUND)));
    }

    public Reservation patchReservationById(Long reservationId, ReservationRequest patchReservation) {
        Reservation updateReservation = reservationRepository.findById(reservationId)
                .map((reservation) -> reservation.toBuilder()
                        .comment(Optional.of(patchReservation.getComment()).orElse(reservation.getComment()))
                        .timeslot((null == patchReservation.getTimeslotId())
                                ? reservation.getTimeslot()
                                : Optional.of(timeslotRepository.findById(patchReservation.getTimeslotId()).get())
                                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND)))
                        .build()
                ).orElseThrow(
                        () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "The Reservation with provided ID not found.")
                );

        reservationRepository.save(updateReservation);

        return updateReservation;
    }

    public Collection<ReservationDto> getUserReservations(String email) {
        return reservationRepository.findUserAppointments(email).stream().map(ReservationMapper::toDto)
                .collect(Collectors.toList());
    }
}
