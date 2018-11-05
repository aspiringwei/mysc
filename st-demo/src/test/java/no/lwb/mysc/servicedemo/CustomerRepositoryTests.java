package no.lwb.mysc.servicedemo;

import no.lwb.mysc.servicedemo.config.CustomerConfig;
import no.lwb.mysc.servicedemo.repository.Customer;
import no.lwb.mysc.servicedemo.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author WeiBin Lin
 */
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = CustomerConfig.class)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void createSimpleCustomer() {

        Customer customer = new Customer();
        customer.setDob(LocalDate.of(1904, 5, 14));
        customer.setFirstName("Albert");
        customer.setId(123L);
        Customer saved = customerRepo.save(customer);

        assertThat(saved.getId()).isNotNull();

        saved.setFirstName("Hans Albert");

        customerRepo.save(saved);

        Optional<Customer> reloaded = customerRepo.findById(saved.getId());

        assertThat(reloaded).isNotEmpty();

        assertThat(reloaded.get().getFirstName()).isEqualTo("Hans Albert");
    }

    @Test
    public void findByName() {

        Customer customer = new Customer();
        customer.setDob(LocalDate.of(1904, 5, 14));
        customer.setFirstName("Albert");

        Customer saved = customerRepo.save(customer);

        assertThat(saved.getId()).isNotNull();

        customer.setId(null);
        customer.setFirstName("Bertram");

        customerRepo.save(customer);

        customer.setId(null);
        customer.setFirstName("Beth");

        customerRepo.save(customer);

//        assertThat(customerRepo.findByName("bert")).hasSize(2);
    }
}