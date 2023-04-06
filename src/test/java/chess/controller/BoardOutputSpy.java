package chess.controller;

import chess.controller.game.BoardOutput;
import chess.view.response.PieceResponse;
import java.util.List;

public class BoardOutputSpy implements BoardOutput {

    private int count;

    @Override
    public void printBoard(List<List<PieceResponse>> boardResponse) {
        count++;
    }
}
