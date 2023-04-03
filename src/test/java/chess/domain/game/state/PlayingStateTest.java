package chess.domain.game.state;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class PlayingStateTest {

    private final PlayingState playingState = PlayingState.getInstance();

    @Test
    void getInstance_를_통해_가져오면_동일한_객체가_반환된다() {
        //given
        PlayingState resultState = PlayingState.getInstance();

        //when
        boolean result = playingState == resultState;

        //then
        assertTrue(result);
    }

    @Test
    void start_를_실행하면_처음으로_돌아간다() {
        //given
        GameState resultState = playingState.start();

        //when
        boolean result = resultState == ReadyState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void end_를_실행하면_종료된_상태가_반환된다() {
        //given
        GameState resultState = playingState.end();

        //when
        boolean result = resultState == EndState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void run을_실행하면_현재_상태가_반환된다() {
        //given
        GameState resultState = playingState.run();

        //when
        boolean result = resultState == playingState;

        //then
        assertTrue(result);
    }
}
