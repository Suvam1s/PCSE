package crm.example.crm.Accounts;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountsControll {

    private final AccountsService accountservice;

    public AccountsControll(AccountsService accountservice) {
        this.accountservice = accountservice;
    }
@GetMapping
public List<Accounts> getAllAccounts() {
    return accountservice.getAllAccounts();
}

    @GetMapping("/{id}")
    public Optional<Accounts> searchById(@PathVariable long id) {
        return accountservice.searchById(id);
    }

    @GetMapping("/name/{name}")
    public List<Accounts> searchByName(@PathVariable String name) {
        return accountservice.searchByName(name);
    }

    @PostMapping("/add_account")
    public Accounts addAccount(
            @RequestParam String name,
            @RequestParam String billing_address,
            @RequestParam long phone,
            @RequestParam Prospect pros) {

        return accountservice.addAccount(
                name,
                billing_address,
                phone,
                pros
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountservice.deleteAccount(id);
    }
}