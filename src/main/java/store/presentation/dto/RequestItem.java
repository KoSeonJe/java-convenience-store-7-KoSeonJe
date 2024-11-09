package store.presentation.dto;

import static store.common.constant.PromotionConstant.AGREE_MESSAGE;

import java.util.Objects;

public class RequestItem {

    private String name;

    private int quantity;

    public RequestItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void plusQuantity(String answer) {
        if (Objects.equals(answer, AGREE_MESSAGE)) {
            this.quantity++;
        }
    }
}
