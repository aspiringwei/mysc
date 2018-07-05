package no.lwb.mysc.servicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker // 熔断处理
public class ServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemoApplication.class, args);
    }
}
