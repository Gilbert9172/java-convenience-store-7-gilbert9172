package store.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeConverter {

    private TimeConverter() {
    }

    public static LocalDateTime toStartDate(final String source) {
        LocalDate localDate = LocalDate.parse(source);
        return localDate.atTime(0, 0, 0);
    }

    public static LocalDateTime toEndDate(final String source) {
        LocalDate localDate = LocalDate.parse(source);
        return localDate.atTime(23, 59, 59);
    }
}
