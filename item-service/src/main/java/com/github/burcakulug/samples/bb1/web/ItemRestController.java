package com.github.burcakulug.samples.bb1.web;

import com.github.burcakulug.samples.bb1.service.ItemService;
import com.github.burcakulug.samples.bb1.service.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<ItemDto> getItems() {
        return itemService.findAll();
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    public ItemDto getItems(@PathVariable Long itemId) {
        return itemService.findOne(itemId);
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public ItemDto save(@RequestBody @Valid ItemDto itemDto) {
        return itemService.save(itemDto);
    }
}
