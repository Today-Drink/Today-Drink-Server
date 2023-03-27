package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.menu.MenuDto;
import com.example.todaydrinkserver.shop.ShopDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Tag(name = "User Service", description = "User API")
public class UserController {
    @Autowired
    private final UserService userService;
    @Operation(summary = "user info", description = "user의 정보")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MenuDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameter(name = "id", description = "사용자 id", example = "1L")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> viewUser(@PathVariable("id") Long userSn){
        UserDto userDto = userService.getUser(userSn);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> likeShop(@PathVariable("userId") String userId, @RequestBody ShopDto shopDto){
        String status = userService.registerFavoriteShop(userId, shopDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping("/join")
    public String join(@RequestBody UserDto userDto){
        String status = userService.registerUser(userDto);
        return status;
    }
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto){
        String token = userService.login(userDto);
        return token;
    }
}
