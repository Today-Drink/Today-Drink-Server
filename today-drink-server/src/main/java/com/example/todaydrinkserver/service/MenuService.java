package com.example.todaydrinkserver.service;

import com.example.todaydrinkserver.domain.MenuTb;
import com.example.todaydrinkserver.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;


    public List<MenuTb> findMenuList(String shopName){
        List<MenuTb> menuList = menuRepository.findAllByShopName(shopName);
        return menuList;
    }

}
