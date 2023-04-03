package chess.service.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.game.ChessGame;
import chess.domain.game.state.StatusType;
import chess.repository.InMemoryChessGameRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ChessEndServiceTest {

    private final InMemoryChessGameRepository inMemoryChessGameRepository = new InMemoryChessGameRepository();
    private final ChessEndService chessEndService = new ChessEndService(inMemoryChessGameRepository);

    @Test
    void 작동시키면_끝난다() {
        // given
        int boardId = inMemoryChessGameRepository.create(1);

        // when
        chessEndService.end(1);

        // then
        Optional<ChessGame> chessGame = inMemoryChessGameRepository.findById(boardId);
        assertAll(
                () -> assertThat(chessGame).isPresent(),
                () -> assertThat(chessGame.get().getGameState().getStatusType()).isEqualTo(StatusType.END)
        );
    }
}
