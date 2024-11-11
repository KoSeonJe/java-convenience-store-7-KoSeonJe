package store.presentation.view;

public interface InputView {

    String requireInputItem();

    String confirmAdditionalItem(String name, int getCount);

    String confirmOriginalPrice(String name, int quantityDifference);

    String confirmApplyMembership();

    String askContinue();
}
