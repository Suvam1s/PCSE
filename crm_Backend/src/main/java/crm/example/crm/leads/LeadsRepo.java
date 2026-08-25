package crm.example.crm.leads;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadsRepo extends JpaRepository<Lead, Long> {
    List<Lead> findByStatus(LeadStatus status);
}
