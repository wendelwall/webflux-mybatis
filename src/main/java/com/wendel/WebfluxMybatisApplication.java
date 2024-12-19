package com.wendel;

import com.wendel.scan.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableFeignClients(basePackages = "com.wendel")
@MapperScan(basePackages = {"com.wendel.**.mapper"})
@EnableR2dbcRepositories
@SpringBootApplication
public class WebfluxMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxMybatisApplication.class, args);
	}
}
