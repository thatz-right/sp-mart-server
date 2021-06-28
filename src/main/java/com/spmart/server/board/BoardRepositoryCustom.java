package com.spmart.server.board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findByName(String name);
}
