package com.example.todaydrinkserver.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long>, QuerydslPredicateExecutor<Shop>,
        ShopRepositoryCustom {

    Optional<Shop> findByName(String shopName);
    List<Shop> findAllByOrderByIdAsc();
}
