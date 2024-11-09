package store.presentation.controller;

import java.util.List;
import store.common.support.StoreMapper;
import store.domain.Product;
import store.presentation.dto.GetAllProductResponse;
import store.presentation.dto.RequestItem;
import store.presentation.view.ApplicationView;
import store.service.ProductService;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final ProductService productService;
    private final StoreMapper storeMapper;

    public ConvenicenceStore(ApplicationView applicationView, ProductService productService, StoreMapper storeMapper) {
        this.applicationView = applicationView;
        this.productService = productService;
        this.storeMapper = storeMapper;
    }

    @Override
    public void open() {
        List<RequestItem> requestItems = requirePurchaseItem();


    }

    private List<RequestItem> requirePurchaseItem() {
        List<Product> products = productService.getAllProduct();
        applicationView.introduceItem(GetAllProductResponse.from(products));
        String inputtedItems = applicationView.inputPurchaseItem();
        return storeMapper.toRequestItem(inputtedItems);
    }
}
