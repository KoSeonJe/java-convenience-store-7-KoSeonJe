package store.payment.repository;

import java.util.ArrayList;
import java.util.List;
import store.payment.domain.Receipt;

public class ReceiptRepository {

    private static final ReceiptRepository INSTANCE = new ReceiptRepository();

    private final List<Receipt> repository = new ArrayList<>();

    private ReceiptRepository() {

    }

    public static ReceiptRepository getInstance() {
        return INSTANCE;
    }

    public void save(Receipt receipt) {
        repository.add(receipt);
    }

    public Receipt getRecent() {
        return repository.getLast();
    }

    public void clear() {
        repository.clear();
    }
}
