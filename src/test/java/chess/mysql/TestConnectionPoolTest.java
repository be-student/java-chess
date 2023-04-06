package chess.mysql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class TestConnectionPoolTest {

    @Test
    void connection을_생성하면_null이_아닌_결과가_반환된다() {
        var connection = new TestConnectionPool().getConnection();
        assertNotNull(connection);
    }
}
