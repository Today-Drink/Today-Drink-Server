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

    @ApiOperation(value = "가게 등록", notes = "가게의 정보를 받아 db에 저장한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("")
    public ResponseEntity registerShop(@RequestBody ShopDto shopDto){
        String status = shopService.registerShop(shopDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @ApiOperation(value = "초기화면", notes = "필터 선택 안했을 시 메세지 반환 \n" +
            "필터 선택된 상태일 시 가게 리스트 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("")
    public ResponseEntity<List<ResponseAllShop>> getAllShop(){
        List<ResponseAllShop> shopDtoList = shopService.getShopByAll();
        return ResponseEntity.status(HttpStatus.OK).body(shopDtoList);
    }

    @ApiOperation(value = "Id를 통해 특정 가게 조회", notes = "ID를 통해 특정 가게의 정보와 대표메뉴를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseShop> getShopById(@PathVariable("id") Long id){
        ResponseShop shopDto = shopService.getShop(id);
        return ResponseEntity.status(HttpStatus.OK).body(shopDto);
    }


    @ApiOperation(value = "Id를 통해 특정 가게의 정보 수정", notes = "ID를 통해 받은 가게의 정보 수정한다.")
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

    @ApiOperation(value = "Id를 통해 특정 가게 db에서 삭제", notes = "ID를 통해 받은 가게를 db에서 삭제한다.")
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
