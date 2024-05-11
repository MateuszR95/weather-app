package pl.mateusz.example.weatherapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.example.weatherapp.dto.WeatherDetailsDisplayDto;
import pl.mateusz.example.weatherapp.model.WeatherDetailsResponse;
import pl.mateusz.example.weatherapp.exceptions.CityNotFoundException;
import pl.mateusz.example.weatherapp.service.WeatherService;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/weather")
    public String getWeather(Model model, String cityName) {
        try {
            WeatherDetailsResponse weatherForCity = weatherService.getWeatherForCity(cityName);
            WeatherDetailsDisplayDto weatherDto = weatherService.convertToWeatherDetailsDisplayDto(weatherForCity);
            model.addAttribute("cityName", cityName);
            model.addAttribute("weather", weatherDto);
            return "weather";
        } catch (CityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }
}