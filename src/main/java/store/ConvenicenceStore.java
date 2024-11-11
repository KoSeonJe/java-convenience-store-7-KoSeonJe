package store;

import java.util.List;
import store.common.support.Answer;
import store.payment.domain.PurchaseItemInfo;
import store.payment.repository.InMemoryPurchaseInfoRepository;
import store.payment.repository.ReceiptRepository;
import store.presentation.controller.StoreFront;
import store.product.repository.InMemoryProductRepository;
import store.promotion.repository.InMemoryPromotionRepository;

public class ConvenicenceStore implements Store {

    private final StoreFront storeFront;

    public ConvenicenceStore(StoreFront storeFront) {
        this.storeFront = storeFront;
    }

    @Override
    public void sell() {
        String answer;
        do {
            List<PurchaseItemInfo> purchaseItemInfos = storeFront.requirePurchaseItem();
            storeFront.checkPromotion(purchaseItemInfos);
            storeFront.processPayment();
            storeFront.printReceipt();
            answer = storeFront.askContinue();
        } while (Answer.isAgree(answer));
    }

    @Override
    public void clear() {
        InMemoryPurchaseInfoRepository.getInstance().clear();
        ReceiptRepository.getInstance().clear();
        InMemoryProductRepository.getInstance().clear();
        InMemoryPromotionRepository.getInstance().clear();
    }
}
