package crm.example.crm.Opportunity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface Opportunityrepo extends JpaRepository<Opportunity, Long> {
    List<Accounts> findByName(String name){
        @Query("SELECT a.name FROM Accounts a ")
    }
}