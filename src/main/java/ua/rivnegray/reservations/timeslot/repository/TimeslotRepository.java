package ua.rivnegray.reservations.timeslot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.rivnegray.reservations.timeslot.model.Timeslot;

import java.util.Collection;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    @Query("SELECT t from Timeslot t where (SELECT count(a) from Reservation a "
            + "where a.timeslot.id = t.id and a.user IS NOT NULL) = 0 "
            + "and t.startTime > CURRENT_TIMESTAMP")
    Collection<Timeslot> findAvailableTimeslots();
}
