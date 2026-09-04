
package crm.example.crm.Forecasts;
import org.springframework.data.jpa.repository.JpaRepository;

import  crm.example.crm.Accounts.Accounts;
public interface ForecastsRepo extends JpaRepository<Forecasts, Long> {
    boolean existsByAccountAndOpportunityName(Accounts account, String OpportunityName);
}
