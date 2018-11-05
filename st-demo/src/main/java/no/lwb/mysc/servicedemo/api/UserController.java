package no.lwb.mysc.servicedemo.api;

import no.lwb.mysc.servicedemo.mybatis.domain.City;
import no.lwb.mysc.servicedemo.mybatis.service.CityService;
import no.lwb.mysc.servicedemo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserInfo() {
        User user = new User();
        user.setAge("123");
        user.setId(129);
        user.setName("test");
        return user;
    }

    @RequestMapping(value = "/city/{state}", method = RequestMethod.GET)
    public City getCityInfo(@PathVariable("state") String state) {
        City city = new City();
        city.setState(state);
        return cityService.get(city);
    }
}
