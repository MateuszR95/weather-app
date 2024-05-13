package pl.mateusz.example.weatherapp.weather;

import lombok.Data;

@Data
class WeatherInfo {

    private long id;
    private String main;
    private String description;
    private String icon;

}
