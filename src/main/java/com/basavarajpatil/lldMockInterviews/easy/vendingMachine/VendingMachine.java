package com.basavarajpatil.lldMockInterviews.easy.vendingMachine;

public class VendingMachine {
    private final Inventory inventory;
    private final PaymentService paymentService;
    private final Display display;
    private Slot selectedSlot;

    public VendingMachine(int maxSlots) {
        this.inventory = new Inventory(maxSlots);
        this.paymentService = new PaymentService();
        this.display = new Display();
    }

    public void initializeInventory() {
        // Example preloading, you can customize this
        Slot slotA1 = new Slot("A1", 10, 7);
        slotA1.setItem(new Item("Coke", Item.Type.DRINK));
        slotA1.setQuantity(5);

        Slot slotB1 = new Slot("B1", 8, 5);
        slotB1.setItem(new Item("Chips", Item.Type.SNACK));
        slotB1.setQuantity(8);

        inventory.addSlot(slotA1);
        inventory.addSlot(slotB1);
    }

    public void displayItems() {
        display.showMessage("Available Items:");
        for (Slot slot : inventory.getSlots()) {
            if (!slot.isEmpty()) {
                display.showMessage(slot.toString());
            }
        }
    }

    public void selectItem(String slotCode) {
        try {
            selectedSlot = inventory.getSlotByCode(slotCode);
            if (selectedSlot.isEmpty()) {
                display.showMessage("Selected slot " + slotCode + " is out of stock.");
                selectedSlot = null;
            } else {
                display.showMessage("Selected " + selectedSlot.getItem().getName() + " for $" + selectedSlot.getPrice());
            }
        } catch (IllegalArgumentException e) {
            display.showMessage("Invalid slot code: " + slotCode);
            selectedSlot = null;
        }
    }

    public void insertMoney(int amount) {
        paymentService.insertMoney(amount);
        display.showMessage("Inserted $" + amount + ". Current balance: $" + paymentService.getBalance());
    }

    public void dispenseItem() {
        if (selectedSlot == null) {
            display.showMessage("No item selected.");
            return;
        }

        int price = selectedSlot.getPrice();
        PaymentStatus status = paymentService.processPayment(price);

        if (status == PaymentStatus.INSUFFICIENT_FUNDS) {
            display.showMessage("Insufficient funds. Please insert more money.");
            return;
        }

        // Dispense the item
        selectedSlot.dispenseOne();
        display.showMessage("Dispensed: " + selectedSlot.getItem().getName());

        // Return change if any
        int change = paymentService.refund();
        if (change > 0) {
            display.showMessage("Returned change: $" + change);
        }

        selectedSlot = null; // reset selection after dispensing
    }

    public void cancelTransaction() {
        int refundAmount = paymentService.refund();
        display.showMessage("Transaction cancelled. Refunded: $" + refundAmount);
        selectedSlot = null;
    }
}
