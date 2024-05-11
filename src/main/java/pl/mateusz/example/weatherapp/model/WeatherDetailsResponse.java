package pl.mateusz.example.weatherapp.model;

import lombok.Data;
import java.util.Map;

@Data
public class WeatherDetailsResponse {

    private Map<String, Double> coord;
    private WeatherInfo[] weather;
    private String base;
    private WeatherMainInfo main;
    private long visibility;
    private Wind wind;
    private Map<String, Double> clouds;
    private long dt;
    private WeatherSystemInfo sys;
    private long timezone;
    private long id;
    private String name;
    private long cod;

}
