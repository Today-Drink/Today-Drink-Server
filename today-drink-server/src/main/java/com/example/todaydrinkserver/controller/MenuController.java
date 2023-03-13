package com.example.todaydrinkserver.controller;

import com.example.todaydrinkserver.domain.MenuTb;
import com.example.todaydrinkserver.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

public class MenuController {
    private final MenuService menuService;

    @GetMapping("/menu")
    public String viewMenu(String shopName, Model model){
        List<MenuTb> menuList = menuService.findMenuList(shopName);
        model.addAttribute("menuList",menuList);
        return "Success";
    }
}
