package com.wendel;

import com.wendel.scan.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.wendel")
@EnableFeignClients(basePackages = "com.wendel")
@MapperScan(basePackages = "com.wendel.**.mapper")
@SpringBootApplication
public class WebfluxMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxMybatisApplication.class, args);
	}
}
