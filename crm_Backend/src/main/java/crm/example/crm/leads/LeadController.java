package crm.example.crm.leads;
import java.util.*;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }
    @PostMapping
    public Lead createLead(@RequestBody Lead lead){
        return leadService.createLead(lead);

    }
    @GetMapping
    public List<Lead> getAllLeads() {
    return leadService.getAllLeads();
}
@PutMapping("/{id}")
public Lead updateLead(@PathVariable Long id, @RequestBody Lead lead) {
    return leadService.updateLead(id, lead);
    }
 
}
