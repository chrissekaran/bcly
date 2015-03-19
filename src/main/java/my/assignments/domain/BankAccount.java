package my.assignments.domain;

import java.util.List;

/**
 * Created by chrissekaran on 04/02/15.
 */
public interface BankAccount {
    
    //Primary ID of this BankAccount
    String getId();
    
    //List of alternative ID's for this BankAccount
    List<String> getAlternativeIds();
    
    //The customer that owns this BankAccount
    Customer getOwningCustomer();
    
}
