package chess.controller.room.join;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.controller.BoardOutputSpy;
import chess.controller.main.Request;
import chess.domain.game.state.StatusType;
import chess.repository.InMemoryChessGameRepository;
import chess.service.game.ChessLoadService;
import chess.view.request.RequestImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ChessJoinBoardControllerTest {

    private final InMemoryChessGameRepository chessGameRepository = new InMemoryChessGameRepository();
    private final JoinBoardOutputSpy joinBoardOutputSpy = new JoinBoardOutputSpy();
    private final JoinBoardSpy joinBoardSpy = new JoinBoardSpy();
    private final ChessJoinBoardController chessJoinBoardController = new ChessJoinBoardController(joinBoardSpy,
            joinBoardOutputSpy, new ChessLoadService(chessGameRepository), new BoardOutputSpy());

    @Test
    void 게임_참여_성공() {
        // given
        int boardId = chessGameRepository.create(1);
        // when
        chessJoinBoardController.run(createRequest(List.of("join", String.valueOf(boardId))));
        // then
        assertAll(
                () -> assertThat(joinBoardSpy.getBoardId()).isEqualTo(boardId),
                () -> assertThat(joinBoardOutputSpy.getCount()).isOne(),
                () -> assertThat(joinBoardOutputSpy.getStatusType()).isEqualTo(StatusType.READY)
        );
    }

    private Request createRequest(List<String> commands) {
        return RequestImpl.of(commands, Optional.empty(), Optional.empty());
    }
}

class JoinBoardSpy implements JoinBoard {

    private int boardId;

    @Override
    public void join(int boardId) {
        this.boardId = boardId;
    }

    public int getBoardId() {
        return boardId;
    }
}

class JoinBoardOutputSpy implements JoinBoardOutput {

    private int count;
    private StatusType statusType;

    @Override
    public void printJoinBoardSuccess(StatusType statusType) {
        this.statusType = statusType;
        count++;
    }

    public int getCount() {
        return count;
    }

    public StatusType getStatusType() {
        return statusType;
    }
}
