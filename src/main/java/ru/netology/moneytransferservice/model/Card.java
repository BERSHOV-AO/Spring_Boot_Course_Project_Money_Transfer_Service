package ru.netology.moneytransferservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

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
