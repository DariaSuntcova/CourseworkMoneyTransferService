package ru.netology.courseworkmoneytransferservice.model;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private final SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    @Getter
    private final String cardFromCVV;
    @Getter
    private final String cardFromNumber;
    @Getter
    private final String cardFromValidTill;
    @Getter
    private final String cardToNumber;
    @Getter
    private final Amount amount;
    @Getter
    private final Date date;
    @Getter
    @Setter
    private TransactionState state;

    public Transaction(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromCVV = cardFromCVV;
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
        date = new Date();
        state = TransactionState.LOAD;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", state=" + state +
                '}';
    }
}
