package ua.rivnegray.reservations.reservation.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReservationRequest {
    private Long timeslotId;
    private String comment;
}
