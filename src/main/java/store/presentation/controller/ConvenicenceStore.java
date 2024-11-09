package store.presentation.controller;

import java.util.List;
import store.common.support.StoreMapper;
import store.domain.Product;
import store.presentation.dto.GetAllProductResponse;
import store.presentation.view.ApplicationView;
import store.service.ProductService;

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
