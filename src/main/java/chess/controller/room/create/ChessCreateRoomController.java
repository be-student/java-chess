package chess.controller.room.create;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.room.ChessCreateRoomService;

public class ChessCreateRoomController implements Controller {

    private final ChessCreateRoomService chessCreateRoomService;
    private final CreateRoomOutput createRoomOutput;

    public ChessCreateRoomController(ChessCreateRoomService chessCreateRoomService, CreateRoomOutput createRoomOutput) {
        this.chessCreateRoomService = chessCreateRoomService;
        this.createRoomOutput = createRoomOutput;
    }

    @Override
    public void run(Request request) {
        int roomId = chessCreateRoomService.createRoom(request.getUserId().get());
        createRoomOutput.printCreateRoomSuccess(roomId);
    }
}
