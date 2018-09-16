package no.lwb.mysc.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author WeiBin Lin
 * @since 2018/9/15
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceProviderApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
    }
}
