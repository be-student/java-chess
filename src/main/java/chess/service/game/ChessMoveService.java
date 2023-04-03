package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.domain.game.ChessGame;
import chess.domain.game.command.Command;
import chess.domain.game.command.MoveCommand;
import chess.service.ChessGameRepository;
import java.util.Optional;

public class ChessMoveService {

    private final ChessGameRepository chessGameRepository;

    public ChessMoveService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public void move(int boardId, String source, String target) {
        Optional<ChessGame> chessGame = chessGameRepository.findById(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        Command command = MoveCommand.of(source, target);
        command.execute(chessGame.get());
        chessGameRepository.saveMoves(boardId, source, target, chessGame.get().getTurn().getValue());
    }
}
