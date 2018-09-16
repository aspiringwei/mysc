package no.lwb.base;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created by ixm. on 2018/7/18
 */
@Data
@ToString(exclude = "firstName", callSuper = true)
@Builder
public class LombokTest {

    private String firstName;

    private String lastName;

    public static void main(String[] args) {
        LombokTest lombokTest = LombokTest.builder().firstName("first").lastName("last").build();
        System.out.println(lombokTest);
    }
}
