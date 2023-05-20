package com.example.todaydrinkserver;

import com.example.todaydrinkserver.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodayDrinkServerApplication implements CommandLineRunner{

	@Autowired
	private ShopService shopService;

	public static void main(String[] args) {
		SpringApplication.run(TodayDrinkServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		shopService.loadDataIntoRedis();
	}
}
