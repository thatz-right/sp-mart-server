package com.spmart.server.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = BoardController.class,
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = BoardController.class)
        }
)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BoardRepository boardRepository;

    @Test
    public void transformedCallControllerHappyPathTest() throws Exception {
        // Create Model to test

        // mocking 하는 방법
//        doNothing().when(this.abstractCallHistoryService).processWavFileAndCallInfo(any(), any());
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

}