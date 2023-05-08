package com.example.todaydrinkserver.shop;

import com.example.todaydrinkserver.menu.MenuDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ShopService {
    private final ShopRepository shopRepository;

    @Transactional
    public String registerShop(ShopDto shopDto){
        Shop shopEntity = Shop.builder()
                .name(shopDto.getName())
                .classify(shopDto.getClassify())
                .num(shopDto.getNum())
                .endTime(shopDto.getEndTime())
                .address(shopDto.getAddress())
                .latitude(shopDto.getLatitude())
                .longitude(shopDto.getLongitude())
                .shopImage(shopDto.getShopImage())
                .build();
        shopRepository.save(shopEntity);
        return "register success";
    }

    @Transactional
    public List<ShopDto> getShopByAll(){
        List<Shop> shopEntityList = shopRepository.findAllByOrderByIdAsc();

        List<ShopDto> shopDtoList = new ArrayList<>();
        shopEntityList.forEach(v-> {
            shopDtoList.add(ShopDto.builder()
                    .name(v.getName())
                    .classify(v.getClassify())
                    .num(v.getNum())
                    .endTime(v.getEndTime())
                    .address(v.getAddress())
                    .latitude(v.getLatitude())
                    .longitude(v.getLongitude())
                    .shopImage(v.getShopImage())
                    .build());
        });
        return shopDtoList;
    }
    @Transactional
    public ShopDto getShop(Long id){
        Shop shopEntity = shopRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid shop ID"));

        List<MenuDto> menuDtoList = new ArrayList<>();
        shopEntity.getMenus().forEach(menu -> {
            if(menu.getBest()){
                MenuDto menuDto = MenuDto.builder()
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .shopName(menu.getShopName())
                        .image(menu.getImage())
                        .build();
                menuDtoList.add(menuDto);
            }
        });

        ShopDto shopDto = ShopDto.builder()
                .name(shopEntity.getName())
                .classify(shopEntity.getClassify())
                .num(shopEntity.getNum())
                .endTime(shopEntity.getEndTime())
                .address(shopEntity.getAddress())
                .latitude(shopEntity.getLatitude())
                .longitude(shopEntity.getLongitude())
                .shopImage(shopEntity.getShopImage())
                .menus(menuDtoList)
                .build();
        return shopDto;
    }
    @Transactional
    public List<ResponseShop> getShopByFiltering(RequestShop requestShop){
        List<Shop> shops= shopRepository.findShopByFiltering(
                requestShop.getClassify(),
                requestShop.getNum(),
                requestShop.getEndTime());

        List<ResponseShop> responseShopList = new ArrayList<>();
        shops.forEach(v-> {
            responseShopList.add(ResponseShop.builder()
                    .name(v.getName())
                    .address(v.getAddress())
                    .latitude(v.getLatitude())
                    .longitude(v.getLongitude())
                    .build());
        });
        return responseShopList;
    }
    @Transactional
    public String updateShop(Long id, ShopDto shopDto){
        shopRepository.updateShop(id, shopDto);
        return "success";
    }
    @Transactional
    public String deleteShop(Long id){
        shopRepository.deleteById(id);
        return "success";
    }
}
