package store.presentation.view.console;

import java.util.List;
import store.common.exception.CustomException;
import store.common.support.Answer;
import store.common.support.StoreMapper;
import store.common.validate.StoreValidator;
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
    private final StoreValidator storeValidator;


    public ApplicationConsoleView(InputView inputView, OutputView outputView, StoreMapper storeMapper,
            StoreValidator storeValidator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.storeMapper = storeMapper;
        this.storeValidator = storeValidator;
    }

    @Override
    public void introduceItem(ProductAllInfo response) {
        outputView.printWelcomeMessage();
        outputView.printInventory(response);
    }

    @Override
    public List<PurchaseItemInfo> inputPurchaseItem() {
        while (true) {
            try {
                String inputtedItems = inputView.requireInputItem();
                List<PurchaseItemInfo> purchaseItemInfos = storeMapper.toPurchaseInfo(inputtedItems);
                storeValidator.enoughQuantity(purchaseItemInfos);
                storeValidator.existProduct(purchaseItemInfos);
                return purchaseItemInfos;
            } catch (CustomException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    @Override
    public String confirmAdditionalItem(String name, int getCount) {
        while (true) {
            try {
                String inputAnswer = inputView.confirmAdditionalItem(name, getCount);
                Answer.validateFormat(inputAnswer);
                return inputAnswer;
            } catch (CustomException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    @Override
    public String confirmOriginalPrice(String name, int quantityDifference) {
        while (true) {
            try {
                String inputAnswer = inputView.confirmOriginalPrice(name, quantityDifference);
                Answer.validateFormat(inputAnswer);
                return inputAnswer;
            } catch (CustomException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    @Override
    public String confirmApplyMembership() {
        while (true) {
            try {
                String inputAnswer = inputView.confirmApplyMembership();
                Answer.validateFormat(inputAnswer);
                return inputAnswer;
            } catch (CustomException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    @Override
    public void printReceipt(Receipt receipt) {
        outputView.printReceipt(receipt);
    }

    @Override
    public String askContinue() {
        while (true) {
            try {
                String inputAnswer = inputView.askContinue();
                Answer.validateFormat(inputAnswer);
                return inputAnswer;
            } catch (CustomException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
