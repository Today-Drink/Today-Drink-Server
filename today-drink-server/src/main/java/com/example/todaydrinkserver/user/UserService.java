package com.example.todaydrinkserver.user;

import com.example.todaydrinkserver.jwt.JwtTokenProvider;
import com.example.todaydrinkserver.shop.*;
import com.example.todaydrinkserver.jwt.TokenDTO;
import com.example.todaydrinkserver.shop.ShopRepository;
import com.example.todaydrinkserver.shop.Shop;
import com.example.todaydrinkserver.shop.ShopDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final FavoriteShopRepository favoriteShopRepository;
    private final ShopRepository shopRepository;
    @Transactional
    public UserDto getUser(String userId){
        Optional<User> user = userRepository.findByUserId(userId);
        List<FavoriteShop> favoriteShops = favoriteShopRepository.findAllByUser(user.get());
        List<ShopDto> shopList = new ArrayList<>();

        for(FavoriteShop f_shop : favoriteShops){
            Optional<Shop> shop = shopRepository.findById(f_shop.getShop().getId());
            ShopDto shopDto = ShopDto.builder()
                    .name(shop.get().getName())
                    .classify(shop.get().getClassify())
                    .num(shop.get().getNum())
                    .endTime(shop.get().getEndTime())
                    .address(shop.get().getAddress())
                    .latitude(shop.get().getLatitude())
                    .longitude(shop.get().getLongitude())
                    .build();
            shopList.add(shopDto);
        }

        return UserDto.builder()
                .userName(user.get().getUserName())
                .userId(user.get().getUserId())
                .userPwd((user.get().getUserPwd()))
                .favoriteShops(shopList)
                .build();
    }

    @Transactional
    public String registerFavoriteShop(String userId, String shopName){
        Optional<User> user = userRepository.findByUserId(userId);
        Optional<Shop> shop = shopRepository.findByName(shopName);
        FavoriteShop user_shop = FavoriteShop.builder()
                .user(user.get())
                .shop(shop.get())
                .build();
        favoriteShopRepository.save(user_shop);
        return "register favorite shop success";
    }

    @Transactional
    public String registerUser(RequestSignup newUser){
        User user = User.builder()
                .userId(newUser.getUserId())
                .userName(newUser.getUserName())
                .userPwd(newUser.getUserPw())
                .build();
        userRepository.save(user);
        return "SUCCESS";
    }

    @Transactional
    public ResponseLogin login(RequestLogin requestLogin){
        log.info("user id = {}", requestLogin.getUserId());
        User member = userRepository.findByUserId(requestLogin.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        log.info("request pw=>{}, member pw=>{}", requestLogin.getUserPw(), member.getUserPwd());

        // 로그인 시 패스워드가 불일치하면 에러 발생(passwordEncoder이용?)
        if (!requestLogin.getUserPw().equals(member.getUserPwd())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        TokenDTO token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

        return ResponseLogin.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Transactional
    public void updateUserShopList(Long id, RequestShop requestShop) {
        List<Shop> shops= shopRepository.findShopByFiltering(
                requestShop.getClassify(),
                requestShop.getNum(),
                requestShop.getEndTime());

        userRepository.updateUserShopList(id,shops);
    }

    @Transactional
    public List<ResponseAllShop> getShopList(Long id){
        User user = userRepository.findById(id).get();
        List<ResponseAllShop> responseAllShops = new ArrayList<>();
        List<Shop> shops = user.getShops();
        if (shops != null && !shops.isEmpty()) {
            shops.forEach(v -> {
                responseAllShops.add(ResponseAllShop.builder()
                        .name(v.getName())
                        .address(v.getAddress())
                        .tel(v.getTel())
                        .star(v.getStar())
                        .classify(v.getClassify())
                        .shopImage(v.getShopImage())
                        .build());
            });
        } else {
            ResponseAllShop responseAllShop = ResponseAllShop.builder()
                    .name(null)
                    .tel(null)
                    .address(null)
                    .shopImage(null)
                    .classify(null)
                    .star(0.0)
                    .build();
            responseAllShops.add(responseAllShop);
        }
        return responseAllShops;
    }
    public boolean logout(UserDto userDto){
       return true;
    }

}
