package store;

import java.util.List;

public class FileDataInitializer implements DataInitializer {

    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";

    private final FileLoader fileloader;
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;


    public FileDataInitializer(FileLoader fileloader, ObjectMapper objectMapper, ProductRepository productRepository,
            PromotionRepository promotionRepository) {
        this.fileloader = fileloader;
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void init() {
        List<String> rawProducts = fileloader.loadFile(PRODUCTS_FILE_NAME);
        List<String> rawPromotions = fileloader.loadFile(PROMOTIONS_FILE_NAME);
        productRepository.saveAll(objectMapper.toProducts(rawProducts));
        promotionRepository.saveAll(objectMapper.toPromotions(rawPromotions));
    }
}
