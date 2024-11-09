package store.infra.file;

import java.util.List;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

public class FileDataInitializer implements DataInitializer {

    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";

    private final FileLoader fileloader;
    private final StoreFileConverter storeFileConverter;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;


    public FileDataInitializer(FileLoader fileloader, StoreFileConverter storeFileConverter, ProductRepository productRepository,
            PromotionRepository promotionRepository) {
        this.fileloader = fileloader;
        this.storeFileConverter = storeFileConverter;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void init() {
        List<String> rawProducts = fileloader.loadFile(PRODUCTS_FILE_NAME);
        List<String> rawPromotions = fileloader.loadFile(PROMOTIONS_FILE_NAME);
        productRepository.saveAll(storeFileConverter.toProducts(rawProducts));
        promotionRepository.saveAll(storeFileConverter.toPromotions(rawPromotions));
    }
}
