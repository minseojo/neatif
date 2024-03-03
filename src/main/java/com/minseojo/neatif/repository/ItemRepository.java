package com.minseojo.neatif.repository;

import com.minseojo.neatif.domain.item.*;
import com.minseojo.neatif.dto.item.UpdateItemDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ItemRepository {

    @PersistenceContext
    EntityManager em;


    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public void update(UpdateItemDto dto) {
        Item item = em.find(Item.class, dto.getId());
        item.updateItem(dto);
    }

    public void deleteOne(Long id) {
        Item item = em.find(Item.class, id);
        em.remove(item);
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(em.find(Item.class, id));
    }


    public List<Item> findAllItem() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findByIdWithLargeCodeWithMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        if (itemMediumCode == null) {
            return em.createQuery("select i from Item i " +
                            "where i.itemLargeCode =: itemLargeCode", Item.class)
                    .setParameter("itemLargeCode", itemLargeCode)
                    .getResultList();
        } else {
            return em.createQuery("select i from Item i " +
                            "where i.itemLargeCode =: itemLargeCode and " +
                            "i.itemMediumCode =: itemMediumCode", Item.class)
                    .setParameter("itemLargeCode", itemLargeCode)
                    .setParameter("itemMediumCode", itemMediumCode)
                    .getResultList();
        }
    }

    public List<Item> findBestTop4ByItemLargeCodeWithItemMediumCode(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        if (itemMediumCode == null || itemMediumCode == ItemMediumCode.DEFAULT) {
            return em.createQuery("select i from Item i " +
                            "where i.itemLargeCode =: itemLargeCode " +
                            "and i.bestSeller = true " +
                            "order by i.createdDate desc", Item.class)
                    .setParameter("itemLargeCode", itemLargeCode)
                    .setMaxResults(4)
                    .getResultList();
        } else {
            return em.createQuery("select i from Item i " +
                            "where i.itemLargeCode =: itemLargeCode and " +
                            "i.itemMediumCode =: itemMediumCode " +
                            "and i.bestSeller = true " +
                            "order by i.createdDate desc", Item.class)
                    .setParameter("itemLargeCode", itemLargeCode)
                    .setParameter("itemMediumCode", itemMediumCode)
                    .setMaxResults(4)
                    .getResultList();
        }
    }

    public List<Item> findLimit24() {
        return em.createQuery("select i from Item as i order by i.id desc", Item.class)
                .setMaxResults(24)
                .getResultList();
    }

    public List<Item> findBestLimit24() {
        return em.createQuery("select i from Item as i where i.bestSeller = true " +
                        "order by i.id desc",
                        Item.class)
                .setMaxResults(24)
                .getResultList();
    }

    public List<Item> findItemsOrderByNew(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return em.createQuery("select i from Item i order by i.id desc", Item.class)
                .getResultList();
    }

    public List<Item> findItemsOrderByPriceAsc(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return em.createQuery("select i from Item i order by i.price", Item.class)
                .getResultList();
    }

    public List<Item> findItemsOrderByPriceDesc(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return em.createQuery("select i from Item i order by i.price desc", Item.class)
                .getResultList();
    }

    public List<Item> findItemsOrderByReview(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode) {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findBestLimit4(ItemLargeCode c) {
        return em.createQuery("select i from Item i", Item.class)
                .setMaxResults(4)
                .getResultList();
    }

    public List<Item> findByItemLargeCode(ItemLargeCode itemLargeCode) {
        return em.createQuery("select i from Item i where i.itemLargeCode =: itemLargeCode", Item.class)
                .setParameter("itemLargeCode", itemLargeCode)
                .setMaxResults(24)
                .getResultList();
    }

    public List<Item> findBest24() {
        return em.createQuery("select i from Item i " +
                        "where i.bestSeller = true", Item.class)
                .setMaxResults(24)
                .getResultList();
    }

    public List<Item> findNewItem24() {
        return em.createQuery("select i from Item i order by createdDate desc ", Item.class)
                .setMaxResults(24)
                .getResultList();
    }

    public List<Item> findByItemLargeCodeWithSortType(ItemLargeCode itemLargeCode, SortType sortType) {
        StringBuilder sql = new StringBuilder("select i from Item i " +
                "where i.itemLargeCode =: itemLargeCode ");

        switch (sortType) {
            case NEW_ARRIVAL:
                sql.append("order by i.createdDate desc");
                break;
            case LOW_PRICE:
                sql.append("order by i.price asc");
                break;
            case HIGH_PRICE:
                sql.append("order by i.price desc");
                break;
            case REVIEW:
                // 리뷰순에 대한 로직
                break;
            default:
                // 다른 모든 경우에 대한 로직
                break;
        }


        return em.createQuery(sql.toString(), Item.class)
                .setParameter("itemLargeCode", itemLargeCode)
                .setMaxResults(4)
                .getResultList();
    }

    public List<Item> findByItemLargeCodeWithItemMediumCodeWithSortType(ItemLargeCode itemLargeCode, ItemMediumCode itemMediumCode, SortType sortType) {
        StringBuilder sql = new StringBuilder("select i from Item i " +
                "where i.itemLargeCode =: itemLargeCode and " +
                "i.itemMediumCode =: itemMediumCode ");

        switch (sortType) {
            case NEW_ARRIVAL:
                sql.append("order by i.createdDate desc");
                break;
            case LOW_PRICE:
                sql.append("order by i.price asc");
                break;
            case HIGH_PRICE:
                sql.append("order by i.price desc");
                break;
            case REVIEW:
                // 리뷰순에 대한 로직
                break;
            default:
                // 다른 모든 경우에 대한 로직
                break;
        }

        return em.createQuery(sql.toString(), Item.class)
                .setParameter("itemLargeCode", itemLargeCode)
                .setParameter("itemMediumCode", itemMediumCode)
                .setMaxResults(24)
                .getResultList();
    }
}
