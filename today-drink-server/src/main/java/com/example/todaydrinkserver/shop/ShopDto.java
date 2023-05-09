package com.example.todaydrinkserver.shop;

import com.example.todaydrinkserver.menu.Menu;
import com.example.todaydrinkserver.menu.MenuDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ShopDto {
    private String name;
    private String classify;
    private Integer num;
    private Integer endTime;
    private String address;
    private String tel;
    private Double star;
    private Double latitude;
    private Double longitude;

    private String shopImage;
    private List<MenuDto> menus;
}
