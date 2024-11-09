package store.presentation.view;

import store.presentation.dto.GetAllProductResponse;

public interface OutputView {

    void printWelcomeMessage();

    void printInventory(GetAllProductResponse response);
}
