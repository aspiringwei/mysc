package no.lwb.mysc.servicedemo.api;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import no.lwb.mysc.servicedemo.intf.UserClient;
import no.lwb.mysc.servicedemo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserClient userClient;

    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    @Hy
    public User getuserinfo() {
        return userClient.getuserinfo();
    }
}
