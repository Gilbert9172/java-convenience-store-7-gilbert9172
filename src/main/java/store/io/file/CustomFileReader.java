package store.io.file;

import java.util.List;

public interface CustomFileReader {
    List<String> readFileLinesFrom(final String filePath);
}
