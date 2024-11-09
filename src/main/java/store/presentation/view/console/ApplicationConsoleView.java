package store.presentation.view.console;

import store.presentation.dto.ProductAllInfo;
import store.presentation.view.ApplicationView;
import store.presentation.view.InputView;
import store.presentation.view.OutputView;

public class ApplicationConsoleView implements ApplicationView {

    private final InputView inputView;
    private final OutputView outputView;

    public ApplicationConsoleView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public void introduceItem(ProductAllInfo response) {
        outputView.printWelcomeMessage();
        outputView.printInventory(response);
    }

    @Override
    public String inputPurchaseItem() {
        return inputView.requireInputItem();
    }
}
