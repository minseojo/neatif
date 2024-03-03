// ItemController.java
package com.minseojo.neatif.api;

import com.minseojo.neatif.domain.item.Item;
import com.minseojo.neatif.domain.item.ItemLargeCode;
import com.minseojo.neatif.domain.item.ItemMediumCode;
import com.minseojo.neatif.domain.item.SortType;
import com.minseojo.neatif.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemApiController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/mediumCategories")
    @ResponseBody
    public List<ItemMediumCode> getMediumCategories(@RequestParam("largeCategory") ItemLargeCode largeCategory) {
        return ItemMediumCode.getByItemLargeCode(largeCategory);
    }
}
