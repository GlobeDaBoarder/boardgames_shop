package ua.rivnegray.boardgames_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import generated.board_game.api.BoardgamesApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.delegateService.BoardgamesApiDelegateImpl;
import ua.rivnegray.boardgames_shop.repository.UserCredentialsRepository;
import ua.rivnegray.boardgames_shop.service.BoardGameService;
import ua.rivnegray.boardgames_shop.testFactory.BoardGameTestDataFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false, controllers = BoardgamesApiController.class)
//@SpringBootTest()
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith({MockitoExtension.class})
public class BoardGameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BoardGameService boardGameService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private UserCredentialsRepository  userCredentialsRepository;
    @MockBean
    private BoardgamesApiDelegateImpl boardgamesApiDelegate;
    @Autowired
    private ObjectMapper objectMapper;

    private CreateAndUpdateBoardGameDto createBoardGameDto;
    private BoardGameDto resultBoardGameDto;

    @BeforeEach
    void init(){
        this.createBoardGameDto = BoardGameTestDataFactory.createCreateAndUpdateDto();
        this.resultBoardGameDto = BoardGameTestDataFactory.createResultBoardgameDto();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"SCOPE_boardGame:create"})
    void addBoardGame_Returns201AndBoardGameDto() throws Exception {
        given(boardGameService.addBoardGame(any())).willAnswer(invocationOnMock -> resultBoardGameDto);

        ResultActions response = mockMvc.perform(post("/boardgames")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBoardGameDto)));

        response.andExpect(status().isCreated());
    }
}
