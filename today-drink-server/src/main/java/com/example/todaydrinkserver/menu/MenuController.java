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
    private final MenuService menuService;

    @ApiOperation(value = "Get All menus", notes = "카테고리 별로 전체 메뉴를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllMenus() {
        List<MenuDto> mainMenus = menuService.getCategoryMenus("main");
        List<MenuDto> subMenus = menuService.getCategoryMenus("sub");
        List<MenuDto> drinks = menuService.getCategoryMenus("drink");
        Map<String, Object> result = new HashMap<>();
        result.put("main",mainMenus);
        result.put("sub",subMenus);
        result.put("drink",drinks);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "Get a menu by id", notes = "id를 통해 특정 메뉴 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable("id") Long id) {
        MenuDto menuDto = menuService.getMenuById(id);
        return ResponseEntity.status(HttpStatus.OK).body(menuDto);
    }

    @ApiOperation(value = "Get Menus By Shop id", notes = "가게의 이름을 통해 메뉴들을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/shop")
    public ResponseEntity<List<MenuDto>> getMenusByShopName(@RequestParam("name") String shopName) {
        List<MenuDto> menus = menuService.getMenusByShopName(shopName);
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    @ApiOperation(value = "Post a menu", notes = "메뉴 정보를 받아 db에 저장한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("")
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createdMenu = menuService.createMenu(menuDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @ApiOperation(value = "Update a menu", notes = "id를 통해 조회한 메뉴를 새로운 데이터로 업데이트한다.")
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

    @ApiOperation(value = "Delete a menu", notes = "id를 통해 받은 메뉴를 db에서 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable("id") Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete success");
    }
}