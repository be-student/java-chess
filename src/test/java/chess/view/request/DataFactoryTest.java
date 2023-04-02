package chess.view.request;

import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.game.move.MoveRequest;
import chess.controller.main.ActionType;
import chess.controller.main.EmptyRequest;
import chess.controller.room.join.JoinBoardRequest;
import chess.controller.user.LoginRequest;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class DataFactoryTest {

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(ActionType.MOVE, MoveRequest.class, List.of("move", "a2", "a3")),
                Arguments.of(ActionType.LOGIN, LoginRequest.class, List.of("login", "id")),
                Arguments.of(ActionType.JOIN, JoinBoardRequest.class, List.of("join", "1"))
        );
    }

    @ParameterizedTest
    @CsvSource({"START, start", "END, end", "STATUS, status", "GAMES, games", "CREATE, create"})
    void 빈_데이터가_필요로하는_경우(ActionType actionType, String command) {
        //given
        //when
        var data = DataFactory.map(List.of(command), actionType);

        //then
        assertThat(data.get(EmptyRequest.class)).isExactlyInstanceOf(EmptyRequestImpl.class);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void 빈_데이터가_필요하지_않은_경우(ActionType actionType, Class<?> type, List<String> commands) {
        //given
        //when
        var data = DataFactory.map(commands, actionType);

        //then
        assertThat(data.get(type)).isNotNull();
    }
}
