package no.lwb.mysc.service.api;

import com.google.common.base.Objects;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author WeiBin Lin
 * @since 2018/9/15
 */
@Slf4j
@RestController
public class ProviderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

    /**
     * // 服务注册
     */
    @Autowired
    private Registration registration;

    /**
     * // 服务发现客户端
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/provider")
    public String provider() {

        ServiceInstance instance = serviceInstance();
        return "Hello,Provider!"+instance.getServiceId() + "\t" + instance.getHost()
                + "\t" + instance.getPort() + "\t" + instance.getUri();
    }

    /**
     * 获取当前服务的服务实例
     *
     * @return ServiceInstance
     */
    private ServiceInstance serviceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());

        list.forEach(instance -> log.info(instance.getServiceId() + "\t" + instance.getHost()
                + "\t" + instance.getPort() + "\t" + instance.getUri())
        );
        return list.get(0);
    }

    @GetMapping("/users/{from}")
    @HystrixCommand(fallbackMethod = "fallbackUser")
    public List<String> user(@PathVariable("from") String from) {
        log.info("qry param::{}", from);
        if (Objects.equal(from, "china")) {
            return Arrays.asList("张三", "李四", "王五", "贼溜");
        }
        throw new InvalidParameterException();
    }

    public List<String> fallbackUser(@PathVariable("from") String from) {
        return Collections.singletonList("fallbackUser");
    }
}
