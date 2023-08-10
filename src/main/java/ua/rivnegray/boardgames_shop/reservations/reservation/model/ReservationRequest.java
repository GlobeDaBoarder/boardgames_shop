package ua.rivnegray.boardgames_shop.reservations.reservation.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReservationRequest {
    private Long timeslotId;
    private String comment;
}
