package store.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TimeConverterTest {

    @Test
    @DisplayName("문자열을 시작일로 변경")
    void toStartDate() {
        // given
        String source = "2024-11-06";
        // when
        LocalDateTime expected = TimeConverter.toStartDate(source);
        // then
        assertEquals(expected.toString(), "2024-11-06T00:00");
    }

    @Test
    @DisplayName("문자열을 종료일로 변경")
    void toEndDate() {
        // given
        String source = "2024-11-06";
        // when
        LocalDateTime expected = TimeConverter.toEndDate(source);
        // then
        assertEquals(expected.toString(), "2024-11-06T23:59:59");
    }

}
