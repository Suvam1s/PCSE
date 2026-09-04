package crm.example.crm.Accounts;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AccountsService{
    private final Accountsrepo accountsrepository;
       public AccountsService(Accountsrepo accountsrepository) {
        this.accountsrepository = accountsrepository;
    }
    public List<Accounts> getAllAccounts(){
        return accountsrepository.findAll();
    }
   
    public Optional<Accounts> searchById(long id) {
        return accountsrepository.findById(id);
    }

   
    public Optional<Accounts> searchByName(String name) {
        return accountsrepository.findByName (name);
    }

   
    public Accounts addAccount(String name, String billing_address, long phone, Prospect pros) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Account name is mandatory.");
        }

        if (billing_address == null || billing_address.trim().isEmpty()) {
            throw new IllegalArgumentException("Billing address is mandatory.");
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
                billing_address,
                phone,
                pros
        );

        return accountsrepository.save(account);
    }

  
    public void deleteAccount(long id) {

        if (!accountsrepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Account with ID " + id + " does not exist."
            );
        }

        accountsrepository.deleteById(id);
    }
}