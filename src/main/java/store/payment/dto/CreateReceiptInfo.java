package store.payment.dto;

import java.util.List;

public record CreateReceiptInfo(
        List<ProductDeductionInfo> productDeductionInfos,
        boolean isMembership
) {
}
