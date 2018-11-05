package no.lwb.mysc.servicedemo.mybatis.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WeiBin Lin
 */
@Data
public class City implements Serializable {

    private Long id;

    private String name;

    private String state;

    private String country;
}
