package chess.controller.room.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.controller.main.Request;
import chess.repository.InMemoryChessGameRepository;
import chess.service.room.ChessCreateRoomService;
import chess.view.request.RequestImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ChessCreateRoomControllerTest {

    private final InMemoryChessGameRepository inMemoryChessGameRepository = new InMemoryChessGameRepository();
    private final ChessCreateRoomService chessCreateRoomService = new ChessCreateRoomService(
            inMemoryChessGameRepository);

    private final CreateRoomOutputSpy createRoomOutputSpy = new CreateRoomOutputSpy();

    @Test
    void 방_생성_성공() {
        //given
        ChessCreateRoomController chessCreateRoomController
                = new ChessCreateRoomController(chessCreateRoomService, createRoomOutputSpy);
        // when
        chessCreateRoomController.run(createRequest(List.of("create")));
        // then
        assertAll(
                () -> assertThat(createRoomOutputSpy.getCount()).isOne(),
                () -> assertThat(createRoomOutputSpy.getRoomId()).isPositive()
        );
    }

    private Request createRequest(List<String> commands) {
        return RequestImpl.of(commands, Optional.of(1), Optional.empty());
    }
}

class CreateRoomOutputSpy implements CreateRoomOutput {

    private int roomId;
    private int count;

    @Override
    public void printCreateRoomSuccess(int roomId) {
        this.roomId = roomId;
        count++;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCount() {
        return count;
    }
}
