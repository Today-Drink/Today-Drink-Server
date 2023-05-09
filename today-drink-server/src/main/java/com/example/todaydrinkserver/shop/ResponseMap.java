package com.example.todaydrinkserver.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMap {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}
