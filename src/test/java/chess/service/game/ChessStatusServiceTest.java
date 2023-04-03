package chess.service.game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.controller.game.status.StatusResponse;
import chess.repository.InMemoryChessGameRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ChessStatusServiceTest {

    private final InMemoryChessGameRepository inMemoryChessGameRepository = new InMemoryChessGameRepository();
    private final ChessStatusService chessStatusService = new ChessStatusService(
            inMemoryChessGameRepository);

    @Test
    void 상태를_가져온다() {
        // given
        int boardId = inMemoryChessGameRepository.create(1);
        inMemoryChessGameRepository.findById(boardId).get().start();

        // when
        StatusResponse result = chessStatusService.status(boardId);

        // then
        assertAll(
                () -> assertEquals(38.0, result.getWhiteScore()),
                () -> assertEquals(38.0, result.getBlackScore())
        );
    }
}
