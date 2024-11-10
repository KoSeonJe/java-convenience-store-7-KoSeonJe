package store.payment.implement;

import store.payment.domain.Receipt;
import store.payment.repository.ReceiptRepository;

public class ReceiptFinder {

    private final ReceiptRepository receiptRepository;

    public ReceiptFinder(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt getRecent() {
        return receiptRepository.getRecent();
    }
}
