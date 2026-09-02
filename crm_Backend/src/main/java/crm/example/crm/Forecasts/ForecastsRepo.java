package crm.example.crm.Forecasts;
import java.util.*;

import org.springframework.data.jpa.repository.*;
public interface Accountsrepo extends JpaRepository<Accounts, Long> {
    boolean existsByAccountAndOpportunityName(Accounts account, String OpportunityName);
}
