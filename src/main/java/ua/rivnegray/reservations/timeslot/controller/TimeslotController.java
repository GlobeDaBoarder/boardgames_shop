package ua.rivnegray.reservations.timeslot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.rivnegray.reservations.timeslot.model.Timeslot;
import ua.rivnegray.reservations.timeslot.model.TimeslotDto;
import ua.rivnegray.reservations.timeslot.model.TimeslotRequest;
import ua.rivnegray.reservations.timeslot.service.TimeslotService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/timeslots", produces = "application/json")
public class TimeslotController {
    private final TimeslotService timeslotService;

    @GetMapping
    public Map<String, List<TimeslotDto>> getAllTimeslots() {
        return timeslotService.getAllTimeslots();
    }

    @GetMapping(params = {"available=true", "groupedBy=start_time_date"})
    public Map<String, List<TimeslotDto>> getAvailableTimeslotsGroupedByStartTime() {
        return timeslotService.getGroupedAvailableTimeslots();
    }

    @PostMapping(consumes = "application/json")
    public Collection<Timeslot> createTimeslots(@RequestBody List<TimeslotRequest> timeslots) {
        return timeslotService.createTimeslots(timeslots);
    }

    @GetMapping(path = "/{timeslotId}")
    public TimeslotDto getTimeslotById(@PathVariable Long timeslotId) {
        return timeslotService.getTimeslotById(timeslotId);
    }

    @DeleteMapping(path = "/{timeslotId}")
    public void deleteTimeslotById(@PathVariable Long timeslotId) {
        timeslotService.deleteTimeslotById(timeslotId);
    }

    @PatchMapping(path = "/{timeslotId}", consumes = "application/json")
    public Timeslot patchTimeslotById(@PathVariable Long timeslotId, @RequestBody TimeslotRequest timeslotPatch) {
        return timeslotService.patchTimeslotById(timeslotId, timeslotPatch);
    }
}
