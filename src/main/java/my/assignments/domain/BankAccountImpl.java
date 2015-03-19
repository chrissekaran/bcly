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
@Table(name = "BankAccount")
public class BankAccountImpl {//implements BankAccount {

    @Id
    @NotNull
    @Length(min = 1, max = 40)
    private String id;

    //@Column
    //@NotNull
    //@Length(min = 1, max = 100)
    @Transient
    private List<String> alternativeIds = new ArrayList<>();

    @ManyToOne()
    private CustomerImpl owningCustomer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAlternativeIds() {
        return alternativeIds;
    }

    public void setAlternativeIds(List<String> alternativeIds) {
        this.alternativeIds = alternativeIds;
    }

    public CustomerImpl getOwningCustomer() {
        return owningCustomer;
    }

    public void setOwningCustomer(CustomerImpl owningCustomer) {
        this.owningCustomer = owningCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccountImpl that = (BankAccountImpl) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        return result;
    }

    /**
     * Overriding the Default toString with Gson to reflect only an id's based version to avoid a cyclic dependency between BankAccount and Customer
     * @return
     */
    @Override
    public String toString() {
        //return new Gson().toJson(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        //jsonObject.addProperty("owningCustomer", owningCustomer.getID());
        return jsonObject.toString();
    }
}
