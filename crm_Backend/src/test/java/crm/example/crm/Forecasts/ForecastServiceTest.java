package crm.example.crm.Forecasts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import crm.example.crm.Accounts.Accounts;
import crm.example.crm.Accounts.Accountsrepo;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {
    @Mock
    private ForecastsRepo forecastsRepo;

    @Mock
    private Accountsrepo accountsRepository;

    private ForecastService forecastService;

    @BeforeEach
    void setUp() {
        forecastService = new ForecastService(forecastsRepo, accountsRepository);
    }

    @Test
    void createsForecastUsingAccountIdAndKeepsAllResponseFields() {
        Accounts account = new Accounts();
        account.setId(7L);
        account.setName("Acme");
        when(accountsRepository.findById(7L)).thenReturn(Optional.of(account));
        when(forecastsRepo.existsByAccountNameIgnoreCaseAndOpportunityNameIgnoreCase("Acme", "Renewal"))
                .thenReturn(false);
        when(forecastsRepo.save(any(Forecasts.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Forecasts result = forecastService.addForecasts(
                " Renewal ",
                7L,
                null,
                ForecastStage.Negotiation,
                25_000L,
                LocalDate.of(2026, 10, 1),
                70);

        assertEquals("Renewal", result.getOpportunityName());
        assertEquals("Acme", result.getAccountName());
        assertEquals(25_000L, result.getAmount());
        assertEquals(LocalDate.of(2026, 10, 1), result.getCloseDate());
        assertEquals(ForecastStage.Negotiation, result.getStage());
        assertEquals(70, result.getProbability());
    }

    @Test
    void rejectsDuplicateOpportunityForTheSameAccount() {
        Accounts account = new Accounts();
        account.setName("Acme");
        when(accountsRepository.findByName("Acme")).thenReturn(Optional.of(account));
        when(forecastsRepo.existsByAccountNameIgnoreCaseAndOpportunityNameIgnoreCase("Acme", "Renewal"))
                .thenReturn(true);

        assertThrows(IllegalStateException.class, () -> forecastService.addForecasts(
                "Renewal",
                null,
                "Acme",
                ForecastStage.Proposal,
                10_000L,
                LocalDate.of(2026, 11, 1),
                50));

        verify(forecastsRepo, never()).save(any(Forecasts.class));
    }

    @Test
    void rejectsProbabilityOutsideThePercentageRange() {
        assertThrows(IllegalArgumentException.class, () -> forecastService.addForecasts(
                "Renewal",
                7L,
                null,
                ForecastStage.Proposal,
                10_000L,
                LocalDate.of(2026, 11, 1),
                101));

        verify(accountsRepository, never()).findById(anyLong());
    }

    @Test
    void updatesAllMutableFields() {
        Forecasts existing = new Forecasts(
                "Old opportunity",
                "Old account",
                ForecastStage.Qualification,
                1_000L,
                LocalDate.of(2026, 9, 1),
                10);
        Accounts account = new Accounts();
        account.setName("New account");

        when(forecastsRepo.findById(3L)).thenReturn(Optional.of(existing));
        when(accountsRepository.findByName("New account")).thenReturn(Optional.of(account));
        when(forecastsRepo.save(existing)).thenReturn(existing);

        forecastService.updateForecast(
                3L,
                "New opportunity",
                null,
                "New account",
                ForecastStage.ClosedWon,
                50_000L,
                LocalDate.of(2026, 12, 1),
                100);

        ArgumentCaptor<Forecasts> captor = ArgumentCaptor.forClass(Forecasts.class);
        verify(forecastsRepo).save(captor.capture());
        Forecasts result = captor.getValue();
        assertEquals("New opportunity", result.getOpportunityName());
        assertEquals("New account", result.getAccountName());
        assertEquals(ForecastStage.ClosedWon, result.getStage());
        assertEquals(50_000L, result.getAmount());
        assertEquals(LocalDate.of(2026, 12, 1), result.getCloseDate());
        assertEquals(100, result.getProbability());
    }
}
