package chess.domain.game;

import chess.domain.game.constant.ChessPosition;
import chess.domain.game.exception.ChessGameException;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private static final Piece EMPTY_PIECE = Piece.empty();

    private final Map<Position, Piece> piecePosition = ChessPosition.initialPiecePositions();

    public void movePiece(Position origin, Position destination) {
        validateMoveRequest(origin, destination);
        Piece targetPiece = piecePosition.get(origin);
        targetPiece.move(origin.getFileDifference(destination),
                origin.getRankDifference(destination),
                piecePosition.get(destination));
        piecePosition.put(destination, targetPiece);
        piecePosition.put(origin, Piece.empty());
    }

    private void validateMoveRequest(Position origin, Position destination) {
        if (piecePosition.get(origin) == EMPTY_PIECE) {
            throw new ChessGameException("이동할 말이 없습니다.");
        }
        checkPath(origin, destination);
    }

    private void checkPath(Position origin, Position destination) {
        List<Position> straightPath = origin.createStraightPath(destination);
        for (Position position : straightPath) {
            if (piecePosition.get(position) != EMPTY_PIECE) {
                throw new ChessGameException("경로에 말이 있습니다.");
            }
        }
    }

    public List<List<Piece>> getPieces() {
        List<List<Piece>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<Piece> pieceResponses = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(piecePosition::get)
                    .collect(Collectors.toList());
            response.add(pieceResponses);
        }
        return response;
    }
}
