package ua.rivnegray.reservations.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.rivnegray.reservations.reservation.model.Reservation;

import java.util.Collection;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT a FROM Reservation a WHERE a.user IS NULL ORDER BY a.timeslot.startTime")
    Collection<Reservation> findAvailableAppointments();

    @Query("SELECT a FROM Reservation a WHERE a.user IS NOT NULL ORDER BY a.timeslot.startTime")
    Collection<Reservation> findUnavailableAppointments();

    @Query("SELECT a FROM Reservation a WHERE a.user.email = ?1")
    Collection<Reservation> findUserAppointments(String email);
}
