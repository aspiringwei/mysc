package no.lwb.mysc.servicedemo.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * entity
 * Note that you don’t need getters or setters
 * @author WeiBin Lin
 */
@Data
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    String firstName;

    LocalDate dob;
}
