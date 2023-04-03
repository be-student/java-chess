package chess.controller;

import chess.controller.game.end.EndController;
import chess.controller.game.games.GamesController;
import chess.controller.game.move.MoveController;
import chess.controller.game.start.StartController;
import chess.controller.game.status.StatusController;
import chess.controller.main.ActionType;
import chess.controller.main.ControllerMapper;
import chess.controller.main.MainController;
import chess.controller.room.create.CreateRoomController;
import chess.controller.room.join.JoinBoardController;
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
        Controller startController = new StartController(serviceFactory.getStartChessGameService(),
                serviceFactory.getLoadChessGameService(),
                viewFactory.getBoardOutput());
        Controller endController = new EndController(serviceFactory.getEndChessGameService());
        Controller moveController = new MoveController(serviceFactory.getMoveChessGameService(),
                serviceFactory.getLoadChessGameService(), viewFactory.getBoardOutput());
        Controller statusController = new StatusController(viewFactory.getStatusOutput(),
                serviceFactory.getStatusChessGameService());
        Controller gamesController = new GamesController(serviceFactory.getGamesService(),
                viewFactory.getGamesOutput());
        Controller createRoomController = new CreateRoomController(serviceFactory.getCreateRoomService(),
                viewFactory.getCreateRoomOutput());
        Controller joinBoardController = new JoinBoardController(viewFactory.getJoinBoard(),
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
