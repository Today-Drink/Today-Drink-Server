package com.example.todaydrinkserver.shop;

import lombok.Data;

import java.util.List;

@Data
public class RequestShop {
    private List<String> classify;
    private List<Integer> num;
    private List<Integer> endTime;
}
