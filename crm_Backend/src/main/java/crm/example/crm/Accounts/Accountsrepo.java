package crm.example.crm.Accounts;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Accountsrepo extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByName(String name);
    
}