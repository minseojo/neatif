package com.minseojo.neatif.dto.item;

import com.minseojo.neatif.domain.item.ImageFile;
import com.minseojo.neatif.domain.item.ItemLargeCode;
import com.minseojo.neatif.domain.item.ItemMediumCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private boolean bestSeller;
    private List<ImageFile> imageFiles;
    private ItemLargeCode itemLargeCode;
    private ItemMediumCode itemMediumCode;

    public ItemDto(Long id, String name, int price, boolean bestSeller, List<ImageFile> imageFiles,
                   ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.bestSeller = bestSeller;
        this.imageFiles = imageFiles;
        this.itemLargeCode = itemLargeCode;
        this.itemMediumCode = itemMediumCode;
    }
}