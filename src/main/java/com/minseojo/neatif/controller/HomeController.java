package com.minseojo.neatif.controller;

import com.minseojo.neatif.domain.item.Item;
import com.minseojo.neatif.dto.item.ItemDto;
import com.minseojo.neatif.repository.ItemRepository;
import com.minseojo.neatif.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Item> findItem = itemRepository.findLimit24();
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : findItem) {
            itemDtos.add(new ItemDto(
                    item.getId(), item.getName(), item.getPrice(),
                    item.isBestSeller(), item.getImageFiles(),
                    item.getItemLargeCode(), item.getItemMediumCode()
            ));
        }

        model.addAttribute("title", "NEW ARRIVALS & RESTOCK");
        model.addAttribute("sub_title", "이번주 신상품");
        model.addAttribute("items", itemDtos);

        return "index";
    }


}
