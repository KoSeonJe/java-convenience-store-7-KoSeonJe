package store.infra.file;

import java.util.List;
import store.common.StoreMapper;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

public class FileDataInitializer implements DataInitializer {

    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";

    private final FileLoader fileloader;
    private final StoreMapper storeMapper;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;


    public FileDataInitializer(FileLoader fileloader, StoreMapper storeMapper, ProductRepository productRepository,
            PromotionRepository promotionRepository) {
        this.fileloader = fileloader;
        this.storeMapper = storeMapper;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void init() {
        List<String> rawProducts = fileloader.loadFile(PRODUCTS_FILE_NAME);
        List<String> rawPromotions = fileloader.loadFile(PROMOTIONS_FILE_NAME);
        productRepository.saveAll(storeMapper.toProducts(rawProducts));
        promotionRepository.saveAll(storeMapper.toPromotions(rawPromotions));
    }
}
