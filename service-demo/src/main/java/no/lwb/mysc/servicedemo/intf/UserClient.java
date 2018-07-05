package no.lwb.mysc.servicedemo.intf;

import no.lwb.mysc.servicedemo.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("biz-service-0")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getuser")
    public User getuserinfo();
}
