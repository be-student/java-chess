package chess.service.room;

import chess.service.ChessGameRepository;

public class ChessCreateRoomService {

    private final ChessGameRepository chessGameRepository;

    public ChessCreateRoomService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public int createRoom(int userId) {
        return chessGameRepository.create(userId);
    }
}
