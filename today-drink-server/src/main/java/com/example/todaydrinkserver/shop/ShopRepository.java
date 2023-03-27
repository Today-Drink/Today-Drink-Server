package com.example.todaydrinkserver.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    @Modifying(clearAutomatically = true)
    @Query("update Shop s set s.classify = :classify," +
            "s.endTime = :endTime," +
            "s.num = :num," +
            "s.address = :address, " +
            "s.latitude = :latitude," +
            "s.longitude = :longitude" +
            " where s.id = :id")
    void updateShop(Long id, String classify,Integer num, Integer endTime, String address, Double latitude, Double longitude);

    Optional<Shop> findByName(String shopName);
    
    @Modifying(clearAutomatically = true)
    @Query("SELECT s FROM Shop s WHERE \n" +
            "    (s.classify = :classify) or \n" +
            "    (s.num = :num) or\n" +
            "    (s.endTime <= :endTime)")
    List<Shop> findShopByFiltering(String classify, Integer num, Integer endTime);
}
