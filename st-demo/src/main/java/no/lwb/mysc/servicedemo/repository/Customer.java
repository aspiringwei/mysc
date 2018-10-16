package no.lwb.mysc.servicedemo.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * entity
 * Note that you don’t need getters or setters
 * @author WeiBin Lin
 */
@Data
public class Customer {

    @Id
    Long id;

    String firstName;

    LocalDate dob;
}