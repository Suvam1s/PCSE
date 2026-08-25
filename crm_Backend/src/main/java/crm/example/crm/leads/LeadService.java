package crm.example.crm.leads;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
@Service
public class LeadService {
    private final LeadsRepo leadsRepo;
    public LeadService(LeadsRepo leadsRepo) {
        this.leadsRepo = leadsRepo;
    }
    public Lead createLead(Lead lead){
        lead.setCreatedAt(LocalDateTime.now());
        lead.setUpdatedAt(LocalDateTime.now());

    return leadsRepo.save(lead);
    }
    public List<Lead> getAllLeads() {
    return leadsRepo.findAll();
}
public Lead updateLead(Long id, Lead lead) {
    Lead existingLead = leadsRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Lead not found"));

    existingLead.setName(lead.getName());
    existingLead.setEmail(lead.getEmail());
    existingLead.setPhone(lead.getPhone());
    existingLead.setCompany(lead.getCompany());
    existingLead.setStatus(lead.getStatus());

    return leadsRepo.save(existingLead);
}
    @GetMapping("/new")
    public List<Lead> getNewLeads() {
    return leadsRepo.findByStatus(LeadStatus.New);
}

@GetMapping("/contacted")
public List<Lead> getContactedLeads() {
    return leadsRepo.findByStatus(LeadStatus.CONTACTED);
}
@GetMapping("/qualified")
public List<Lead> getQualifiedLeads() {
    return leadsRepo.findByStatus(LeadStatus.QUALIFIED);
}
}
