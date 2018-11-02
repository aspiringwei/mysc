package no.lwb.mysc.servicedemo.mybatis.mapper;

import no.lwb.mysc.servicedemo.mybatis.domain.City;
import org.apache.ibatis.annotations.*;

/**
 * @author WeiBin Lin
 */
@Mapper
public interface CityMapper {

    @Select("select * from city where state = #{state}")
    City findByState(@Param("state") String state);

    @Insert("insert into city(name,state,country) values(#{name}, #{state}, #{country})")
    int insertCity(City city);

    @Delete("delete from city where state = #{state}")
    int deleteCityByState(String state);

}
