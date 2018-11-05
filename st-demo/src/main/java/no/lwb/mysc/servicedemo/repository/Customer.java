package no.lwb.mysc.servicedemo.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * entity
 * Note that you don’t need getters or setters
 * @author WeiBin Lin
 */
@Data
public class Customer implements Serializable {

    @Id
    Long id;

    String firstName;

    LocalDate dob;
}
