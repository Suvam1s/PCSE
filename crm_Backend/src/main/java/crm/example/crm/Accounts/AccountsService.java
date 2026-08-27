package crm.example.crm.Opportunity;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class AccountService{
    private final Accountsrepo accountsrepo;
       public AccountsService(AccountsRepository accountsrepository) {
        this.accountsrepository = accountsrepository;
    }

    // Search account by ID
    public Optional<Accounts> searchById(long id) {
        return accountsrepository.findById(id);
    }

    // Search accounts by name
    public List<Accounts> searchByName(String name) {
        return accountsrepository.findByName(name);
    }

    // Add a new account
    public Accounts addAccount(String name, String Billing_State, int phone, Prospect pros) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Account name is mandatory.");
        }

        if (Billing_State == null || Billing_State.trim().isEmpty()) {
            throw new IllegalArgumentException("Billing State is mandatory.");
        }

        if (phone <= 0) {
            throw new IllegalArgumentException("Phone number is mandatory.");
        }

        if (pros == null) {
            throw new IllegalArgumentException(
                    "Prospect type is mandatory. Options: Analyst, Competitor, Customer, Integrator, Investor, Partner"
            );
        }

        Accounts account = new Accounts(
                name,
                Billing_State,
                phone,
                pros
        );

        return accountsRepository.save(account);
    }

    // Delete account by ID
    public void deleteAccount(long id) {

        if (!accountsRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Account with ID " + id + " does not exist."
            );
        }

        accountsRepository.deleteById(id);
    }
}