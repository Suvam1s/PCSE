package crm.example.crm.Forecasts;
import java.util.*;
import jakarta.persistence.*;
enum stage{
    Qualification, NeedAnalysis, ClosedLost, ClosedWon, Proposal, Negotiation
}
@Entity
@Table(name = "Forecasts")
public class Forecasts(){
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String OpportunityName;
    @Enumerated(EnumType.STRING)
    private stage Stage;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Accounts account;
    private double probability;
    private LocalDate closeDate;
    public Forecasts()
    {}   
       public Forecasts(String OpportunityName, stage Stage, LocalDate closeDate){
        this.OpportunityName= OpportunityName;
        this.Stage= Stage;
        this.closeDate= closeDate;
    }
    public Accounts getacccounts(){
        return account;
    }
    public void setAccounts(Accounts account){
        this.account= account;
    }

public long getId() {

    return id;
}

public void setId(long id) {
    this.id = id;
}

public String getOpportunityName() {
    return OpportunityName;
}

public void setOpportunityName(String OpportunityName) {
    this.OpportunityName = OpportunityName;
}

public stage getStage() {
    return Stage;
}

public void setStage(stage Stage) {
    this.Stage = Stage;
}

public Accounts getAccount() {
    return account;
}

public void setAccount(Accounts account) {
    this.account = account;
}

public double getProbability() {
    return probability;
}

public void setProbability(double probability) {
    this.probability = probability;
}


}