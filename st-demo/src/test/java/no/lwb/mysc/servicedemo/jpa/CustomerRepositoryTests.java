package no.lwb.mysc.servicedemo.jpa;

import no.lwb.mysc.servicedemo.repository.Customer;
import no.lwb.mysc.servicedemo.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author WeiBin Lin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void createSimpleCustomer() {

        Customer customer = new Customer();
        customer.setDob(LocalDate.of(1904, 5, 14));
        customer.setFirstName("Albert");
        Customer saved = customerRepo.save(customer);

        assertThat(saved.getId()).isNotNull();

        customer.setFirstName("Hans Albert");
        saved = customerRepo.save(customer);

        Optional<Customer> reloaded = customerRepo.findById(saved.getId());

        boolean flag = reloaded.isPresent();
        if (flag) {
            assertThat(reloaded.get().getFirstName()).isEqualTo("Hans Albert");
        }

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
    }
}