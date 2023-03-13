package com.example.todaydrinkserver.shop;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @ApiOperation(value = "Create a shop", notes = "Create a shop", tags = "Create Shops")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/shops")
    public ResponseEntity registerShop(@RequestBody ShopDto shopDto){
        String status = shopService.registerShop(shopDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @ApiOperation(value = "Get all shop", notes = "Get all shop", tags = "Get Shops")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/shops")
    public ResponseEntity<List<ShopDto>> getAllShop(){
        List<ShopDto> shopDtoList = shopService.getShopByAll();
        return ResponseEntity.status(HttpStatus.OK).body(shopDtoList);
    }

    @ApiOperation(value = "Get a shop by ID", notes = "Get a shop by specifying its ID", tags = "Get Shop")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/shops/{id}")
    public ResponseEntity<ShopDto> getShop(@PathVariable("id") Long id){
        ShopDto shopDto = shopService.getShop(id);
        return ResponseEntity.status(HttpStatus.OK).body(shopDto);
    }

    @ApiOperation(value = "Update a shop by ID", notes = "Update a shop by specifying its ID", tags = "Update Shop")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PutMapping("/shops/{id}")
    public ResponseEntity updateShop(@PathVariable("id") Long id,
                                       @RequestBody ShopDto shopDto){
        String status = shopService.updateShop(id,shopDto);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @ApiOperation(value = "Delete a shop by ID", notes = "Delete a shop by specifying its ID", tags = "Delete Shop")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @DeleteMapping("/shops/{id}")
    public ResponseEntity deleteShop(@PathVariable("id") Long id){
        String status = shopService.deleteShop(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
