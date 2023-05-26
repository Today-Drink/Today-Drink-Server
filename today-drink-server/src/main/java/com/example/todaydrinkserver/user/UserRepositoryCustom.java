package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.Shop;

import java.util.List;

public interface UserRepositoryCustom {
    void updateUserShopList(Long userId, List<Shop> newShops);
}
