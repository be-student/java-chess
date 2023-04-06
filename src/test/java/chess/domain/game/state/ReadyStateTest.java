package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.game.exception.ChessGameException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ReadyStateTest {

    private final ReadyState readyState = ReadyState.getInstance();

    @Test
    void getInstance를_사용하면_동일한_객체가_반환된다() {
        //given
        ReadyState resultState = ReadyState.getInstance();

        //when
        boolean result = readyState == resultState;

        //then
        assertTrue(result);
    }

    @Test
    void start_를_사용하면_움직이는_상태가_나온다() {
        //given
        GameState resultState = readyState.start();

        //when
        boolean result = resultState == PlayingState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void end_를_사용하면_종료가_된_상태가_나온다() {
        //given
        GameState resultState = readyState.end();

        //when
        boolean result = resultState == EndState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void run_을_사용하면_아직_시작되지_않닸다는_예외가_발생한다() {
        //expect
        assertThatThrownBy(readyState::run)
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }
}