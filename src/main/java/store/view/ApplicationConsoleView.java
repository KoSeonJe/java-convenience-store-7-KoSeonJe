package store.view;

import java.util.List;
import store.domain.Product;

public class ApplicationConsoleView implements ApplicationView {

    private final InputView inputView;
    private final OutputView outputView;

    public ApplicationConsoleView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public void introduceItem(List<Product> products) {
        outputView.printWelcomeMessage();
        outputView.printInventory(products);
    }

    @Override
    public String inputPurchaseItem() {
        return inputView.requireInputItem();
    }
}
