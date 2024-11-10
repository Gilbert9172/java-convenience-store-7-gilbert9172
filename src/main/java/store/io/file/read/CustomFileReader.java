package store.io.file.read;

import java.util.List;

public interface CustomFileReader {
    List<String> readFileLinesFrom(final String filePath);
}
