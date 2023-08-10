package ua.rivnegray.boardgames_shop.reservations.reservation.mapper;

import ua.rivnegray.boardgames_shop.reservations.reservation.model.Reservation;
import ua.rivnegray.boardgames_shop.reservations.reservation.model.ReservationDto;
import ua.rivnegray.boardgames_shop.reservations.timeslot.mapper.TimeslotMapper;

public class ReservationMapper {
    public static ReservationDto toDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .timeslot(TimeslotMapper.toDto(reservation.getTimeslot()))
                .comment(reservation.getComment())
                .build();
    }
}