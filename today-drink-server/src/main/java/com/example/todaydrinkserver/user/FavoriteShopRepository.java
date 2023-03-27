package com.example.todaydrinkserver.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteShopRepository extends JpaRepository<FavoriteShop, Long> {
    List<FavoriteShop> findAllByUser(User user);
}
