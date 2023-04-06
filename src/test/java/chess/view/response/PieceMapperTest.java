package chess.view.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class PieceMapperTest {

    @ParameterizedTest
    @CsvSource(value = {"PAWN, p", "ROOK, r", "KNIGHT, n", "BISHOP, b", "QUEEN, q", "KING, k"})
    void 흰색_이름_가져오는_테스트(PieceType pieceType, String expected) {
        //given
        Piece piece = PieceFactory.getInstance(pieceType, Color.WHITE);
        //when
        String result = PieceMapper.getPieceName(piece);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"PAWN, P", "ROOK, R", "KNIGHT, N", "BISHOP, B", "QUEEN, Q", "KING, K"})
    void 검은색_이름_가져오는_테스트(PieceType pieceType, String expected) {
        //given
        Piece piece = PieceFactory.getInstance(pieceType, Color.BLACK);
        //when
        String result = PieceMapper.getPieceName(piece);

        //then
        assertEquals(result, expected);
    }

    @Test
    void 빈칸_이름_가져오는_테스트() {
        //given
        Piece piece = PieceFactory.getInstance(PieceType.EMPTY, Color.NONE);
        //when
        String result = PieceMapper.getPieceName(piece);

        //then
        assertEquals(result, ".");
    }
}
