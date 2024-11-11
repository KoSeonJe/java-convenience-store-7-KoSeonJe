package store.payment.repository;

import java.util.ArrayList;
import java.util.List;
import store.payment.domain.PurchaseInfo;

public class InMemoryPurchaseInfoRepository implements PurchaseInfoRepository {

    private static final InMemoryPurchaseInfoRepository INSTANCE = new InMemoryPurchaseInfoRepository();

    private final List<PurchaseInfo> repository = new ArrayList<>();

    private InMemoryPurchaseInfoRepository() {

    }

    public static InMemoryPurchaseInfoRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(PurchaseInfo purchaseInfo) {
        repository.add(purchaseInfo);
    }

    @Override
    public PurchaseInfo getRecent() {
        return repository.getLast();
    }

    @Override
    public void clear() {
        repository.clear();
    }
}
