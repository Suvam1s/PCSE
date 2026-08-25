package crm.example.crm.Opportunity;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class OpportunityService {
    private final Opportunityrepo opportunitiesRepo;
    public OpportunityService(Opportunityrepo opportunitiesRepo) {
        this.opportunitiesRepo = opportunitiesRepo;
    }
    public List<Opportunity> getAllOpportunities() {
        return opportunitiesRepo.findAll();
    }
    public int getPipelineTotal() {
        return opportunitiesRepo.findByStatus(Oppstatus.PIPELINE)
                .stream()
                .mapToInt(Opportunity::getAmount)
                .sum();
    }

    public int getCommittedTotal() {
        return opportunitiesRepo.findByStatus(Oppstatus.COMMITTED)
                .stream()
                .mapToInt(Opportunity::getAmount)
                .sum();
    }

    public int getTotalAmount() {
        return getPipelineTotal() + getCommittedTotal();
    }

    public int getDueAmount() {
        return getTotalAmount() - getCommittedTotal();
    }
    public int getHighestOpportunity() {

    int max = Integer.MIN_VALUE;

    for (int c : opportunitiesRepo.findAmount()) {
        if (c > max) {
            max = c;
        }
    }

    return max;
}
}
