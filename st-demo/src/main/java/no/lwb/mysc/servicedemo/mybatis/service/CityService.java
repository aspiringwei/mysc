package no.lwb.mysc.servicedemo.mybatis.service;

import no.lwb.mysc.servicedemo.mybatis.domain.City;

/**
 * @author WeiBin Lin
 */
public interface CityService {


    int insert(City city);

    int delete(String state);

    int insertNew(City city);

    int insertNested(City city);

    City get(City city);

}
