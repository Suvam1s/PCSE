package crm.example.crm.Accounts;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Accountsrepo extends JpaRepository<Accounts, Long> {
    List<Accounts> findByName(String name);
    
}