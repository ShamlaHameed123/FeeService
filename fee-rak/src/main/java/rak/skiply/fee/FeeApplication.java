package rak.skiply.fee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeeApplication.class, args);
	}

}
