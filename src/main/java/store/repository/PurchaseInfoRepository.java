package store.repository;

import java.util.ArrayList;
import java.util.List;
import store.domain.PurchaseInfo;

public class PurchaseInfoRepository {

    private static final PurchaseInfoRepository INSTANCE = new PurchaseInfoRepository();

    private final List<PurchaseInfo> repository = new ArrayList<>();

    private PurchaseInfoRepository() {

    }

    public static PurchaseInfoRepository getInstance() {
        return INSTANCE;
    }

    public void save(PurchaseInfo purchaseInfo) {
        repository.add(purchaseInfo);
    }
}
