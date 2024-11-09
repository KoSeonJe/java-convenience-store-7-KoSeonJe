package store;

import store.application.ProductService;
import store.view.ApplicationView;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final ProductService productService;

    public ConvenicenceStore(ApplicationView applicationView, ProductService productService) {
        this.applicationView = applicationView;
        this.productService = productService;
    }

    @Override
    public void open() {
        requirePurchaseItem();
    }

    private void requirePurchaseItem() {
        applicationView.introduceItem(productService.getAllProduct());
        String inputtedItems = applicationView.inputPurchaseItem();
    }
}
