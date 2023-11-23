package ru.netology.moneytransferservice.model;

public class Card {

    private String cardNumber;
    private String validTill;
    private String cvv;
    private Amount amount;

    public Card() {}

    public Card(String cardNumber, String validTill, String cvv, Amount amount) {
        this.cardNumber = cardNumber;
        this.validTill = validTill;
        this.cvv = cvv;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }


}
