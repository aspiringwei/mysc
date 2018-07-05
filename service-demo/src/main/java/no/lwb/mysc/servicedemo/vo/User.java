package no.lwb.mysc.servicedemo.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class User implements Serializable {

    @Getter
    @Setter
    private String age;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer id;


}
