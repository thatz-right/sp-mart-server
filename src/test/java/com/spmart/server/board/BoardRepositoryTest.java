package com.spmart.server.board;

import com.spmart.server.common.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(TestConfig.class)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void querydsl_기본_기능_확인() {
        String name = "asdf";
        String address = "asdf@gmail.com";
        boardRepository.save(new Board(name, address));

        //when
        List<Board> result = boardRepository.findByName(name);

        //then
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getAddress(), address);
    }

}