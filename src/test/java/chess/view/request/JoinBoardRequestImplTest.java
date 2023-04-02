package chess.view.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class JoinBoardRequestImplTest {

    @Test
    void 보드id_가져오는_테스트() {
        //given
        List<String> input = List.of("join", "1");

        //when
        JoinBoardRequestImpl joinBoardRequest = new JoinBoardRequestImpl(input);

        //then
        assertThat(joinBoardRequest.getBoardId()).isEqualTo(1);
    }

    @Test
    void 커맨드_사이즈가_다르면_예외() {
        //given
        List<String> input = List.of("join", "1", "2");

        //when
        assertThatThrownBy(() -> new JoinBoardRequestImpl(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. join [게임 번호]를 입력해주세요");
    }
}
