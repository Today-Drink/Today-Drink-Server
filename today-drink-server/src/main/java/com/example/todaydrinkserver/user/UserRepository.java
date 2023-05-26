package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>,
    UserRepositoryCustom{

    Optional<User> findByUserId(String userId);
}
