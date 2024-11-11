package store.infra.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import store.common.exception.FileLoadException;

public class FileLoader {

    public List<String> loadFile(String fileName) {
        List<String> fileData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getFilePath(fileName)))) {
            fileData = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileLoadException();
        };
        fileData.removeFirst();
        return fileData;
    }

    private InputStream getFilePath(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
