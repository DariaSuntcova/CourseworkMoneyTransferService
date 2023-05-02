package ru.netology.courseworkmoneytransferservice.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Amount {
    Currency currency;
    int value;

    public Amount(Currency currency, int value) {
        this.currency = currency;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value && currency == amount.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }
}
