package ru.netology.moneytransferservice.model;

public class CardQ {
    private String cardNumber;
    private String validTill;
    private String cvv;
    private Amount amount;

    public Card() {

    }

    public Card(String cardNumber, String validTill, String cvv, Amount amount) {
        this.cardNumber = cardNumber;
        this.validTill = validTill;
        this.cvv = cvv;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getValidTill() {
        return validTill;
    }

    public String getCvv() {
        return cvv;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Card{" + "CardNumber=" + this.cardNumber + ", validTill='" + this.validTill + "\'" + "Amount="
                + amount.toString() + "}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
