package com.github.burcakulug.samples.bb1.service;

import com.github.burcakulug.samples.bb1.domain.ItemRepository;
import com.github.burcakulug.samples.bb1.domain.Item;
import com.github.burcakulug.samples.bb1.service.dto.ItemDto;
import com.github.burcakulug.samples.bb1.service.exception.ItemNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        final Item item = Optional.ofNullable(itemDto.getId())
                .filter(Objects::nonNull)
                .flatMap(itemRepository::findOneAsOptional)
                .orElseGet(Item::new);
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        itemRepository.save(item);
        final ItemDto savedItemDto = mapToItemDto(item);
        return savedItemDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(this::mapToItemDto)
                .collect(toList());
    }

    @Override
    public ItemDto findOne(Long id) {
        return itemRepository.findOneAsOptional(id)
                .map(this::mapToItemDto)
                .orElseThrow(ItemNotFoundException::new);
    }

    private ItemDto mapToItemDto(Item item) {
        return modelMapper.map(item, ItemDto.class);
    }
}
