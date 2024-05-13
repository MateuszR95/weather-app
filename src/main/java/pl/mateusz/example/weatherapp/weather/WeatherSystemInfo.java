package pl.mateusz.example.weatherapp.weather;

import lombok.Data;

@Data
class WeatherSystemInfo {

    private long type;
    private long id;
    private String country;
    private long sunrise;
    private long sunset;

}
