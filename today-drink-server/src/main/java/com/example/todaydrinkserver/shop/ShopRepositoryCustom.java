package com.example.todaydrinkserver.shop;

import java.util.List;

public interface ShopRepositoryCustom {
    void updateShop(Long id, ShopDto shopDto);

    List<Shop> findShopByFiltering(List<String> classify, List<Integer> num, List<Integer> endTime);
}
