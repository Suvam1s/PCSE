package crm.example.crm.Accounts;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/accounts")
public class AccountsControll{
    private final AccountsService accountservice;
    public AccountsControll(AccountsService accountservice){
        this.accountservice= accountservice;
    }
        @GetMapping("/id")
    public Optional<Accounts> searchById(@PathVariable long id) {
        return accountsService.searchById(id);
    }

    // Search accounts by name
    @GetMapping("/name")
    public List<Accounts> searchByName(@PathVariable String name) {
        return accountsService.searchByName(name);
    }
}