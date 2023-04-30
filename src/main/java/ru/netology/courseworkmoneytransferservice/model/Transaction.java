package ru.netology.courseworkmoneytransferservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class Transaction {
    private final String cardFromCVV;
    private final String cardFromNumber;
    private final String cardFromValidTill;
    private final String cardToNumber;
    private final Amount amount;

    private final Date date;

    private final double commission;
    @Setter
    private TransactionState state;

    public Transaction(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromCVV = cardFromCVV;
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
        date = new Date();
        commission = amount.value() / 100;
    }

    @Override
    public String toString() {
        return "new Transaction{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                ", commission=" + commission +
                ", state=" + state +
                '}';
    }
}
