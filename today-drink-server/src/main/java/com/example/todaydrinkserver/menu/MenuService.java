package com.example.todaydrinkserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    @Transactional
    public List<MenuDto> getAllMenus() {
        List<Menu> menuList = menuRepository.findAll();
        return getMenuDtos(menuList);
    }

    @Transactional
    public List<MenuDto> getCategoryMenus(String category){
        List<Menu> menuList = menuRepository.findByCategory(category);
        return getMenuDtos(menuList);
    }

    private List<MenuDto> getMenuDtos(List<Menu> menuList) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        menuList.forEach(v->{
            menuDtoList.add(MenuDto.builder()
                    .name(v.getName())
                    .price(v.getPrice())
                    .image(v.getImage())
                    .description(v.getDescription())
                    .shopName(v.getShopName())
                    .build());
        });
        return menuDtoList;
    }

    @Transactional
    public MenuDto getMenuById(Long id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            MenuDto menuDto = MenuDto.builder()
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .image(menu.getImage())
                    .description(menu.getDescription())
                    .shopName(menu.getShopName())
                    .build();
            return menuDto;
        } else {
            throw new MenuNotFoundException("Menu not found");
        }
    }

    @Transactional
    public List<MenuDto> getMenusByShopName(String shopName) {
        List<Menu> menus = menuRepository.findByShopName(shopName);
        return getMenuDtos(menus);
    }

    @Transactional
    public MenuDto createMenu(MenuDto menuDto) {
        Menu saveMenu = Menu.builder()
                .name(menuDto.getName())
                .price(menuDto.getPrice())
                .image(menuDto.getImage())
                .description(menuDto.getDescription())
                .shopName(menuDto.getShopName())
                .build();
        menuRepository.save(saveMenu);
        return menuDto;
    }

    @Transactional
    public String updateMenu(Long id, MenuDto menuDto) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            menuRepository.updateMenu(id,menuDto.getName(),
                    menuDto.getPrice(),
                    menuDto.getImage(),
                    menuDto.getDescription(),
                    menuDto.getShopName());
            return "success";
        } else {
            throw new MenuNotFoundException("Menu not found");
        }
    }

    @Transactional
    public void deleteMenu(Long id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            menuRepository.deleteById(id);
        } else {
            throw new MenuNotFoundException("Menu not found");
        }
    }
}

