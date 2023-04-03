package chess.controller.game.games;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.GamesService;
import java.util.List;

public class GamesController implements Controller {

    private final GamesService gamesService;
    private final GamesOutput gamesOutput;

    public GamesController(GamesService gamesService, GamesOutput gamesOutput) {
        this.gamesService = gamesService;
        this.gamesOutput = gamesOutput;
    }

    @Override
    public void run(Request request) {
        List<Integer> allUserGameIds = gamesService.findAllGamesByUserId(request.getUserId().get());
        gamesOutput.printGames(allUserGameIds);
    }
}
