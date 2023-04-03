package chess.service.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.state.StatusType;
import chess.repository.InMemoryChessGameRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class StartChessGameServiceTest {

    private final InMemoryChessGameRepository inMemoryChessGameRepository = new InMemoryChessGameRepository();
    private final StartChessGameService startChessGameService = new StartChessGameService(inMemoryChessGameRepository);

    @Test
    void 새_게임을_시작한다() {
        // given
        int boardId = inMemoryChessGameRepository.create(1);

        // when
        startChessGameService.start(boardId);

        // then
        assertThat(inMemoryChessGameRepository.findById(boardId).get().getGameState().getStatusType())
                .isEqualTo(StatusType.PLAYING);
    }
}
