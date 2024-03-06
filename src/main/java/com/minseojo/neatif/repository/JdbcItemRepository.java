package com.minseojo.neatif.repository;

import com.minseojo.neatif.domain.item.Item;
import com.minseojo.neatif.domain.item.ItemLargeCode;
import com.minseojo.neatif.domain.item.ItemMediumCode;
import com.minseojo.neatif.domain.item.SortType;
import com.minseojo.neatif.dto.item.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcItemRepository implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Item item) {
        // next_id는 나중에 auto increment 로 변경될 예정
        long next_id = jdbcTemplate.queryForObject("SELECT MAX(item_id) from item", Long.class) + 1l;
        System.out.println(next_id);
        String sql = "INSERT INTO item (item_id, name, item_detail, price" +
                ", best_seller, stock_quantity) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, next_id, item.getName(), item.getItemDetail(), item.getPrice(), item.isBestSeller(), item.getStockQuantity());

        // 삽입된 아이템의 ID를 반환
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    @Override
    public void update(UpdateItemDto dto) {

    }

    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Item> findAllItem() {
        return null;
    }

    @Override
    public List<Item> findByIdWithLargeCodeWithMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return null;
    }

    @Override
    public List<Item> findBestTop4ByItemLargeCodeWithItemMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return null;
    }

    @Override
    public List<Item> findLimit24() {
        return null;
    }

    @Override
    public List<Item> findBestLimit24() {
        return null;
    }

    @Override
    public List<Item> findItemsOrderByNew(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return null;
    }

    @Override
    public List<Item> findItemsOrderByPriceAsc(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return null;
    }

    @Override
    public List<Item> findItemsOrderByPriceDesc(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return null;
    }

    @Override
    public List<Item> findItemsOrderByReview(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return null;
    }

    @Override
    public List<Item> findBestLimit4(ItemLargeCode c) {
        return null;
    }

    @Override
    public List<Item> findByItemLargeCode(ItemLargeCode itemLargeCode) {
        return null;
    }

    @Override
    public List<Item> findBest24() {
        return null;
    }

    @Override
    public List<Item> findNewItem24() {
        return null;
    }

    @Override
    public List<Item> findByItemLargeCodeWithSortType(ItemLargeCode itemLargeCode, SortType sortType) {
        return null;
    }

    @Override
    public List<Item> findByItemLargeCodeWithItemMediumCodeWithSortType(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode, SortType sortType) {
        return null;
    }
}
