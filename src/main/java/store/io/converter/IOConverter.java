package store.io.converter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import store.model.dto.PreOrderDTO;

public class IOConverter {

    private IOConverter() {
    }

    public static List<PreOrderDTO> toPreOrderDTOListFrom(final String source) {
        List<String> splittedSources = Arrays.stream(source.split(","))
                .map(String::strip)
                .toList();
        return splittedSources.stream()
                .map(splittedSource -> {
                    String substring = splittedSource.substring(1, splittedSource.length() - 1);
                    String[] tokens = substring.split("-");
                    return PreOrderDTO.of(tokens[0], Integer.parseInt(tokens[1]), LocalDateTime.now());
                })
                .toList();
    }
}
