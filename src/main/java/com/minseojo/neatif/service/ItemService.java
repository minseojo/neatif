package com.minseojo.neatif.service;

import com.minseojo.neatif.domain.item.*;
import com.minseojo.neatif.dto.item.CreateItemDto;
import com.minseojo.neatif.dto.item.UpdateItemDto;
import com.minseojo.neatif.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    private final FileService fileService;

    public List<Item> findAllItem() {
        return itemRepository.findAllItem();
    }
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow( () -> new IllegalArgumentException("존재하지 않는 아이템 입니다."));
    }

    public List<Item> findNewItem24() {
        return itemRepository.findNewItem24();
    }

    public List<Item> findBestItem24() {
        return itemRepository.findBest24();
    }

    public List<Item> findBestTop4ByItemLargeCodeWithItemMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return itemRepository.findBestTop4ByItemLargeCodeWithItemMediumCode(itemLargeCode, itemMediumCode);
    }

    public List<Item> findByItemLargeCodeWithItemMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return itemRepository.findByIdWithLargeCodeWithMediumCode(itemLargeCode, itemMediumCode);
    }

    @Transactional
    public Item save(CreateItemDto form) {
        Item item = new Item(form.getName(), form.getPrice(), form.getStockQuantity(),
                ItemStatus.IN_STOCK, form.getItemDetail(), form.isBestSeller(),
                form.getItemLargeCode(), form.getItemMediumCode(),
                LocalDateTime.now(), LocalDateTime.now());

        // 이미지 로컬에 생성
        List<ImageFile> storedImageFiles = fileService.saveFiles(form.getImageFiles(), item);
        // uuid로 변경된 이미지 파일이름을 디비에 저장
        for (ImageFile storedImageFile : storedImageFiles) {
            item.addImageFile(storedImageFile);
        }

        itemRepository.save(item);
        return item;
    }

    @Transactional
    public void deleteOne(Long id) {
        itemRepository.deleteOne(id);
    }

    public List<Item> getSortedItems(String sortType, ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        List<Item> items;

        // 정렬 방식에 따라 데이터를 가져올 메서드를 선택
        switch (sortType) {
            case "신상품순":
                items = itemRepository.findItemsOrderByNew(itemLargeCode, itemMediumCode);
                break;
            case "낮은가격순":
                items = itemRepository.findItemsOrderByPriceAsc(itemLargeCode, itemMediumCode);
                break;
            case "높은가격순":
                items = itemRepository.findItemsOrderByPriceDesc(itemLargeCode, itemMediumCode);
                break;
            case "리뷰순":
                items = itemRepository.findItemsOrderByReview(itemLargeCode, itemMediumCode);
                break;
            default:
                // 기본 정렬 방식
                items = itemRepository.findLimit24();
                break;
        }
        return items;
    }

    @Transactional
    public void update(UpdateItemDto dto) {
        itemRepository.update(dto);
    }

    public List<Item> findByItemLargeCodeWithWithSortType(ItemLargeCode itemLargeCode, SortType sortType) {
        return itemRepository.findByItemLargeCodeWithSortType(itemLargeCode, sortType);
    }
    public List<Item> findByItemLargeCodeWithItemMediumCodeWithSortType(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode, SortType sortType) {
        return itemRepository.findByItemLargeCodeWithItemMediumCodeWithSortType(itemLargeCode, itemMediumCode, sortType);
    }

}
