package com.minseojo.neatif.controller;


import com.minseojo.neatif.domain.item.*;
import com.minseojo.neatif.dto.item.CreateItemDto;
import com.minseojo.neatif.dto.item.ItemDetailDto;
import com.minseojo.neatif.dto.item.ItemDto;
import com.minseojo.neatif.dto.item.UpdateItemDto;
import com.minseojo.neatif.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String sortType(
            @RequestParam(name = "item_large_code") int itemLargeCodeId,
            @RequestParam(name = "item_medium_code", required = false) Integer itemMediumCodeId,
            @RequestParam("sort_type") int sortTypeId,
            Model model) {

        List<Item> findBestItem;
        List<Item> findItem;
        ItemLargeCode itemLargeCode = ItemLargeCode.getById(itemLargeCodeId);
        ItemMediumCode itemMediumCode = ItemMediumCode.getById(itemMediumCodeId);
        SortType sortType = SortType.getById(sortTypeId);

        findBestItem = itemService.findBestTop4ByItemLargeCodeWithItemMediumCode(itemLargeCode, itemMediumCode);
        if (itemMediumCode == ItemMediumCode.DEFAULT) {
            findItem = itemService.findByItemLargeCodeWithWithSortType(itemLargeCode, sortType);
        } else {
            findItem = itemService.findByItemLargeCodeWithItemMediumCodeWithSortType(itemLargeCode, itemMediumCode, sortType);
        }

        System.out.println("findSize" + findBestItem.size());

        List<ItemMediumCode> subCategory = ItemMediumCode.getByItemLargeCode(itemLargeCode);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("title", itemLargeCode.getDisplayName());

        List<ItemDto> bestItemDts = new ArrayList<>();
        List<ItemDto> itemDtos = new ArrayList<>();
        findBestItem.forEach(item ->
                bestItemDts.add(new ItemDto(item.getId(), item.getName(), item.getPrice(), item.isBestSeller(), item.getImageFiles(), item.getItemLargeCode(), item.getItemMediumCode()))
        );
        findItem.forEach(item ->
                itemDtos.add(new ItemDto(item.getId(), item.getName(), item.getPrice(), item.isBestSeller(), item.getImageFiles(), item.getItemLargeCode(), item.getItemMediumCode()))
        );

        model.addAttribute("bestItems", bestItemDts);
        model.addAttribute("items", itemDtos);

        model.addAttribute("itemLargeCodeId", itemLargeCodeId);
        if (itemMediumCodeId != null) {
            model.addAttribute("itemMediumCodeId", itemMediumCodeId);
        }
        //model.addAttribute("pageNo", 1);

        return "item/item_list";
    }

    @GetMapping("/items/new")
    public String createItem(Model model) {
        model.addAttribute("item", new CreateItemDto());
        return "item/createItemForm";
    }

    @GetMapping("/items/update")
    public String updateAll(Model model) {
        List<Item> items = itemService.findAllItem();
        model.addAttribute("items", items);
        return "item/updateItem";
    }

    @GetMapping("/items/update/{id}")
    public String updateForm(@PathVariable("id") Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/updateItemForm";
    }

    @PostMapping("/items/update/{id}")
    public String update(
            @PathVariable("id") Long itemId,
            @ModelAttribute("item") UpdateItemDto dto,
            RedirectAttributes redirectAttributes) {
        itemService.update(dto);
        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/items/{itemId}";
    }

    @PostMapping("/items/new")
    public String createItem(
            @Validated @ModelAttribute("item") CreateItemDto form,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("{}", bindingResult);
            return "index";
        }

        Item savedItem = itemService.save(form);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable("id") Long id, Model model) {
        Item findItem = itemService.findById(id);
        ItemDetailDto itemDetailDto = new ItemDetailDto(findItem.getName(), findItem.getPrice(),
                findItem.isBestSeller(), findItem.getImageFiles(), findItem.getItemStatus());
        model.addAttribute("item", itemDetailDto);
        return "item/item_detail";
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long itemId) {
        itemService.deleteOne(itemId);
        return ResponseEntity.ok("Item deleted successfully");
    }

    @GetMapping("/items/list")
    public String itemList(
            @RequestParam(name = "item_large_code") int itemLargeCodeId,
            @RequestParam(name = "item_medium_code", required = false) Integer itemMediumCodeId,
            Model model) {

        List<Item> findBestItem = new ArrayList<>();
        List<Item> findItem = null;
        String htmlFile = "index2";

        ItemLargeCode itemLargeCode = ItemLargeCode.getById(itemLargeCodeId);
        ItemMediumCode itemMediumCode = ItemMediumCode.getById(itemMediumCodeId);
        if (itemLargeCode == ItemLargeCode.NEW_ARRIVALS) {
            findItem = itemService.findNewItem24();
            model.addAttribute("title", itemLargeCode.getDisplayName());
        } else if (itemLargeCode == ItemLargeCode.BEST_24) {
            findItem = itemService.findBestItem24();
            model.addAttribute("title", itemLargeCode.getDisplayName());
        } else {
            findBestItem = itemService.findBestTop4ByItemLargeCodeWithItemMediumCode(itemLargeCode, itemMediumCode);
            findItem = itemService.findByItemLargeCodeWithItemMediumCode(itemLargeCode, itemMediumCode);
            List<ItemMediumCode> subCategory = ItemMediumCode.getByItemLargeCode(itemLargeCode);
            model.addAttribute("subCategory", subCategory);
            model.addAttribute("title", itemLargeCode.getDisplayName());
            htmlFile = "item/item_list";
        }

        List<ItemDto> bestItemDts = new ArrayList<>();
        List<ItemDto> itemDtos = new ArrayList<>();
        findBestItem.forEach(item ->
                bestItemDts.add(new ItemDto(item.getId(), item.getName(), item.getPrice(), item.isBestSeller(), item.getImageFiles(), item.getItemLargeCode(), item.getItemMediumCode()))
        );
        findItem.forEach(item ->
                itemDtos.add(new ItemDto(item.getId(), item.getName(), item.getPrice(), item.isBestSeller(), item.getImageFiles(), item.getItemLargeCode(), item.getItemMediumCode()))
        );

        model.addAttribute("bestItems", bestItemDts);
        model.addAttribute("items", itemDtos);

        model.addAttribute("itemLargeCodeId", itemLargeCodeId);
        model.addAttribute("itemMediumCodeId", itemMediumCodeId);

        log.info("itemMediumCodeId = {}", itemMediumCodeId);
        //model.addAttribute("pageNo", 1);

        return htmlFile;
    }

    @GetMapping("/items/updateData")
    public String updateData(@RequestParam String sortType,
                             @RequestParam int itemLargeCode,
                             @RequestParam int itemMediumCode,
                             Model model) {
        // 서버에서 정렬된 데이터를 가져오는 비즈니스 로직 수행
        List<Item> sortedItems = itemService.getSortedItems(sortType,
                ItemLargeCode.getById(itemLargeCode), ItemMediumCode.getById(itemMediumCode));

        // 모델에 데이터를 추가하여 Thymeleaf에서 사용할 수 있도록 함
        model.addAttribute("items", sortedItems);

        // Thymeleaf 템플릿 파일로 이동
        return "item/item_list";
    }

}
