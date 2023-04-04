package com.example.todaydrinkserver.menu;

import com.example.todaydrinkserver.shop.Shop;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "menu_tb")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_name")
    private String name;

    @Column(name = "menu_price")
    private Integer price;

    @Column(name = "menu_image")
    private String image;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "category")
    private String category;
    @Column(name="best")
    private Boolean best;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
}