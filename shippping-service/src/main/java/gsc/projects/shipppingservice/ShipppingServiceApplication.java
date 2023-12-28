package gsc.projects.shipppingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShipppingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipppingServiceApplication.class, args);
	}

}
