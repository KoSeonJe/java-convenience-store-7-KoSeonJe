package store.presentation.view;

import store.presentation.dto.ProductAllInfo;

public interface OutputView {

    void printWelcomeMessage();

    void printInventory(ProductAllInfo response);
}
