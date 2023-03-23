package chess.controller.resposne;

import chess.domain.piece.Piece;

public class PieceResponse {

    private final String pieceType;
    private final String pieceColor;

    private PieceResponse(String pieceType, String pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public static PieceResponse from(Piece piece) {
        return new PieceResponse(PieceMapper.getPieceName(piece.getType()),
                ColorMapper.getColorName(piece.getColor()));
    }

    @Override
    public String toString() {
        return "PieceResponse{" +
                "pieceType='" + pieceType + '\'' +
                ", pieceColor='" + pieceColor + '\'' +
                '}';
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getPieceColor() {
        return pieceColor;
    }
}