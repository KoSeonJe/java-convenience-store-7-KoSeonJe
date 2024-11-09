package store.presentation.view;

import store.presentation.dto.AllProductInfo;

public interface OutputView {

    void printWelcomeMessage();

    void printInventory(AllProductInfo response);
}
