package ua.rivnegray.reservations.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ua.rivnegray.reservations.timeslot.model.TimeslotDto;

@Builder
@Data
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private TimeslotDto timeslot;
    private String comment;
}
