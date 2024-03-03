package com.minseojo.neatif.domain.item;

import lombok.Getter;

@Getter
public enum ItemLargeCode {
    NEW_ARRIVALS(0, "New Arrivals"),
    BEST_24(11, "BEST 24"),
    OUTER(1, "Outer"),
    TOP(2, "Top"),
    SHIRTS(3, "Shirts"),
    PANTS(4, "Pants"),
    SUIT(5, "Suit"),
    BAG_SHOES(6, "Bag/Shoes"),
    ACC(8, "ACC"),
    SAIL(9, "Sail");

    private final int id;
    private final String displayName;

    ItemLargeCode(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public static ItemLargeCode getById(int id) {
        for (ItemLargeCode type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ItemType id: " + id);
    }
}
