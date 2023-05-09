package com.example.todaydrinkserver.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAllShop {
    private String name;
    private String address;
    private String classify;
    private String tel;
    private Double star;
    private String shopImage;
}
