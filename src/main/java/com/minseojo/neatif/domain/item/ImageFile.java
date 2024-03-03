package com.minseojo.neatif.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class ImageFile {
    @Id
    @GeneratedValue
    @Column(name = "image_file_id")
    private Long id;

    private String originalFileName; // 원래 이미지파일 이름
    private String storeFileName; // 저장 될 고유의 이미지파일 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // ImageFile과 Item 간의 관계를 나타내는 필드

    public void setItem(Item item) {
        this.item = item;
    }

    protected ImageFile() {
    }

    public void removeItem() {
        this.item = null;
    }

    public ImageFile(String originalFileName, String storeFileName, Item item) {
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
        this.item = item;
    }
}
