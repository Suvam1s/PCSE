package crm.example.crm.Forecasts;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import crm.example.crm.Accounts.Accounts;
import crm.example.crm.Accounts.Accountsrepo;

@Service
public class ForecastService {
    private final ForecastsRepo forecastsRepo;
    private final Accountsrepo accountsRepository;

    public ForecastService(ForecastsRepo forecastsRepo, Accountsrepo accountsRepository) {
        this.forecastsRepo = forecastsRepo;
        this.accountsRepository = accountsRepository;
    }

    public List<Forecasts> getAllForecasts() {
        return forecastsRepo.findAll();
    }

    public Forecasts getForecast(long id) {
        return forecastsRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Forecast not found: " + id));
    }

    public Forecasts addForecasts(
            String opportunityName,
            Long accountId,
            String accountName,
            ForecastStage stage,
            Long amount,
            LocalDate closeDate,
            double probability) {
        validateForecast(opportunityName, stage, amount, closeDate, probability);
        Accounts account = resolveAccount(accountId, accountName);
        String normalizedOpportunityName = opportunityName.trim();

        if (forecastsRepo.existsByAccountNameIgnoreCaseAndOpportunityNameIgnoreCase(
                account.getName(), normalizedOpportunityName)) {
            throw new IllegalStateException("Opportunity already exists for this account");
        }

        Forecasts forecast = new Forecasts(
                normalizedOpportunityName,
                account.getName(),
                stage,
                amount,
                closeDate,
                probability);
        return forecastsRepo.save(forecast);
    }

    public Forecasts updateForecast(
            long id,
            String opportunityName,
            Long accountId,
            String accountName,
            ForecastStage stage,
            Long amount,
            LocalDate closeDate,
            double probability) {
        validateForecast(opportunityName, stage, amount, closeDate, probability);
        Forecasts forecast = getForecast(id);
        Accounts account = resolveAccount(accountId, accountName);
        String normalizedOpportunityName = opportunityName.trim();

        if (forecastsRepo.existsByAccountNameIgnoreCaseAndOpportunityNameIgnoreCaseAndIdNot(
                account.getName(), normalizedOpportunityName, id)) {
            throw new IllegalStateException("Opportunity already exists for this account");
        }

        forecast.setOpportunityName(normalizedOpportunityName);
        forecast.setAccountName(account.getName());
        forecast.setStage(stage);
        forecast.setAmount(amount);
        forecast.setCloseDate(closeDate);
        forecast.setProbability(probability);
        return forecastsRepo.save(forecast);
    }

    public void deleteForecast(long id) {
        Forecasts forecast = getForecast(id);
        forecastsRepo.delete(forecast);
    }

    private Accounts resolveAccount(Long accountId, String accountName) {
        if (accountId != null) {
            return accountsRepository.findById(accountId)
                    .orElseThrow(() -> new NoSuchElementException("Account not found: " + accountId));
        }
        if (accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("accountId or accountName is required");
        }
        return accountsRepository.findByName(accountName.trim())
                .orElseThrow(() -> new NoSuchElementException("Account not found: " + accountName.trim()));
    }

    private void validateForecast(
            String opportunityName,
            ForecastStage stage,
            Long amount,
            LocalDate closeDate,
            double probability) {
        if (opportunityName == null || opportunityName.isBlank()) {
            throw new IllegalArgumentException("opportunityName is required");
        }
        if (stage == null) {
            throw new IllegalArgumentException("stage is required");
        }
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("amount must be zero or greater");
        }
        if (closeDate == null) {
            throw new IllegalArgumentException("closeDate is required");
        }
        if (!Double.isFinite(probability) || probability < 0 || probability > 100) {
            throw new IllegalArgumentException("probability must be between 0 and 100");
        }
    }
}
