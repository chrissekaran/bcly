package my.assignments.domain;

import com.google.gson.JsonObject;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrissekaran on 05/02/15.
 */
@Entity
@Table(name = "Customer")
public class CustomerImpl {//implements Customer {

    @Id
    @NotNull
    @Length(min = 1, max = 100)
    private String id;

    @Column
    @NotNull
    private String name;


    @OneToMany(targetEntity = BankAccountImpl.class, cascade = CascadeType.ALL)
    private List<BankAccountImpl> bankAccounts = new ArrayList<>();

    public CustomerImpl() {
    }

    public CustomerImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BankAccountImpl> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountImpl> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void addBankAccount(BankAccountImpl bankAccount) {
        bankAccounts.add(bankAccount);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerImpl customer = (CustomerImpl) o;

        if (!id.equals(customer.id)) return false;
        if (!name.equals(customer.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        //return new Gson().toJson(this);
        //return new Gson().toJson(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", name);
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        bankAccounts.forEach(b -> sb.append(b.getId()+", "));
        sb.append("]");
        jsonObject.addProperty("bankAccounts", sb.toString());
        
        return jsonObject.toString();

    }
}
