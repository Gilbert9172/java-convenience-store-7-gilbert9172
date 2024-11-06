package store.io.file;

import static store.error.ErrorMessage.FILE_NOT_FOUND;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MarkDownReader implements CustomFileReader {

    @Override
    public List<String> readFileLinesFrom(final String filePath) {
        List<String> readLines = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path)
                    .stream()
                    .skip(1)
                    .toList();
            readLines.addAll(lines);
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
        return readLines;
    }
}
