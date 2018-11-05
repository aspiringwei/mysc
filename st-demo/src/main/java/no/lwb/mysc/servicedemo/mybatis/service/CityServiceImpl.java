package no.lwb.mysc.servicedemo.mybatis.service;

import lombok.extern.log4j.Log4j2;
import no.lwb.mysc.servicedemo.mybatis.domain.City;
import no.lwb.mysc.servicedemo.mybatis.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author WeiBin Lin
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public int insert(City city) {
        int count = cityMapper.insertCity(city);
        log.info("updates:{}", count);
        return count;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int delete(String state) {
        return cityMapper.deleteCityByState(state);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int insertNew(City city) {
        int count = cityMapper.insertCity(city);
        log.info("updates:{}", count);
        return count;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    @Override
    public int insertNested(City city) {
        int count = cityMapper.insertCity(city);
        log.info("updates:{}", count);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return count;
    }

    @Override
    public City get(City city) {
        return cityMapper.findByState(city.getState());
    }
}
