package ua.rivnegray.boardgames_shop.reservations.timeslot.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ua.rivnegray.boardgames_shop.reservations.timeslot.utils.SortByDateString;
import ua.rivnegray.boardgames_shop.reservations.timeslot.repository.TimeslotRepository;
import ua.rivnegray.boardgames_shop.reservations.timeslot.mapper.TimeslotMapper;
import ua.rivnegray.boardgames_shop.reservations.timeslot.model.Timeslot;
import ua.rivnegray.boardgames_shop.reservations.timeslot.model.TimeslotDto;
import ua.rivnegray.boardgames_shop.reservations.timeslot.model.TimeslotRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimeslotService {
    private final TimeslotRepository timeslotRepository;

    public Map<String, List<TimeslotDto>> getAllTimeslots() {
        return groupTimeslotsByStartTimeDate(getTimeslots());
    }

    public Map<String, List<TimeslotDto>> getGroupedAvailableTimeslots() {
        return groupTimeslotsByStartTimeDate(getAvailableTimeslots());
    }

    private List<TimeslotDto> getAvailableTimeslots() {
        return timeslotRepository.findAvailableTimeslots().stream()
                .map(TimeslotMapper::toDto).collect(Collectors.toList());
    }

    private List<TimeslotDto> getTimeslots() {
        return timeslotRepository.findAll().stream().map(TimeslotMapper::toDto)
                .collect(Collectors.toList());
    }

    private Map<String, List<TimeslotDto>> groupTimeslotsByStartTimeDate(List<TimeslotDto> timeslotDtos) {
        Map<String, List<TimeslotDto>> groupedTimeslots = timeslotDtos.stream()
                .collect(Collectors.groupingBy(TimeslotDto::getStartTimeDate));

        Map<String, List<TimeslotDto>> sortedGroupedTimeslots =
                new TreeMap<>(new SortByDateString());
        sortedGroupedTimeslots.putAll(groupedTimeslots);

        return sortedGroupedTimeslots;
    }

    public List<Timeslot> createTimeslots(List<TimeslotRequest> timeslotRequest) {
        List<Timeslot> timeslots =  timeslotRequest.stream().map(timeslot -> Timeslot.builder()
                .startTime(timeslot.getStartTime())
                .endTime(timeslot.getEndTime())
                .build()).collect(
                Collectors.toList());
        return timeslotRepository.saveAll(timeslots);
    }

    public TimeslotDto getTimeslotById(Long timeslotId) {
        return timeslotRepository.findById(timeslotId).map(TimeslotMapper::toDto)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
    }

    public void deleteTimeslotById(Long timeslotId) {
        timeslotRepository.delete(timeslotRepository.findById(timeslotId)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND)));
    }

    public Timeslot patchTimeslotById(Long timeslotId, TimeslotRequest patch) {
        Timeslot updateTimeslot = timeslotRepository.findById(timeslotId)
                .map((timeslot) -> timeslot.toBuilder()
                        .endTime(Optional.ofNullable(patch.getEndTime()).orElse(timeslot.getEndTime()))
                        .startTime(Optional.ofNullable(patch.getStartTime()).orElse(timeslot.getStartTime()))
                        .build()
                ).orElseThrow(
                        () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "The Timeslot with provided ID not found.")
                );

        timeslotRepository.save(updateTimeslot);
        return updateTimeslot;
    }
}
