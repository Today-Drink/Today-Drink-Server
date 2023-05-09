package com.example.todaydrinkserver.menu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
@Api(tags = "Menu Controller")
public class MenuController {
    // 메뉴
    private final MenuService menuService;

    @ApiOperation(value = "가게 이름으로 선택한 가게의 모든 메뉴 조회",
            notes = "가게 상세보기에서 [전체보기] 선택")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("")
    public ResponseEntity<Map<String,Object>>  getAllMenus(@RequestParam("name") String shopName) {
        List<MenuDto> mainMenus = menuService.getCategoryMenusByShopName("main",shopName);
        List<MenuDto> subMenus = menuService.getCategoryMenusByShopName("sub",shopName);
        List<MenuDto> drinks = menuService.getCategoryMenusByShopName("drink",shopName);
        Map<String, Object> result = new HashMap<>();
        result.put("main",mainMenus);
        result.put("sub",subMenus);
        result.put("drink",drinks);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "메뉴 등록", notes = "메뉴 정보를 받아 db에 저장한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("")
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createdMenu = menuService.createMenu(menuDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @ApiOperation(value = "메뉴 수정", notes = "id를 통해 조회한 메뉴를 새로운 데이터로 업데이트한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable("id") Long id,
                                           @RequestBody MenuDto menuDto) {
        String status = menuService.updateMenu(id, menuDto);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @ApiOperation(value = "메뉴 삭제", notes = "id를 통해 받은 메뉴를 db에서 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable("id") Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete success");
    }
    //    @ApiOperation(value = "Get Menus By Shop id", notes = "가게의 이름을 통해 메뉴들을 조회한다.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "success"),
//            @ApiResponse(code = 404, message = "error")
//    })
//    @GetMapping("/shop")
//    public ResponseEntity<List<MenuDto>> getMenusByShopName(@RequestParam("name") String shopName) {
//        List<MenuDto> menus = menuService.getMenusByShopName(shopName);
//        return ResponseEntity.status(HttpStatus.OK).body(menus);
//    }
}