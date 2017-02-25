package com.github.burcakulug.samples.bb1.config;

import com.github.burcakulug.samples.bb1.service.ItemService;
import com.github.burcakulug.samples.bb1.service.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("test-data")
public class TestDataConfig {

    @Autowired
    private ItemService itemService;

    @PostConstruct
    public void initData() {
        final ItemDto item1 = ItemDto.builder().name("Item 1").description("Item 1 description").build();
        final ItemDto item2 = ItemDto.builder().name("Item 2").description("Item 2 description").build();
        final ItemDto item3 = ItemDto.builder().name("Item 3").description("Item 3 description").build();
        itemService.save(item1);
        itemService.save(item2);
        itemService.save(item3);
    }
}
