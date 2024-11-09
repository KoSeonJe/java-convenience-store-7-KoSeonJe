package store;

import store.application.StoreService;
import store.view.ApplicationView;

public class ConvenicenceStore implements Store {

    private final ApplicationView applicationView;
    private final StoreService storeService;

    public ConvenicenceStore(ApplicationView applicationView, StoreService storeService) {
        this.applicationView = applicationView;
        this.storeService = storeService;
    }

    @Override
    public void open() {
        requirePurchaseItem();
    }

    private void requirePurchaseItem() {
        applicationView.showIntroduction(storeService.getAllProduct());
    }
}
