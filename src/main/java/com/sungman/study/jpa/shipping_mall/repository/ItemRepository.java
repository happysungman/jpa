package com.sungman.study.jpa.shipping_mall.repository;

import com.sungman.study.jpa.shipping_mall.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
