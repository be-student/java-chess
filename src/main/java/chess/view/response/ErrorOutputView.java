package chess.view.response;

import chess.controller.ErrorOutput;

public class ErrorOutputView implements ErrorOutput {

    @Override
    public void printError(String message) {
        System.out.println(message);
    }
}
