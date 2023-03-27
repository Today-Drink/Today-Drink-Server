package com.example.todaydrinkserver;

import com.example.todaydrinkserver.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodayDrinkServerApplicationTests {
	@Autowired
	UserService userService;

	@Test
	void saveFavoriteShop(){
		String status = userService.registerFavoriteShop("chae01", "어오내");
		System.out.println(status);
	}

	@Test
	void contextLoads() {
	}

}
