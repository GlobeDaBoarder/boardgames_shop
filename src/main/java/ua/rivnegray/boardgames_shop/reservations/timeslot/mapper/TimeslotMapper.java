package ua.rivnegray.boardgames_shop.reservations.timeslot.mapper;

import ua.rivnegray.boardgames_shop.reservations.timeslot.model.Timeslot;
import ua.rivnegray.boardgames_shop.reservations.timeslot.model.TimeslotDto;

public class TimeslotMapper {
    public static TimeslotDto toDto(Timeslot timeslot) {
        return TimeslotDto.builder()
                .id(timeslot.getId())
                .startTime(timeslot.getStartTime())
                .endTime(timeslot.getEndTime())
                .build();
    }
}