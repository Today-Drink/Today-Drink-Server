package com.example.todaydrinkserver.shop;

import com.example.todaydrinkserver.menu.MenuDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseShop {
    private String name;
    private String address;
    private String classify;
    private String tel;
    private Double star;
    private String shopImage;
    private List<MenuDto> menus;

}
