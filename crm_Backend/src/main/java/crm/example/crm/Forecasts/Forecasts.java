package crm.example.crm.Forecasts;
import  java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
enum stage{
    Qualification, NeedAnalysis, ClosedLost, ClosedWon, Proposal, Negotiation
}
@Entity
@Table(name = "forecast")
public class Forecasts{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private Long amount;
    @Column(name = "accountname")
    private String AccountName;
    @Column(name = "opportunityname")
    private String OpportunityName;
    @Enumerated(EnumType.STRING)
    private stage Stage;
  
   // private Accounts account;
    private double probability;
    @Column(name = "closedate")
    private LocalDate closeDate;
    public Forecasts()
    {}   
       public Forecasts(String OpportunityName,String AccountName, stage Stage, Long amount, LocalDate closeDate){
        this.OpportunityName= OpportunityName;
        this.AccountName= AccountName;
        this.Stage= Stage;
        this.amount= amount;
        this.closeDate= closeDate;
    }
//    public Accounts getacccounts(){
  //      return account;
    //}
    //public void setAccounts(Accounts account){
     //   this.account= account;
    //}

public long getId() {

    return id;
}

public void setId(long id) {
    this.id = id;
}
public String getAccountName() {

    return AccountName;
}

public void setAccountName(String AccountName) {
    this.AccountName =AccountName;
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

//public Accounts getAccount() {
//    return account;
//}

//public void setAccount(Accounts account) {
//    this.account = account;
//}

public double getProbability() {
    return probability;
}

public void setProbability(double probability) {
    this.probability = probability;
}


}