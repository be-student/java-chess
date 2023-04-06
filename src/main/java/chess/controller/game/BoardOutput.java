package chess.controller.game;

import chess.view.response.PieceResponse;
import java.util.List;

public interface BoardOutput {

    void printBoard(List<List<PieceResponse>> boardResponse);
}
