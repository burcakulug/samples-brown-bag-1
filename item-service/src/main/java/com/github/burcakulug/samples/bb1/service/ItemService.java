package com.github.burcakulug.samples.bb1.service;

import com.github.burcakulug.samples.bb1.service.dto.ItemDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemService {
    @Transactional
    ItemDto save(ItemDto itemDto);

    @Transactional(readOnly = true)
    List<ItemDto> findAll();

    @Transactional(readOnly = true)
    ItemDto findOne(Long id);
}
