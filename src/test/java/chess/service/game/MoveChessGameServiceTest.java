package chess.service.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.exception.ChessGameException;
import chess.repository.InMemoryChessGameRepository;
import org.junit.jupiter.api.Test;

class MoveChessGameServiceTest {

    private final InMemoryChessGameRepository inMemoryChessGameRepository = new InMemoryChessGameRepository();
    private final MoveChessGameService moveChessGameService = new MoveChessGameService(inMemoryChessGameRepository);

    @Test
    void 다음_칸으로_이동한다() {
        // given
        int boardId = inMemoryChessGameRepository.create(1);
        inMemoryChessGameRepository.findById(boardId).get().start();

        // when
        moveChessGameService.move(boardId, "a2", "a3");

        // then
        assertThatThrownBy(() -> moveChessGameService.move(boardId, "a2", "a3"))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("이동할 말이 없습니다.");
    }
}
