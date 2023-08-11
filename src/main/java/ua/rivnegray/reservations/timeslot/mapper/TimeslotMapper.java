package ua.rivnegray.reservations.timeslot.mapper;

import ua.rivnegray.reservations.timeslot.model.Timeslot;
import ua.rivnegray.reservations.timeslot.model.TimeslotDto;

public class TimeslotMapper {
    public static TimeslotDto toDto(Timeslot timeslot) {
        return TimeslotDto.builder()
                .id(timeslot.getId())
                .startTime(timeslot.getStartTime())
                .endTime(timeslot.getEndTime())
                .build();
    }
}
