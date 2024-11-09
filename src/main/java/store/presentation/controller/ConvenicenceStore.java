package store.presentation.controller;

import java.util.List;
import store.common.support.StoreMapper;
import store.domain.Product;
import store.presentation.dto.ProductAllInfo;
import store.presentation.dto.RequestItem;
import store.presentation.view.ApplicationView;
import store.service.StoreService;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    public ConvenicenceStore(ApplicationView applicationView, StoreService storeService,
            StoreMapper storeMapper) {
        this.applicationView = applicationView;
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @Override
    public void open() {
        List<RequestItem> requestItems = requirePurchaseItem();
        checkPromotion(requestItems);

    }

    private void checkPromotion(List<RequestItem> requestItems) {
        requestItems.forEach(requestItem -> {
            if (storeService.checkPromotionQuantity(requestItem)) {
                String answer = applicationView.confirmAdditionalItem(requestItem.getName());
                requestItem.plusQuantity(answer);
            }
        });
    }

    //TODO: 존재하지 않은 상품 입력한 경우, 구매 수량이 재고 수량을 초과한 경우 예외 처리
    private List<RequestItem> requirePurchaseItem() {
        List<Product> products = storeService.getAllProduct();
        applicationView.introduceItem(ProductAllInfo.from(products));
        String inputtedItems = applicationView.inputPurchaseItem();
        return storeMapper.toRequestItem(inputtedItems);
    }
}
