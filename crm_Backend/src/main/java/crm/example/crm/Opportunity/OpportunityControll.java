package crm.example.crm.Opportunity;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityControll{

    private final OpportunityService opportunityService;

    public OpportunityControll(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping("/total")
    public int getTotalAmount() {
        return opportunityService.getTotalAmount();
    }

    @GetMapping("/pipeline")
    public int getPipelineTotal() {
        return opportunityService.getPipelineTotal();
    }

    @GetMapping("/committed")
    public int getCommittedTotal() {
        return opportunityService.getCommittedTotal();
    }

    @GetMapping("/due")
    public int getDueAmount() {
        return opportunityService.getDueAmount();
    }

    @GetMapping("/highest")
    public int getHighestOpportunity() {
        return opportunityService.getHighestOpportunity();
    }
    @GetMapping ("/all")
    public List<Opportunity> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }
}