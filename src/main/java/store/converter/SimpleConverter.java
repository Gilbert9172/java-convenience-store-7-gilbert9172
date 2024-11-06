package store.converter;

import java.util.Arrays;
import java.util.List;

public class SimpleConverter {

    private SimpleConverter() {
    }

    public static List<String> stringToStringList(String source) {
        return Arrays.stream(source.split(","))
                .map(String::strip)
                .toList();
    }

}
