package kr.co.ensmart.frameworkdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "kr.co.ensmart.frameworkdemo.app.mapper")
@EnableCaching
public class FrameworkDemoEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameworkDemoEhcacheApplication.class, args);
	}

}
