package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.domain.game.ChessGame;
import chess.domain.game.command.Command;
import chess.domain.game.command.StartCommand;
import chess.service.ChessGameRepository;
import java.util.Optional;

public class ChessStartService {

    private final ChessGameRepository chessGameRepository;

    public ChessStartService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public void start(int boardId) {
        Optional<ChessGame> chessGame = chessGameRepository.findById(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        Command command = new StartCommand();
        command.execute(chessGame.get());
        chessGameRepository.saveGameState(boardId, chessGame.get().getGameState());
    }
}
