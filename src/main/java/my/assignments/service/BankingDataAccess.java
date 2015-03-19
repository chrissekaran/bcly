package my.assignments.service;

import my.assignments.domain.BankAccountImpl;
import my.assignments.domain.CustomerImpl;
import my.assignments.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A duplicate class that is not used now anymore
 *
 * Created by chrissekaran on 09/02/15.
 */
@Service
@Transactional
public class BankingDataAccess {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public CustomerImpl fetchCustomerById(String customerId) {
        return customerRepository.findOne(customerId);
    }
    
    public void saveCustomer(CustomerImpl cusomter) {
        customerRepository.saveAndFlush(cusomter);
    }
    
    public void addBankAccountForCustomer(BankAccountImpl bankAccount)  {
        
            
    }
    
    public int $() {
        int x = 2003;
        return x;
        
    }
}
