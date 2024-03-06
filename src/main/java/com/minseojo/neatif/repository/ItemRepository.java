package com.minseojo.neatif.repository;

import com.minseojo.neatif.domain.item.*;
import com.minseojo.neatif.dto.item.UpdateItemDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Long save(Item item);

    void update(UpdateItemDto dto);

    void deleteOne(Long id);

    Optional<Item> findById(Long id);


    List<Item> findAllItem();

    List<Item> findByIdWithLargeCodeWithMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode);

    List<Item> findBestTop4ByItemLargeCodeWithItemMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode);
    List<Item> findLimit24();

    List<Item> findBestLimit24();

    List<Item> findItemsOrderByNew(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode);

    List<Item> findItemsOrderByPriceAsc(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode);

    List<Item> findItemsOrderByPriceDesc(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode);

    List<Item> findItemsOrderByReview(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode);

    List<Item> findBestLimit4(ItemLargeCode c);

    List<Item> findByItemLargeCode(ItemLargeCode itemLargeCode);

    List<Item> findBest24();
    List<Item> findNewItem24();
    List<Item> findByItemLargeCodeWithSortType(ItemLargeCode itemLargeCode, SortType sortType);

    List<Item> findByItemLargeCodeWithItemMediumCodeWithSortType(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode, SortType sortType);
}
