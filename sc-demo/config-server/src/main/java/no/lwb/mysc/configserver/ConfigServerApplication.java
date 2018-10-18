package no.lwb.mysc.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WeiBin Lin
 */
@Configuration
@EnableAutoConfiguration
@RestController
//@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {

//    @Value("${config.name}")
    String name = "World";

    @GetMapping("/")
    public String home() {
        return "Hello " + name;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
