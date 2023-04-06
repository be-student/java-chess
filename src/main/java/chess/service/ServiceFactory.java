package chess.service;

import chess.mysql.ConnectionPoolImpl;
import chess.mysql.JdbcTemplate;
import chess.repository.chess.ChessGameDao;
import chess.repository.chess.ChessGameRepositoryImpl;
import chess.repository.chess.MoveDao;
import chess.repository.user.UserDao;
import chess.repository.user.UserRepositoryImpl;
import chess.service.game.ChessEndService;
import chess.service.game.ChessGamesService;
import chess.service.game.ChessLoadService;
import chess.service.game.ChessMoveService;
import chess.service.game.ChessStartService;
import chess.service.game.ChessStatusService;
import chess.service.room.ChessCreateRoomService;
import chess.service.user.LoginService;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final JdbcTemplate jdbcTemplate;
    private final ChessStartService chessStartService;
    private final ChessEndService chessEndService;
    private final ChessMoveService chessMoveService;
    private final ChessLoadService chessLoadService;
    private final ChessStatusService chessStatusService;
    private final ChessCreateRoomService chessCreateRoomService;
    private final LoginService loginService;
    private final ChessGamesService chessGamesService;

    private ServiceFactory() {
        jdbcTemplate = new JdbcTemplate(ConnectionPoolImpl.getInstance());
        ChessGameRepository chessGameRepository = new ChessGameRepositoryImpl(createChessGameDao(), createMoveDao());
        chessLoadService = new ChessLoadService(chessGameRepository);
        chessStartService = new ChessStartService(chessGameRepository);
        chessEndService = new ChessEndService(chessGameRepository);
        chessMoveService = new ChessMoveService(chessGameRepository);
        chessStatusService = new ChessStatusService(chessGameRepository);
        chessGamesService = new ChessGamesService(chessGameRepository);
        chessCreateRoomService = new ChessCreateRoomService(chessGameRepository);
        loginService = new LoginService(createUserRepository());
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ChessStatusService getStatusChessGameService() {
        return chessStatusService;
    }

    public ChessCreateRoomService getCreateRoomService() {
        return chessCreateRoomService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public ChessGamesService getGamesService() {
        return chessGamesService;
    }

    private ChessGameDao createChessGameDao() {
        return new ChessGameDao(jdbcTemplate);
    }

    private UserRepositoryImpl createUserRepository() {
        return new UserRepositoryImpl(new UserDao(jdbcTemplate));
    }

    private MoveDao createMoveDao() {
        return new MoveDao(jdbcTemplate);
    }

    public ChessStartService getStartChessGameService() {
        return chessStartService;
    }

    public ChessEndService getEndChessGameService() {
        return chessEndService;
    }

    public ChessMoveService getMoveChessGameService() {
        return chessMoveService;
    }

    public ChessLoadService getLoadChessGameService() {
        return chessLoadService;
    }
}
