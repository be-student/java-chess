package chess.view.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class MoveRequestImplTest {

    @Test
    void 커맨드로부터_움직임_가져오는_테스트() {
        //given
        List<String> commands = List.of("move", "a2", "a3");

        //when
        MoveRequestImpl moveRequest = new MoveRequestImpl(commands);

        //then
        assertAll(
                () -> assertThat(moveRequest.getOrigin()).isEqualTo("a2"),
                () -> assertThat(moveRequest.getDestination()).isEqualTo("a3")
        );
    }
}
