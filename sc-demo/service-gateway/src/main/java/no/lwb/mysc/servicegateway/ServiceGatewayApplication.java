package no.lwb.mysc.servicegateway;

import no.lwb.mysc.servicegateway.filters.PreFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 *  see
 *  https://github.com/spring-guides/gs-gateway
 *  https://github.com/spring-guides/gs-routing-and-filtering
 *  请求路由和过滤 网关
 * @author WeiBin Lin
 */
@SpringBootApplication
@EnableZuulProxy
public class ServiceGatewayApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceGatewayApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
}
