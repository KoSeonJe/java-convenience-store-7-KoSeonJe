package store.presentation.view;

public interface InputView {

    String requireInputItem();

    String confirmAdditionalItem(String name);

    String confirmOriginalPrice(String name, int quantityDifference);

    String confirmApplyMembership();
}
