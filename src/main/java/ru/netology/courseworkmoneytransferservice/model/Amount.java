package ru.netology.courseworkmoneytransferservice.model;

public record Amount(Currency currency, int value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value && currency == amount.currency;
    }

}
