package com.example.todaydrinkserver.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class MenuDto {

    private String name; // 메뉴명
    private int price; // 가격
    private String image; // 메뉴 이미지
    private String shopName; // 가게명..?
}
