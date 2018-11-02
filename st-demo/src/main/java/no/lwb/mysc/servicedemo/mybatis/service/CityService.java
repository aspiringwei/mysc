package no.lwb.mysc.servicedemo.mybatis.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import no.lwb.mysc.servicedemo.mybatis.domain.City;
import no.lwb.mysc.servicedemo.mybatis.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author WeiBin Lin
 */
@Service
@Log4j2
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int insert(City city) {
        int count = cityMapper.insertCity(city);
        log.info("updates:{}", count);
        return count;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int delete(String state) {
        return cityMapper.deleteCityByState(state);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int insertNew(City city) {
        int count = cityMapper.insertCity(city);
        log.info("updates:{}", count);
        return count;
    }
}
