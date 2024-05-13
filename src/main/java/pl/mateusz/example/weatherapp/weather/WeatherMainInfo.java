package pl.mateusz.example.weatherapp.weather;

import lombok.Data;

@Data
class WeatherMainInfo {

    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private long pressure;
    private long humidity;

}
