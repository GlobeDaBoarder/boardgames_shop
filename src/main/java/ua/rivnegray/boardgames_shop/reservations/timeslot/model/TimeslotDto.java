package ua.rivnegray.boardgames_shop.reservations.timeslot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
public class TimeslotDto {
    private Long id;
    private Timestamp startTime;
    private Timestamp endTime;

    public String getStartTimeDate() {
        LocalDate date = startTime.toLocalDateTime().toLocalDate();
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
