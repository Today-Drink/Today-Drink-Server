package com.example.todaydrinkserver.repository;

import com.example.todaydrinkserver.domain.MenuTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuTb, Long> {

    List<MenuTb> findAllByShopName(String shopName);
}
