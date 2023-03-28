package com.example.todaydrinkserver.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestFavoriteShop {
    private String userId;
    private String shopName;
}
