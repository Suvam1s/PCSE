package crm.example.crm.Forecasts;

import  java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/forecasts")
public class ForecastsController{
    private final ForecastService forecastService;
    public ForecastsController(ForecastService forecastService) {
    this.forecastService = forecastService;
    }
   @PostMapping("/add_Forecast")
public Forecasts addForecasts(
        @RequestParam String OpportunityName,
        @RequestParam String AccountName,
        @RequestParam stage Stage,
        @RequestParam Long amount,
        @RequestParam LocalDate closeDate) {

    return forecastService.addForecasts(
            OpportunityName,
            AccountName,
            Stage,
            amount,
            closeDate
    );
    }
}