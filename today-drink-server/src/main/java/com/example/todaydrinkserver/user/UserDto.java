package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.ShopDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private String userName;
    private String userId;
    private String userPwd;
    private List<ShopDto> favoriteShops;

}
