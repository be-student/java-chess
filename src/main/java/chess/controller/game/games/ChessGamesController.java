package chess.controller.game.games;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.ChessGamesService;
import java.util.List;

public class ChessGamesController implements Controller {

    private final ChessGamesService chessGamesService;
    private final GamesOutput gamesOutput;

    public ChessGamesController(ChessGamesService chessGamesService, GamesOutput gamesOutput) {
        this.chessGamesService = chessGamesService;
        this.gamesOutput = gamesOutput;
    }

    @Override
    public void run(Request request) {
        List<Integer> allUserGameIds = chessGamesService.findAllGamesByUserId(request.getUserId().get());
        gamesOutput.printGames(allUserGameIds);
    }
}
