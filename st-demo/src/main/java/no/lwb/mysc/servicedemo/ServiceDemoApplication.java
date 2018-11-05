package no.lwb.mysc.servicedemo;

import no.lwb.mysc.servicedemo.hello.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.CountDownLatch;


@SpringBootApplication
public class ServiceDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceDemoApplication.class);

//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
//                                            MessageListenerAdapter  messageListenerAdapter) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConnectionFactory);
//        container.addMessageListener(messageListenerAdapter, new PatternTopic("chat"));
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

    @Bean
    Receiver receiver(CountDownLatch countDownLatch) {
        return new Receiver(countDownLatch);
    }

    @Bean
    CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }

//    @Bean
//    StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        return new StringRedisTemplate(redisConnectionFactory);
//    }

    /**
     * spring 4.2 Spring MVC 开始支持 CORS
     * 使用 @CrossOrigin 或全局配置注册 WebMvcConfigurer bean
     * Cross-origin resource sharing (CORS)
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext cxt = SpringApplication.run(ServiceDemoApplication.class, args);
//        StringRedisTemplate template = cxt.getBean(StringRedisTemplate.class);
//        CountDownLatch latch = cxt.getBean(CountDownLatch.class);
//        LOGGER.info("Sending message...");
//        template.convertAndSend("chat", "hello from redis!");
//        latch.await();
//        System.exit(0);
    }
}
