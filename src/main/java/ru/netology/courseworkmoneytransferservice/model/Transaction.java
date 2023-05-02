package ru.netology.courseworkmoneytransferservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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
        commission = amount.getValue() / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.commission, commission) == 0 && Objects.equals(cardFromCVV, that.cardFromCVV) && Objects.equals(cardFromNumber, that.cardFromNumber) && Objects.equals(cardFromValidTill, that.cardFromValidTill) && Objects.equals(cardToNumber, that.cardToNumber) && Objects.equals(amount, that.amount) && Objects.equals(date, that.date) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromCVV, cardFromNumber, cardFromValidTill, cardToNumber, amount, date, commission, state);
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
