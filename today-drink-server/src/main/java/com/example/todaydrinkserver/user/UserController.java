package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.ShopDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Api(tags="User Controller")
public class UserController {
    private final UserService userService;
    @ApiOperation(value = "user info", notes = "user의 정보")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> viewUser(@PathVariable("id") Long userSn){
        UserDto userDto = userService.getUser(userSn);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @ApiOperation(value = "choose favorite shop", notes = "관심있는 shop 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/likeshop")
    public ResponseEntity<String> likeShop(@RequestBody RequestFavoriteShop requestFavoriteShop){
        String status = userService.registerFavoriteShop(requestFavoriteShop.getUserId(), requestFavoriteShop.getShopName());
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @ApiOperation(value = "join", notes = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/join")
    public String join(@RequestBody UserDto userDto){
        String status = userService.registerUser(userDto);
        return status;
    }

    @ApiOperation(value = "login", notes = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/login")
    public String login(@RequestBody RequestLogin requestLogin){
        String token = userService.login(requestLogin);
        return token;
    }
}
