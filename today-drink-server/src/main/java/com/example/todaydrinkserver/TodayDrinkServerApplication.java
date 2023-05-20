package com.example.todaydrinkserver;

import com.example.todaydrinkserver.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodayDrinkServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(TodayDrinkServerApplication.class, args);
	}

}
