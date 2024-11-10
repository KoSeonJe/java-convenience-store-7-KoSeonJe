package store.presentation.view.console;

import java.util.List;
import store.common.support.StoreMapper;
import store.payment.domain.PurchaseItemInfo;
import store.payment.domain.Receipt;
import store.presentation.dto.ProductAllInfo;
import store.presentation.view.ApplicationView;
import store.presentation.view.InputView;
import store.presentation.view.OutputView;

public class ApplicationConsoleView implements ApplicationView {

    private final InputView inputView;
    private final OutputView outputView;
    private final StoreMapper storeMapper;

    public ApplicationConsoleView(InputView inputView, OutputView outputView, StoreMapper storeMapper) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.storeMapper = storeMapper;
    }

    @Override
    public void introduceItem(ProductAllInfo response) {
        outputView.printWelcomeMessage();
        outputView.printInventory(response);
    }

    @Override
    public List<PurchaseItemInfo> inputPurchaseItem() {
        String inputtedItems = inputView.requireInputItem();
        return storeMapper.toPurchaseInfo(inputtedItems);
    }

    @Override
    public String confirmAdditionalItem(String name, int getCount) {
        return inputView.confirmAdditionalItem(name, getCount);
    }

    @Override
    public String confirmOriginalPrice(String name, int quantityDifference) {
        return inputView.confirmOriginalPrice(name, quantityDifference);
    }

    @Override
    public String confirmApplyMembership() {
        return inputView.confirmApplyMembership();
    }

    @Override
    public void printReceipt(Receipt receipt) {
        outputView.printReceipt(receipt);
    }
}
