package chess.controller.main;

import chess.controller.Controller;
import chess.controller.ErrorOutput;
import chess.controller.Filter;
import chess.view.InputView;
import chess.view.response.ErrorMessageConverter;
import java.util.List;

public class MainController {

    private final Filter filter;
    private final CommandMapper commandMapper;
    private final InputView inputView;
    private final ErrorOutput errorOutput;
    private final InitialOutput initialOutput;

    public MainController(CommandMapper commandMapper, ErrorOutput errorOutput, InputView inputView,
            InitialOutput initialOutput) {
        filter = new Filter(
                List.of(ActionType.START, ActionType.END, ActionType.MOVE, ActionType.STATUS,
                        ActionType.GAMES, ActionType.CREATE, ActionType.JOIN),
                List.of(ActionType.START, ActionType.END, ActionType.MOVE, ActionType.STATUS)
        );
        this.commandMapper = commandMapper;
        this.inputView = inputView;
        this.errorOutput = errorOutput;
        this.initialOutput = initialOutput;
    }

    public void run() {
        initialOutput.printInitialMessage();
        while (true) {
            try {
                Request request = inputView.inputGameCommand();
                filter.validateRequest(request);
                Controller controller = commandMapper.getController(request.getActionType());
                controller.run(request);
            } catch (Exception e) {
                errorOutput.printError(ErrorMessageConverter.convert(e.getMessage()));
            }
        }
    }
}
