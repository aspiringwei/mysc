package no.lwb.mysc.servicedemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ixm.
 * @date 2018/8/13
 */
@Data
@AllArgsConstructor
public class CustomErrorType implements Serializable {

    private int status;

    private String errorMessage;
}
