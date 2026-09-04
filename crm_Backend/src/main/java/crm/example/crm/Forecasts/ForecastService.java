package crm.example.crm.Forecasts;
import  java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import crm.example.crm.Accounts.Accounts;
import crm.example.crm.Accounts.Accountsrepo;

@Service
public class ForecastService {

    private final ForecastsRepo forecastsrepo;
    private final Accountsrepo accountsrepository1;

    public ForecastService(ForecastsRepo forecastsrepo, Accountsrepo accountsrepository1) {
        this.forecastsrepo = forecastsrepo;
        this.accountsrepository1 = accountsrepository1;
    }
      
    //SERVICE 1
    public Forecasts addForecasts(
            String OpportunityName,
            String AccountName,
            stage Stage,
            Long amount,
            LocalDate closeDate) {

    Optional<Accounts> account = accountsrepository1.findByName(AccountName);

        if (account.isEmpty()) {
            throw new IllegalArgumentException("name not found");
        }


        if (forecastsrepo.existsByAccountAndOpportunityName(account.get(), OpportunityName)) {
            throw new IllegalArgumentException("Opportunity already exists for this account");
        } 

        Forecasts forecast = new Forecasts(
                OpportunityName,
                account.get().getName(),
                Stage,
                amount,
                closeDate
        );

        return forecastsrepo.save(forecast);
    }
}