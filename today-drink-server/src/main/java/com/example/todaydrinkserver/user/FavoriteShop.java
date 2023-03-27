package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "favorite_shop_tb")
public class FavoriteShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_sn")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
