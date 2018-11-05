package no.lwb.mysc.servicedemo.mybatis;

import lombok.extern.log4j.Log4j2;
import no.lwb.mysc.servicedemo.mybatis.domain.City;
import no.lwb.mysc.servicedemo.mybatis.mapper.CityMapper;
import no.lwb.mysc.servicedemo.mybatis.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能点::
 * 1. mybatis功能验证
 * 2. spring事务验证
 * 3. spring事务传播特性验证
 * 4. 分页插件功能验证
 *
 * ------------------------------------------------------
 *
 * 默认单元测试 @Rollback(true) Rolled back transaction for test 事务回滚
 *
 * @author WeiBin Lin
 */
@RunWith(SpringRunner.class)
@MybatisTest
@Log4j2
@ComponentScan(basePackages = "no.lwb.mysc.servicedemo.mybatis")
public class CityMapperTests {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CityService cityService;

    @Test
    public void findByStateTest() {
        City city = cityMapper.findByState("ca");
        city = cityService.get(city);
        log.info(city);
    }

    @Test
    public void insertCityTest() {
        City city = new City();
        city.setCountry("日本");
        city.setState("xx");
        city.setName("苍老师");
        int count = cityMapper.insertCity(city);
        log.info("updates:{}", count);
//        city = cityMapper.findByState("有效");

    }

    @Test
    public void insertCityServiceTest() {
        City city = new City();
        city.setCountry("日本");
        city.setState("有效");
        city.setName("苍老师");
        cityService.insert(city);
        city.setCountry("new");
        cityService.insertNew(city);
    }

    @Test
    @Rollback(true)
    public void insertCityServiceTest1() {
        City city = new City();
        city.setCountry("东京");
        city.setState("有效");
        city.setName("哇");
        cityService.insert(city);
        city.setCountry("nested");
        cityService.insertNested(city);
    }

    @Test
    public void delete() {
        cityService.delete("ca");
    }
}
