package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.mapper")
@SpringBootApplication(scanBasePackages = "com.example")
public class DemoApplication {
	
	// 测试
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
