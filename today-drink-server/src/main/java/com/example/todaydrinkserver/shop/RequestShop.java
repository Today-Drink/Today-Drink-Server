package com.example.todaydrinkserver.shop;

import lombok.Data;

@Data
public class RequestShop {
    private String classify;
    private Integer num;
    private Integer endTime;
}
