package com.github.burcakulug.samples.bb1.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>{
    default Optional<Item> findOneAsOptional(Long id){
        return Optional.ofNullable(findOne(id));
    }
}
