package no.lwb.mysc.servicedemo.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.*;

@RestController
public class HystrixTest {

    @RequestMapping(value = "/query/user/name", method = RequestMethod.GET )
    @HystrixCommand(fallbackMethod = "getDefaultUserName", threadPoolKey = "query_user",
            threadPoolProperties = {
                    @HystrixProperty(name = CORE_SIZE, value = "10"),
                    @HystrixProperty(name = MAX_QUEUE_SIZE, value = "10")
            },
            commandProperties = {
                    @HystrixProperty(name = CIRCUIT_BREAKER_ENABLED, value = "true"),
                    @HystrixProperty(name = CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "1000"),
                    @HystrixProperty(name = CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "25")
            }
    )
    public String getUserName(String userID) throws InterruptedException {
        Thread.sleep(-1);
        return userID;
    }
    public String getDefaultUserName(String userID) {
        return "";
    }
}
