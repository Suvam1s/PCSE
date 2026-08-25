package crm.example.crm.Opportunity;
import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
enum Oppstage{
    PROSPECTING,
    QUALIFICATION,
    NEEDS_ANALYSIS,
    VALUE_PROPOSITION,
}
enum Oppstatus{
    PIPELINE,
    COMMITTED,
}
@Entity
@Table(name = "opportunities")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String AccountName;
    private int Amount;
    private LocalDate closeDate;
   private String RecentActivity;
    @Enumerated(EnumType.STRING)
    private Oppstage stage;
    @Enumerated(EnumType.STRING)
    private Oppstatus status;
    public Opportunity() {
    }
    public Opportunity(
            String accountName,
            int amount,
            LocalDate closeDate,
            String recentActivity,
            Oppstage stage,
            Oppstatus status) {

        this.AccountName = accountName;
        this.Amount = amount;
        this.closeDate = closeDate;
        this.RecentActivity = recentActivity;
        this.stage = stage;
        this.status = status;
            }

            public String getAccountName() {
    return AccountName;
}

public void setAccountName(String accountName) {
    this.AccountName = accountName;
}

public int getAmount() {
    return Amount;
}

public void setAmount(int amount) {
    this.Amount = amount;
}

public LocalDate getCloseDate() {
    return closeDate;
}

public void setCloseDate(LocalDate closeDate) {
    this.closeDate = closeDate;
}

public String getRecentActivity() {
    return RecentActivity;
}

public void setRecentActivity(String recentActivity) {
    this.RecentActivity = recentActivity;
}

public Oppstage getStage() {
    return stage;
}

public void setStage(Oppstage stage) {
    this.stage = stage;
}

public Oppstatus getStatus() {
    return status;
}

public void setStatus(Oppstatus status) {
    this.status = status;
}
}
