package pl.mateusz.example.weatherapp.model;

import lombok.Data;

@Data
public class WeatherInfo {

    private long id;
    private String main;
    private String description;
    private String icon;

}
