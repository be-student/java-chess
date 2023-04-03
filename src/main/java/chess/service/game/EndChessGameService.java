package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.domain.game.ChessGame;
import chess.domain.game.command.Command;
import chess.domain.game.command.EndCommand;
import chess.domain.game.state.EndState;
import chess.service.repository.ChessGameRepository;
import java.util.Optional;

public class EndChessGameService {

    private final ChessGameRepository chessGameRepository;

    public EndChessGameService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public void end(int boardId) {
        Optional<ChessGame> chessGame = chessGameRepository.findById(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        Command command = new EndCommand();
        command.execute(chessGame.get());
        chessGameRepository.saveGameState(boardId, EndState.getInstance());
    }
}
