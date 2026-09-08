package crm.example.crm.Forecasts;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forecasts")
public class ForecastsController {
    private final ForecastService forecastService;

    public ForecastsController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping({"", "/all"})
    public List<Forecasts> getAllForecasts() {
        return forecastService.getAllForecasts();
    }

    @GetMapping("/{id}")
    public Forecasts getForecast(@PathVariable long id) {
        return forecastService.getForecast(id);
    }

    @PostMapping({"", "/add_Forecast"})
    public ResponseEntity<Forecasts> addForecast(
            @RequestParam(name = "opportunityName", required = false) String opportunityName,
            @RequestParam(name = "OpportunityName", required = false) String legacyOpportunityName,
            @RequestParam(name = "accountId", required = false) Long accountId,
            @RequestParam(name = "accountName", required = false) String accountName,
            @RequestParam(name = "AccountName", required = false) String legacyAccountName,
            @RequestParam(name = "stage", required = false) ForecastStage stage,
            @RequestParam(name = "Stage", required = false) ForecastStage legacyStage,
            @RequestParam Long amount,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate closeDate,
            @RequestParam(defaultValue = "0") double probability) {
        Forecasts created = forecastService.addForecasts(
                firstNonBlank(opportunityName, legacyOpportunityName),
                accountId,
                firstNonBlank(accountName, legacyAccountName),
                stage != null ? stage : legacyStage,
                amount,
                closeDate,
                probability);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public Forecasts updateForecast(
            @PathVariable long id,
            @RequestParam String opportunityName,
            @RequestParam(name = "accountId", required = false) Long accountId,
            @RequestParam(name = "accountName", required = false) String accountName,
            @RequestParam ForecastStage stage,
            @RequestParam Long amount,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate closeDate,
            @RequestParam double probability) {
        return forecastService.updateForecast(
                id,
                opportunityName,
                accountId,
                accountName,
                stage,
                amount,
                closeDate,
                probability);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForecast(@PathVariable long id) {
        forecastService.deleteForecast(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleConflict(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException exception) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", exception.getMessage()));
    }

    private String firstNonBlank(String preferred, String fallback) {
        return preferred != null && !preferred.isBlank() ? preferred : fallback;
    }
}
