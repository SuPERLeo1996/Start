package com.leo.hogwarts;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.leo.hogwarts.mapper")
@SpringBootApplication
public class HogwartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HogwartsApplication.class, args);
	}
}
