package my.assignments.repo;

import my.assignments.domain.CustomerImpl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CRUD operations on Customer object
 *
 * Created by chrissekaran on 09/02/15.
 */
public interface CustomerRepository extends JpaRepository<CustomerImpl, String> {
}
