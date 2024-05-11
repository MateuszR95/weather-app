package pl.mateusz.example.weatherapp.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mateusz.example.weatherapp.dto.WeatherDetailsDisplayDto;
import pl.mateusz.example.weatherapp.model.WeatherDetailsResponse;
import pl.mateusz.example.weatherapp.exceptions.CityNotFoundException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WeatherService {

    private static final double KELVIN_TO_CELSIUS_FACTOR = 273.15;

    public WeatherDetailsResponse getWeatherForCity(String cityName) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                    "&appid=81e2d8db803359793ac07d009537bdaf&lang=pl";
            return restTemplate.getForObject(url, WeatherDetailsResponse.class);
        } catch (Exception e) {
            throw new CityNotFoundException();
        }

    }

    public WeatherDetailsDisplayDto convertToWeatherDetailsDisplayDto(WeatherDetailsResponse weatherDetailsResponse) {
        WeatherDetailsDisplayDto dto = new WeatherDetailsDisplayDto();
        dto.setDescription(weatherDetailsResponse.getWeather()[0].getDescription());
        double temperature = weatherDetailsResponse.getMain().getTemp();
        dto.setTemperature(temperature - KELVIN_TO_CELSIUS_FACTOR);
        double perceivedTemp = weatherDetailsResponse.getMain().getFeels_like();
        dto.setPerceivedTemperature(perceivedTemp - KELVIN_TO_CELSIUS_FACTOR);
        double tempMin = weatherDetailsResponse.getMain().getTemp_min();
        dto.setMinTemperature(tempMin - KELVIN_TO_CELSIUS_FACTOR);
        double tempMax = weatherDetailsResponse.getMain().getTemp_max();

        dto.setMaxTemperature(tempMax - KELVIN_TO_CELSIUS_FACTOR);
        dto.setPressure(weatherDetailsResponse.getMain().getPressure());
        dto.setHumidity(weatherDetailsResponse.getMain().getHumidity());
        dto.setVisibility(weatherDetailsResponse.getVisibility());
        dto.setWindSpeed(weatherDetailsResponse.getWind().getSpeed());
        dto.setTimezone(weatherDetailsResponse.getTimezone());

        long sunriseTimestamp = weatherDetailsResponse.getSys().getSunrise();
        LocalDateTime sunriseDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseTimestamp),
                ZoneOffset.ofTotalSeconds((int)weatherDetailsResponse.getTimezone()));
        dto.setSunrise(sunriseDateTime);

        long sunsetTimestamp = weatherDetailsResponse.getSys().getSunset();
        LocalDateTime sunsetDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetTimestamp),
                ZoneOffset.ofTotalSeconds((int)weatherDetailsResponse.getTimezone()));
        dto.setSunset(sunsetDateTime);

        return dto;
    }


}
