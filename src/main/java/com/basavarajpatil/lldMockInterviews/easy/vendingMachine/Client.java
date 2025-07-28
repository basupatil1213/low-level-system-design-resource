package com.basavarajpatil.lldMockInterviews.easy.vendingMachine;

public class Client {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(10);
        machine.initializeInventory();

        machine.displayItems();

        machine.selectItem("A1");
        machine.insertMoney(5);
        machine.insertMoney(2);
        machine.dispenseItem();

        machine.selectItem("B1");
        machine.insertMoney(3);
        machine.dispenseItem();  // should say insufficient funds

        machine.cancelTransaction();
    }
}
