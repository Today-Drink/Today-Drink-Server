package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.shop.RequestShop;
import com.example.todaydrinkserver.shop.ResponseAllShop;
import com.example.todaydrinkserver.shop.ResponseShop;
import com.example.todaydrinkserver.shop.ShopDto;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Api(tags="User Controller")
public class UserController {
    private final UserService userService;
    @ApiOperation(value = "사용자 정보 조회", notes = "로그인 시 발급받은 토큰을 헤더로 넘겨주면 로그인한 user의 정보를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping(value = "/view", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> viewUser(Authentication authentication){
        String userId = authentication.getName();
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @ApiOperation(value = "사용자가 관심있는 가게 등록", notes = "user가 관심있는 shop을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/likeshop")
    public ResponseEntity<String> likeShop(@RequestBody RequestFavoriteShop requestFavoriteShop){
        String status = userService.registerFavoriteShop(requestFavoriteShop.getUserId(), requestFavoriteShop.getShopName());
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 시 db에 user 정보가 저장된다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/join")
    public String join(@RequestBody UserDto userDto){
        String status = userService.registerUser(userDto);
        return status;
    }

    @ApiOperation(value = "로그인", notes = "로그인 시 토큰을 response한다.(유효시간 30분)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/login")
    public String login(@RequestBody RequestLogin requestLogin){
        String token = userService.login(requestLogin);
        return token;
    }

    @ApiOperation(value = "클라이언트가 조건을 선택하여 가게를 검색",
            notes = "분류, 인원수, 시간 선택하여 request 날린 후 성공하면 [필터가 적용되었습니다.] 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @PostMapping("/shops/{id}")
    public ResponseEntity<String> getShopByFiltering(@PathVariable(name = "id") Long userId,
                                                @RequestBody RequestShop requestShop) {
        userService.updateUserShopList(userId, requestShop);
        return ResponseEntity.status(HttpStatus.OK).body("필터가 적용되었습니다.");
    }

    @ApiOperation(value = "user가 필터에서 선택한 가게 조회",
            notes = "아직 필터기능 이용 안했을 시 모든 필드에 null값 반환\n" +
                    "필터에서 조건 선택했을 시 조건에 맞는 가게 리스트 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "error")
    })
    @GetMapping("/shops/{id}")
    public ResponseEntity<List<ResponseAllShop>> getShopList(@PathVariable(name = "id") Long userSn){
        List<ResponseAllShop> responseAllShops = userService.getShopList(userSn);
        return ResponseEntity.status(HttpStatus.OK).body(responseAllShops);
    }
}
