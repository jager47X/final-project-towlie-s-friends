package dto;

public enum TransactionType {
    Deposit("Deposit"),
    Transfer("Transfer"),

    Purchase("Purchase"),
    Withdraw("Withdraw"),
    Sell("Sell");



    private final String stringValue;

    TransactionType(final String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
