package no.lwb.mysc.servicedemo.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String age;

    private String name;

    private Integer id;


}
