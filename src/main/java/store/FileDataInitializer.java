package store;

import java.util.List;

public class FileDataInitializer implements DataInitializer {

    private static final String PRODUCTS_FILE_NAME = "products.md";

    private final FileLoader fileloader;

    public FileDataInitializer(FileLoader fileloader) {
        this.fileloader = fileloader;
    }

    @Override
    public void init() {
        List<String> rawProducts = fileloader.loadFile(PRODUCTS_FILE_NAME);
    }
}
