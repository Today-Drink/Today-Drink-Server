package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.QShop;
import com.example.todaydrinkserver.shop.Shop;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
public class UserRepositoryImpl implements UserRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateUserShopList(Long userId, List<Shop> newShops) {

        User user = entityManager.find(User.class, userId);
        List<Shop> existingShops = new ArrayList<>(user.getShops());
        for (Shop existingShop : existingShops) {
            existingShop.setUser(null);
        }

        for (Shop newShop : newShops) {
            newShop.setUser(user);
            user.getShops().add(newShop);
        }

        // 엔티티를 저장합니다.
        entityManager.merge(user);
        entityManager.flush();
    }
}
