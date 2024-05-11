package pl.mateusz.example.weatherapp.model;

import lombok.Data;

@Data
public class WeatherSystemInfo {

    private long type;
    private long id;
    private String country;
    private long sunrise;
    private long sunset;

}
