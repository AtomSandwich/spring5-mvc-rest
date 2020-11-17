package guru.springfamework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springfamework.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
