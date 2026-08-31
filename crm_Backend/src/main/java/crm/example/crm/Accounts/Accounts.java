package crm.example.crm.Accounts;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

enum Prospect{
    Analyst,Competitor
,Customer
,Integrator
,Investor
,Partner

}
@Entity
@Table(name= "accounts")
public class Accounts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String billing_address;
    private long  phone;
    @Enumerated(EnumType.STRING)
    private Prospect prospect;
    public Accounts(){
    }
    public Accounts(String name, String billing_address, long phone,Prospect prospect){
        this.name= name;
        this.billing_address= billing_address;
        this.phone= phone;
        this.prospect= prospect;
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

public String getBilling_address() {
    return billing_address;
}

public void setBilling_address(String billing_address) {
    this.billing_address = billing_address;
}

public long getPhone() {
    return phone;
}

public void setPhone(long phone) {
    this.phone = phone;
}

public Prospect getProspect() {
    return prospect;
}

public void setProspect(Prospect prospect) {
    this.prospect = prospect;
}
}