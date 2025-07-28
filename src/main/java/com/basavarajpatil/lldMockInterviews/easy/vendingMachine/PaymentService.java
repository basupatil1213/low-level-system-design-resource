package com.basavarajpatil.lldMockInterviews.easy.vendingMachine;

public class PaymentService {
    private int balance;

    public PaymentService() {
        this.balance = 0;
    }

    public void insertMoney(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Inserted amount must be positive");
        balance += amount;
    }

    public PaymentStatus processPayment(int price) {
        if (balance < price) {
            return PaymentStatus.INSUFFICIENT_FUNDS;
        }
        balance -= price;
        return PaymentStatus.SUCCESS;
    }

    public int refund() {
        int amount = balance;
        balance = 0;
        return amount;
    }

    public int getBalance() {
        return balance;
    }
}
