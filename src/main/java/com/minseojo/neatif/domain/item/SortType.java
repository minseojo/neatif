package com.minseojo.neatif.domain.item;

import lombok.Getter;

@Getter
public enum SortType {
    NEW_ARRIVAL(0L, "신상품순"),
    LOW_PRICE(1L, "낮은가격순"),
    HIGH_PRICE(2L, "높은가격순"),
    REVIEW(3L, "리뷰순");

    private Long Id;
    private String name;

    SortType(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public static SortType getById(int id) {
        for (SortType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ItemType id: " + id);
    }
}
