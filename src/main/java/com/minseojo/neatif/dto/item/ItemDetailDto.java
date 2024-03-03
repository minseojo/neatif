package com.minseojo.neatif.dto.item;

import com.minseojo.neatif.domain.item.ImageFile;
import com.minseojo.neatif.domain.item.ItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemDetailDto {
    private String name;
    private int price;
    private boolean bestSeller;
    private List<ImageFile> imageFiles = new ArrayList<>();
    private ItemStatus itemStatus;

    public ItemDetailDto(String name, int price, boolean bestSeller, List<ImageFile> imageFiles, ItemStatus itemStatus) {
        this.name = name;
        this.price = price;
        this.bestSeller = bestSeller;
        this.imageFiles = imageFiles;
        this.itemStatus = itemStatus;
    }
}
