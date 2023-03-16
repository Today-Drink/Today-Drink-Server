package com.example.todaydrinkserver.menu;

import com.example.todaydrinkserver.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {
    private String name;
    private Integer price;
    private String image;
    private String shopName;
}