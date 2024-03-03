package com.minseojo.neatif.dto.item;

import com.minseojo.neatif.domain.item.ItemLargeCode;
import com.minseojo.neatif.domain.item.ItemMediumCode;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateItemDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String name;

    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    @NotNull(message = "가격은 필수 입력 값입니다.")
    private int price;

    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    @NotNull(message = "재고는 필수 입력 값입니다.")
    private int stockQuantity;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    private boolean bestSeller;

    private ItemLargeCode itemLargeCode;
    private ItemMediumCode itemMediumCode;

    private List<MultipartFile> imageFiles = new ArrayList<>();

    protected UpdateItemDto() {
    }

    public UpdateItemDto(Long id, String name, int price, int stockQuantity,
                         String itemDetail, boolean bestSeller,
                         List<MultipartFile> imageFiles) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemDetail = itemDetail;
        this.bestSeller = bestSeller;
        this.imageFiles = imageFiles;
    }
}
