package no.lwb.mysc.servicedemo.api;

import no.lwb.mysc.servicedemo.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserInfo() {
        User user = new User();
        user.setAge("123");
        user.setId(129);
        user.setName("test");
        return user;
    }
}
