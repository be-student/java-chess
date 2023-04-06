package chess.controller;

import chess.controller.game.end.ChessEndController;
import chess.controller.game.games.ChessGamesController;
import chess.controller.game.move.ChessMoveController;
import chess.controller.game.start.ChessStartController;
import chess.controller.game.status.ChessStatusController;
import chess.controller.main.ActionType;
import chess.controller.main.ControllerMapper;
import chess.controller.main.MainController;
import chess.controller.room.create.ChessCreateRoomController;
import chess.controller.room.join.ChessJoinBoardController;
import chess.controller.user.LoginController;
import chess.service.ServiceFactory;
import chess.view.ViewFactory;
import java.util.Map;

public class ControllerFactory {

    private static final ControllerFactory instance = new ControllerFactory();
    private final MainController mainController;
    private final Map<ActionType, Controller> controllers;

    private ControllerFactory() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ViewFactory viewFactory = ViewFactory.getInstance();
        controllers = initializeControllers(serviceFactory, viewFactory);
        mainController = new MainController(createControllerMapper(), viewFactory.getErrorOutput(),
                viewFactory.getInputView(), viewFactory.getInitialOutput());
    }

    public static ControllerFactory getInstance() {
        return instance;
    }

    private Map<ActionType, Controller> initializeControllers(ServiceFactory serviceFactory, ViewFactory viewFactory) {
        Controller startController = new ChessStartController(serviceFactory.getStartChessGameService(),
                serviceFactory.getLoadChessGameService(),
                viewFactory.getBoardOutput());
        Controller endController = new ChessEndController(serviceFactory.getEndChessGameService());
        Controller moveController = new ChessMoveController(serviceFactory.getMoveChessGameService(),
                serviceFactory.getLoadChessGameService(), viewFactory.getBoardOutput());
        Controller statusController = new ChessStatusController(viewFactory.getStatusOutput(),
                serviceFactory.getStatusChessGameService());
        Controller gamesController = new ChessGamesController(serviceFactory.getGamesService(),
                viewFactory.getGamesOutput());
        Controller createRoomController = new ChessCreateRoomController(serviceFactory.getCreateRoomService(),
                viewFactory.getCreateRoomOutput());
        Controller joinBoardController = new ChessJoinBoardController(viewFactory.getJoinBoard(),
                viewFactory.getJoinBoardOutput(),
                serviceFactory.getLoadChessGameService(), viewFactory.getBoardOutput());
        Controller loginController = new LoginController(viewFactory.getLogin(), viewFactory.getLoginOutput(),
                serviceFactory.getLoginService());
        return Map.of(
                ActionType.START, startController,
                ActionType.END, endController,
                ActionType.MOVE, moveController,
                ActionType.STATUS, statusController,
                ActionType.GAMES, gamesController,
                ActionType.CREATE, createRoomController,
                ActionType.JOIN, joinBoardController,
                ActionType.LOGIN, loginController
        );
    }

    private ControllerMapper createControllerMapper() {
        return new ControllerMapper(controllers);
    }

    public MainController getMainController() {
        return mainController;
    }
}
