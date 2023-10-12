package ua.rivnegray.boardgames_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameMechanicIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMechanicMapper;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.service.BoardGameMechanicService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardGameMechanicServiceImpl implements BoardGameMechanicService {
    private final BoardGameMechanicRepository boardGameMechanicRepository;
    private final BoardGameMechanicMapper boardGameMechanicMapper;

    @Override
    public List<BoardGameMechanicDto> getAllMechanics() {
        return this.boardGameMechanicRepository.findAll().stream()
                .map(this.boardGameMechanicMapper::boardGameMechanicToBoardGameMechanicDto)
                .collect(Collectors.toList());
    }

    @Override
    public BoardGameMechanicDto getMechanicById(Long id) {
        return this.boardGameMechanicMapper.boardGameMechanicToBoardGameMechanicDto(this.boardGameMechanicRepository
                .findById(id).orElseThrow(() -> new BoardGameMechanicIdNotFoundException(id)));
    }

    @Override
    public BoardGameMechanicDto addMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        BoardGameMechanic boardGameMechanic = this.boardGameMechanicMapper
                .createBoardGameMechanicDtoToBoardGameMechanic(createAndUpdateBoardGameMechanicDto);
        this.boardGameMechanicRepository.save(boardGameMechanic);
        return this.boardGameMechanicMapper.boardGameMechanicToBoardGameMechanicDto(boardGameMechanic);
    }

    @Override
    public BoardGameMechanicDto updateMechanic(Long id, CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        BoardGameMechanic boardGameMechanic = this.boardGameMechanicRepository.findById(id)
                .orElseThrow(() -> new BoardGameMechanicIdNotFoundException(id));
        this.boardGameMechanicMapper.updateBoardGameMechanicFromDto(createAndUpdateBoardGameMechanicDto,
                boardGameMechanic);
        this.boardGameMechanicRepository.save(boardGameMechanic);
        return this.boardGameMechanicMapper.boardGameMechanicToBoardGameMechanicDto(boardGameMechanic);
    }

    @Override
    public void deleteMechanic(Long id) {
        this.boardGameMechanicRepository.deleteById(id);
    }
}
