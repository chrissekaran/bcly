package my.assignments.service;

import com.google.common.eventbus.EventBus;
import my.assignments.domain.BankAccount;
import my.assignments.domain.BankAccountImpl;
import my.assignments.domain.Customer;
import my.assignments.domain.CustomerImpl;
import my.assignments.repo.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * A data access object that does the plumbing between Persistence and Event publishing.
 *  
 * Created by chrissekaran on 05/02/15.
 */
@Service
@DependsOn({"eventBus", "customerRepository"})
public class DataService implements BankAccount, Customer, InitializingBean {

    private static final Map<String, CustomerImpl> customerMap = new HashMap<>();
    
    @Autowired
    private CustomerRepository customerRepository;
    
    static  {
        customerMap.put("101", new CustomerImpl("101", "George Harrison"));
        customerMap.put("102", new CustomerImpl("102", "Ringo Starr"));
        customerMap.put("103", new CustomerImpl("103", "Paul McCartney"));
    }

    @Autowired
    private EventBus eventBus;

    public CustomerImpl customerById(String id) {
        return customerRepository.findOne(id);
    }

    public CustomerImpl addAccount(BankAccountImpl bankAccount) {
        //CustomerImpl customer = customerMap.get(bankAccount.getOwningCustomer().getID());
        CustomerImpl customer  = customerRepository.findOne(bankAccount.getOwningCustomer().getID());
        customer.addBankAccount(bankAccount);
        customerRepository.saveAndFlush(customer);
        eventBus.post(bankAccount);
        return customer;
    }


    @Override
    public String getId() {
        return null;
    }

    @Override
    public List<String> getAlternativeIds() {
        return null;
    }

    @Override
    public Customer getOwningCustomer() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public List<BankAccount> getAccounts() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    /*
     *Sample data to be loaded on start-up
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        customerRepository.saveAndFlush(new CustomerImpl("1001", "George Harrison1-JPA"));
        customerRepository.saveAndFlush(new CustomerImpl("1002", "George Harrison2-JPA"));
        customerRepository.saveAndFlush(new CustomerImpl("1003", "George Harrison3-JPA"));
        customerRepository.saveAndFlush(new CustomerImpl("1004", "George Harrison4-JPA"));
    }

}
