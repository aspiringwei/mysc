package no.lwb.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * todo feign 客户端方式
 * @author WeiBin Lin
 * @since 2018/9/15
 */
@RestController
public class ConsumerController {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/customer")
    public String customer() {
        String providerMsg = restTemplate.getForEntity("http://SERVICE-PROVIDER/provider",
                String.class).getBody();

        return "Hello,Customer! msg from provider<br/><br/> " + providerMsg;
    }

}
