package com.example.todaydrinkserver.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopDto {
    private String name;
    private String classify;
    private Integer num;
    private Integer endTime;
    private String address;
    private Double latitude;
    private Double longitude;
}
