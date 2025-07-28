package com.basavarajpatil.lldMockInterviews.easy.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Slot> slots;
    private final int maxSlots;

    Inventory(int maxSlots) {
        this.maxSlots = maxSlots;
        this.slots = new ArrayList<>();
    }

    public void addSlot(Slot slot) {
        if (slots.size() >= maxSlots) {
            throw new IllegalArgumentException("Maximum slot count exceeded");
        }
        for (Slot s : slots) {
            if (s.getSlotCode().equals(slot.getSlotCode())) {
                throw new IllegalArgumentException("Slot already exists: " + slot.getSlotCode());
            }
        }
        slots.add(slot);
    }

    public Slot getSlotByCode(String code) {
        for (Slot slot : slots) {
            if (slot.getSlotCode().equals(code)) {
                return slot;
            }
        }
        throw new IllegalArgumentException("Slot not found: " + code);
    }

    public List<Slot> getSlots() {
        return new ArrayList<>(slots);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Slot slot : slots) {
            sb.append(slot).append("\n");
        }
        return sb.toString();
    }
}
