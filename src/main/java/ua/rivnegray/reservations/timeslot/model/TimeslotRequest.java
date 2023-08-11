package ua.rivnegray.reservations.timeslot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class TimeslotRequest {
    private Timestamp startTime;
    private Timestamp endTime;
}
