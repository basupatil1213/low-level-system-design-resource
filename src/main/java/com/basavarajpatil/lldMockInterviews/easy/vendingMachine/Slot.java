package com.basavarajpatil.lldMockInterviews.easy.vendingMachine;

public class Slot {
    private final String slotCode;   // e.g. "A1", "B2"
    private Item item;
    private int quantity;
    private final int capacity;
    private final int price;         // price in cents or dollars

    public Slot(String slotCode, int capacity, int price) {
        this.slotCode = slotCode;
        this.capacity = capacity;
        this.price = price;
        this.quantity = 0;
    }

    public String getSlotCode() {
        return slotCode;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0 || quantity > capacity) {
            throw new IllegalArgumentException("Quantity must be between 0 and capacity");
        }
        this.quantity = quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

    public void dispenseOne() {
        if (quantity <= 0) {
            throw new IllegalStateException("Slot is empty");
        }
        quantity--;
    }

    @Override
    public String toString() {
        return "Slot[" + slotCode + "] Item: " + (item != null ? item.getName() : "Empty") +
                ", Price: $" + price + ", Qty: " + quantity + "/" + capacity;
    }
}
