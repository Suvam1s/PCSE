package crm.example.crm.Forecasts;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forecast")
public class Forecasts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @Column(name = "accountname")
    private String accountName;

    @Column(name = "opportunityname")
    private String opportunityName;

    @Enumerated(EnumType.STRING)
    private ForecastStage stage;

    private double probability;

    @Column(name = "closedate")
    private LocalDate closeDate;

    public Forecasts() {
    }

    public Forecasts(
            String opportunityName,
            String accountName,
            ForecastStage stage,
            Long amount,
            LocalDate closeDate,
            double probability) {
        this.opportunityName = opportunityName;
        this.accountName = accountName;
        this.stage = stage;
        this.amount = amount;
        this.closeDate = closeDate;
        this.probability = probability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOpportunityName() {
        return opportunityName;
    }

    public void setOpportunityName(String opportunityName) {
        this.opportunityName = opportunityName;
    }

    public ForecastStage getStage() {
        return stage;
    }

    public void setStage(ForecastStage stage) {
        this.stage = stage;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }
}
