package crm.example.crm.Accounts;
import java.util.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
enum Prospect{
    Analyst,Competitor
,Customer
,Integrator
,Investor
,Partner

}
@Entity
@Table(name= "Accounts")
public class Accounts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String Billing_State;
    private int phone;
    @Enumerated(EnumType.STRING)
    private Prospect pros;
    public Accounts(){
    }
    public Accounts(String name, String Billing_State, int phone,Prospect pros){
        this.name= name;
        this.Billing_State= Billing_State;
        this.phone= phone;
        this.pros= pros;
    }
    public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getBilling_State() {
    return Billing_State;
}

public void setBilling_State(String Billing_State) {
    this.Billing_State = Billing_State;
}

public int getPhone() {
    return phone;
}

public void setPhone(int phone) {
    this.phone = phone;
}

public Prospect getPros() {
    return pros;
}

public void setPros(Prospect pros) {
    this.pros = pros;
}
}