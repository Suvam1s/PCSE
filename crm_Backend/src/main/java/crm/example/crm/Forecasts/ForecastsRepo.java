package crm.example.crm.Forecasts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastsRepo extends JpaRepository<Forecasts, Long> {
    boolean existsByAccountNameIgnoreCaseAndOpportunityNameIgnoreCase(
            String accountName,
            String opportunityName);

    boolean existsByAccountNameIgnoreCaseAndOpportunityNameIgnoreCaseAndIdNot(
            String accountName,
            String opportunityName,
            Long id);
}
