package com.example.todaydrinkserver.shop;

import com.example.todaydrinkserver.menu.MenuDto;
import com.example.todaydrinkserver.menu.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
@Api(tags = "Shop Controller")
public class ShopController {
    private final ShopService shopService;

    @ApiOperation(value = "Create a shop", notes = "가게의 정보를 받아 db에 저장한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("")
    public ResponseEntity registerShop(@RequestBody ShopDto shopDto){
        String status = shopService.registerShop(shopDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @ApiOperation(value = "Get all shop", notes = "모든 가게 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("")
    public ResponseEntity<List<ShopDto>> getAllShop(){
        List<ShopDto> shopDtoList = shopService.getShopByAll();
        return ResponseEntity.status(HttpStatus.OK).body(shopDtoList);
    }

    @ApiOperation(value = "Get a shop by ID", notes = "ID를 통해 특정 가게의 정보 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable("id") Long id){
        ShopDto shopDto = shopService.getShop(id);
        return ResponseEntity.status(HttpStatus.OK).body(shopDto);
    }

    @ApiOperation(value = "Get shops By Filtering",
            notes = "카테고리(String) ex)" + "{이자카야, 치킨, 포차, 해산물, 고기&구이, 칵테일, 맥주, 기타},\n" +
            "인원수(integer) 2~4-> 4, 4~6 -> 6, 7인 이상 ->7\n" +
            "끝나는 시간(integer) ex)~22 -> 22, ~00 -> 24 , ~02 -> 26, ~04 ->28\n" +
                    "******형식 꼭 맞춰서 데이터 넘겨줄 것*******")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/lists")
    public ResponseEntity<List<ResponseShop>> getShopByFiltering(@RequestBody RequestShop requestShop){
        List<ResponseShop> responseShopList = shopService.getShopByFiltering(requestShop);
        return ResponseEntity.status(HttpStatus.OK).body(responseShopList);
    }

    @ApiOperation(value = "Update a shop by ID", notes = "ID를 통해 받은 가게의 정보 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateShop(@PathVariable("id") Long id,
                                       @RequestBody ShopDto shopDto){
        String status = shopService.updateShop(id,shopDto);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @ApiOperation(value = "Delete a shop by ID", notes = "ID를 통해 받은 가게를 db에서 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteShop(@PathVariable("id") Long id){
        String status = shopService.deleteShop(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
