package store.presentation.view;

import store.payment.domain.Receipt;
import store.presentation.dto.ProductAllInfo;

public interface OutputView {

    void printWelcomeMessage();

    void printInventory(ProductAllInfo response);

    void printReceipt(Receipt receipt);
}
