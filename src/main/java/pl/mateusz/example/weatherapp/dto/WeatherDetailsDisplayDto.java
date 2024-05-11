package pl.mateusz.example.weatherapp.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherDetailsDisplayDto {

    private String description;
    private double temperature;
    private double perceivedTemperature;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;
    private double humidity;
    private long visibility;
    private double windSpeed;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private long timezone;

}
