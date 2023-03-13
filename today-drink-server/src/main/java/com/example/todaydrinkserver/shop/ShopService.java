package com.example.todaydrinkserver.shop;

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
                .build();
        shopRepository.save(shopEntity);
        return "register success";
    }

    @Transactional
    public List<ShopDto> getShopByAll(){
        List<Shop> shopEntityList = shopRepository.findAll();

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
                    .build());
        });
        return shopDtoList;
    }
    @Transactional
    public ShopDto getShop(Long id){
        Shop shopEntity = shopRepository.findById(id).get();
        ShopDto shopDto = ShopDto.builder()
                .name(shopEntity.getName())
                .classify(shopEntity.getClassify())
                .num(shopEntity.getNum())
                .endTime(shopEntity.getEndTime())
                .address(shopEntity.getAddress())
                .latitude(shopEntity.getLatitude())
                .longitude(shopEntity.getLongitude())
                .build();
        return shopDto;
    }
    @Transactional
    public String updateShop(Long id, ShopDto shopDto){
        shopRepository.updateShop(id, shopDto.getClassify(),
                shopDto.getEndTime(), shopDto.getNum(),
                shopDto.getAddress(), shopDto.getLatitude(),
                shopDto.getLongitude());
        return "success";
    }
    @Transactional
    public String deleteShop(Long id){
        shopRepository.deleteById(id);
        return "success";
    }
}
