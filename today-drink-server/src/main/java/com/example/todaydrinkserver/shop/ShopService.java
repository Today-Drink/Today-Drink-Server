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

    public List<ResponseAllShop> getShopsByStar(){
        List<Shop> shops = shopRepository.findAllByOrderByStarDesc();
        List<ResponseAllShop> responseShopList = new ArrayList<>();
        shops.forEach(v-> {
            responseShopList.add(ResponseAllShop.builder()
                    .name(v.getName())
                    .classify(v.getClassify())
                    .address(v.getAddress())
                    .tel(v.getTel())
                    .star(v.getStar())
                    .shopImage(v.getShopImage())
                    .build());
        });
        return responseShopList;
    }
    @Transactional
    public String registerShop(ShopDto shopDto){
        Shop shopEntity = Shop.builder()
                .name(shopDto.getName())
                .classify(shopDto.getClassify())
                .num(shopDto.getNum())
                .endTime(shopDto.getEndTime())
                .address(shopDto.getAddress())
                .tel(shopDto.getTel())
                .star(shopDto.getStar())
                .latitude(shopDto.getLatitude())
                .longitude(shopDto.getLongitude())
                .shopImage(shopDto.getShopImage())
                .build();
        shopRepository.save(shopEntity);
        return "register success";
    }

    @Transactional
    public List<ResponseAllShop> getShopByAll(){
        List<Shop> shopEntityList = shopRepository.findAllByOrderByIdAsc();

        List<ResponseAllShop> responseShopList = new ArrayList<>();
        shopEntityList.forEach(v-> {
            responseShopList.add(ResponseAllShop.builder()
                    .name(v.getName())
                    .classify(v.getClassify())
                    .address(v.getAddress())
                    .tel(v.getTel())
                    .star(v.getStar())
                    .shopImage(v.getShopImage())
                    .build());
        });
        return responseShopList;
    }
    @Transactional
    public ResponseShop getShop(Long id){
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

        ResponseShop responseShop = ResponseShop.builder()
                .name(shopEntity.getName())
                .classify(shopEntity.getClassify())
                .star(shopEntity.getStar())
                .tel(shopEntity.getTel())
                .address(shopEntity.getAddress())
                .shopImage(shopEntity.getShopImage())
                .menus(menuDtoList)
                .build();
        return responseShop;
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
                    .tel(v.getTel())
                    .star(v.getStar())
                    .classify(v.getClassify())
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

    public ResponseMap getLocationById(Long id){
        Shop shop = shopRepository.findById(id).get();
        ResponseMap responseMap = ResponseMap.builder()
                .name(shop.getName())
                .address(shop.getAddress())
                .latitude(shop.getLatitude())
                .longitude(shop.getLongitude())
                .build();
        return responseMap;
    }

}
