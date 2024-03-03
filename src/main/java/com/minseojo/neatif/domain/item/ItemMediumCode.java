package com.minseojo.neatif.domain.item;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ItemMediumCode {

    /** OUTER */
    OUTER_JACKET(100, ItemLargeCode.OUTER, "자켓"),
    OUTER_CARDIGAN(101, ItemLargeCode.OUTER, "가디건"),
    OUTER_COAT(102, ItemLargeCode.OUTER, "코트"),
    OUTER_JUMPER_AND_ZIP_UP(103, ItemLargeCode.OUTER, "점퍼/짚업"),


    /** TOP */
    TOP_LONG_SLEEVE(200, ItemLargeCode.TOP,"긴팔"),
    TOP_KNIT(201, ItemLargeCode.TOP, "니트"),
    TOP_VEST(202, ItemLargeCode.TOP, "조끼"),
    TOP_SHORT_SLEEVE(203, ItemLargeCode.TOP,  "반팔"),
    TOP_SEVEN_SLEEVE(204, ItemLargeCode.TOP, "7부"),


    /** SHIRTS */
    SHIRTS_BASIC(300, ItemLargeCode.SHIRTS, "베이직"),
    SHIRTS_CHECK_AND_PATTERN(301, ItemLargeCode.SHIRTS, "체크/패턴"),
    SHIRTS_STRIPE(302, ItemLargeCode.SHIRTS, "스트라이프"),

    /** PANTS */
    PANTS_BASIC_SLACKS(400, ItemLargeCode.PANTS,"베이직 슬랙스"),
    PANTS_WIDE(401, ItemLargeCode.PANTS, "와이드 팬츠"),
    PANTS_DENIM(402, ItemLargeCode.PANTS, "데님"),
    PANTS_COTTON(403, ItemLargeCode.PANTS, "면바지"),
    PANTS_TRAINING(404, ItemLargeCode.PANTS, "트레이닝"),
    PANTS_SHORTS(405, ItemLargeCode.PANTS, "반바지"),

    /** SUIT */
    SUIT(500, ItemLargeCode.SUIT,"수트"),

    /** BAG/SHOES */
    BAG(600, ItemLargeCode.BAG_SHOES, "가방"),
    SHOES(700, ItemLargeCode.BAG_SHOES,"신발"),

    /** ACC */
    ACC_BELT(800, ItemLargeCode.ACC,"벨트"),
    ACC_TIE(801, ItemLargeCode.ACC,"타이"),
    ACC_SCARF_AND_MUFFLER(802, ItemLargeCode.ACC,"스카프/머플러"),
    ACC_JEWELRY(803, ItemLargeCode.ACC,"쥬얼리"),
    ACC_BRACELET(804, ItemLargeCode.ACC,"팔찌"),
    ACC_GLOVES(805, ItemLargeCode.ACC,"장갑"),
    ACC_GLASSES(806, ItemLargeCode.ACC,"안경"),
    ACC_OTHER(807, ItemLargeCode.ACC,"기타"),

    /** SAIL */
    SAIL(900, ItemLargeCode.SAIL,"세일"),
    DEFAULT(-1, null, "없음");

    private final Integer id;
    private final ItemLargeCode itemLargeCode;
    private final String displayName;

    ItemMediumCode(int id, ItemLargeCode itemLargeCode, String displayName) {
        this.id = id;
        this.itemLargeCode = itemLargeCode;
        this.displayName = displayName;
    }

    public static ItemMediumCode getById(Integer id) {
        if (id == null) {
            return null;
        }
        for (ItemMediumCode type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ItemType id: " + id);
    }

    public static List<ItemMediumCode> getByItemLargeCode(ItemLargeCode itemLargeCode) {
        List<ItemMediumCode> category = new ArrayList<>();
        for (ItemMediumCode type : values()) {
            if (type.itemLargeCode == itemLargeCode) {
                category.add(type);
            }
        }
        return category;
    }
}
