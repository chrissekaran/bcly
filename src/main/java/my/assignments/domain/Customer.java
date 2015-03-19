package my.assignments.domain;

import java.util.List;

/**
 * Created by chrissekaran on 04/02/15.
 */
public interface Customer {
    
    //Primary ID of Customer
    String getID();
    
    //BankAccounts owned by this Customer 
    List<BankAccount> getAccounts();
    
    //Name of Customer
    String getName();
    
}
