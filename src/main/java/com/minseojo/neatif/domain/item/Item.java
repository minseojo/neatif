package com.minseojo.neatif.domain.item;

import com.minseojo.neatif.dto.item.UpdateItemDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(value = EnumType.STRING)
    private ItemStatus itemStatus; // 상품 상태 (IN_STOCK, SOLD_OUT)
    private boolean bestSeller; // 베스트 셀러 여부

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ImageFile> imageFiles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private ItemLargeCode itemLargeCode;

    @Enumerated(value = EnumType.STRING)
    private ItemMediumCode itemMediumCode;

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new IllegalArgumentException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    public void addImageFile(ImageFile imageFile) {
        this.imageFiles.add(imageFile);
        imageFile.setItem(this);
    }

    public boolean isBestSeller() {
        return this.bestSeller;
    }

    protected Item() {
    }

    public Item(String name, int price, int stockQuantity,
                ItemStatus itemStatus, String itemDetail, boolean bestSeller,
                ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode,
                LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemStatus = itemStatus;
        this.itemDetail = itemDetail;
        this.bestSeller = bestSeller;
        this.itemLargeCode = itemLargeCode;
        this.itemMediumCode = itemMediumCode;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void updateItem(UpdateItemDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
        this.itemDetail = dto.getItemDetail();
        this.bestSeller = dto.isBestSeller();
        this.itemLargeCode = dto.getItemLargeCode();
        this.itemMediumCode = dto.getItemMediumCode();
        this.lastModifiedDate = LocalDateTime.now();
    }
}
