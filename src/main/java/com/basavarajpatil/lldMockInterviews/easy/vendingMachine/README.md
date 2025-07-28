# 🥤 Vending Machine – Low Level Design (LLD)

This project implements a simple object-oriented Low-Level Design (LLD) of a Vending Machine. It simulates how a vending machine handles item selection, payment, and item dispensing.

## 📌 Features

- Display available items
- Select an item by code
- Insert money (simulate payment)
- Dispense item
- Return change (if any)
- Handle out-of-stock and insufficient balance scenarios

---

## 🧱 Object-Oriented Design Overview

### ✅ Core Classes

| Class           | Responsibility |
|----------------|----------------|
| `VendingMachine` | Orchestrates the overall operations (selection, payment, dispense) |
| `Item`           | Represents a product (e.g., Coke, Chips, etc.) |
| `Slot`           | Holds an item and tracks quantity |
| `Inventory`      | Manages all slots |
| `PaymentService` | Handles payment processing and change calculations |
| `Display`        | Shows messages to the user |

### ✅ Enums

- `ItemType` (e.g., DRINK, SNACK, CHOCOLATE)
- `PaymentStatus` (SUCCESS, FAILED, INSUFFICIENT_FUNDS)

---

## 🧪 Sample Use Case (Flow)

1. Display all items with prices and codes
2. User selects an item code
3. Insert money
4. Machine checks stock and balance
5. Item is dispensed or an error is shown
6. Change is returned if needed

---

## 💡 Sample Code Usage (Console-Based)

```java
VendingMachine machine = new VendingMachine();
machine.initializeInventory();
machine.displayItems();

machine.selectItem("A1");
machine.insertMoney(50);
machine.dispenseItem();
