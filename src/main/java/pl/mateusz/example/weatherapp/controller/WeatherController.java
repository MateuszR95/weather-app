package pl.mateusz.example.weatherapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.example.weatherapp.exception.CityNotFoundException;
import pl.mateusz.example.weatherapp.weather.WeatherDetailsDisplayDto;
import pl.mateusz.example.weatherapp.weather.WeatherService;

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

    @GetMapping("/weather")
    public String getWeather(Model model, @RequestParam(required = false) String cityName) {
        try {
            WeatherDetailsDisplayDto weatherDto = weatherService.getWeatherForCity(cityName);
            model.addAttribute("cityName", cityName.substring(0, 1).toUpperCase() +
                    cityName.substring(1).toLowerCase());
            model.addAttribute("weather", weatherDto);
            return "weather";
        } catch (CityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }
}