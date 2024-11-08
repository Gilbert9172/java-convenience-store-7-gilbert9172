package store.converter;

import java.util.Arrays;
import java.util.List;
import store.model.order.Quantity;

public class SimpleConverter {

    private SimpleConverter() {
    }

    public static List<String> stringToStringList(String source) {
        return Arrays.stream(source.split(","))
                .map(String::strip)
                .toList();
    }

    public static Quantity stringToQuantity(String source) {
        int intSource = Integer.parseInt(source);
        return Quantity.from(intSource);
    }

}
