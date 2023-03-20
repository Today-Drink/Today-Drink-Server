package com.example.todaydrinkserver.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByShopName(String shopName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Menu m SET m.name = :name, m.price = :price, m.image = :image, m.shopName = :shopName WHERE m.id = :id")
    void updateMenu(Long id, String name, Integer price, String image, String shopName);
}