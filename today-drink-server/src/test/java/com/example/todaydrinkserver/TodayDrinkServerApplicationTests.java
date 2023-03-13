package com.example.todaydrinkserver;

import com.example.todaydrinkserver.domain.MenuTb;
import com.example.todaydrinkserver.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TodayDrinkServerApplicationTests {

	@Autowired
	private MenuRepository menuRepository;

	@Test
	void find1(){
		List<MenuTb> menuList = menuRepository.findAllByShopName("무이");
		for (MenuTb menu : menuList) {
			System.out.println("menu = " + menu.getMenuName());
		}

	}
	@Test
	void contextLoads() {
	}

}
