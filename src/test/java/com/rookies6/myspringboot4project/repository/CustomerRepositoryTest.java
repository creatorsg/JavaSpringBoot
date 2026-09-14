package com.rookies6.myspringboot4project.repository;

import com.rookies6.myspringboot4project.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCreate(){
        //Given(준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("A006");
        customer.setCustomerName("둘리");
        //When(실행단계)
        Customer addCustomer = customerRepository.save(customer);
        //Then(검증단계)
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("둘리");
    }

    @Test
    void TestFindBy() {
        Optional<Customer> optionalCustomer = customerRepository.findById(1L);
        if(optionalCustomer.isPresent()){
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getId()).isEqualTo(1L);
        }

        optionalCustomer.ifPresent(customer -> System.out.println(customer.getCustomerName()));
        optionalCustomer.ifPresent(System.out::println);
    }

    @Test
    void testFindByNotFound() {
        Customer notFoundCustomer = customerRepository.findByCustomerId("B001")
                .orElseGet(() -> new Customer());
//        assertThat(notFoundCustomer.getCustomerId()).isEqualTo("A004");
        assertThat(notFoundCustomer.getCustomerId()).isNull();

        Customer notFound = customerRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }
}