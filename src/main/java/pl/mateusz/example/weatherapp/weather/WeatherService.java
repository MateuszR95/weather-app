package pl.mateusz.example.weatherapp.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mateusz.example.weatherapp.exception.CityNotFoundException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WeatherService {
    private final String appId;

    public WeatherService(@Value("${weather.app-id}") String appId) {
        this.appId = appId;
    }

    public WeatherDetailsDisplayDto getWeatherForCity(String cityName) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                    "&appid=" + appId + "&lang=pl&units=metric";
            WeatherDetailsResponse weatherDetailsResponse = restTemplate.getForObject(url, WeatherDetailsResponse.class);
            return convertToWeatherDetailsDisplayDto(weatherDetailsResponse);
        } catch (Exception e) {
            throw new CityNotFoundException();
        }

    }

    private WeatherDetailsDisplayDto convertToWeatherDetailsDisplayDto(WeatherDetailsResponse weatherDetailsResponse) {
        WeatherDetailsDisplayDto dto = new WeatherDetailsDisplayDto();
        dto.setDescription(weatherDetailsResponse.getWeather()[0].getDescription());
        dto.setTemperature(weatherDetailsResponse.getMain().getTemp());
        dto.setPerceivedTemperature(weatherDetailsResponse.getMain().getFeels_like());
        dto.setMinTemperature(weatherDetailsResponse.getMain().getTemp_min());
        dto.setMaxTemperature(weatherDetailsResponse.getMain().getTemp_max());
        dto.setPressure(weatherDetailsResponse.getMain().getPressure());
        dto.setHumidity(weatherDetailsResponse.getMain().getHumidity());
        dto.setVisibility(weatherDetailsResponse.getVisibility());
        dto.setWindSpeed(weatherDetailsResponse.getWind().getSpeed());
        dto.setTimezone(weatherDetailsResponse.getTimezone());

        long sunriseTimestamp = weatherDetailsResponse.getSys().getSunrise();
        LocalDateTime sunriseDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseTimestamp),
                ZoneOffset.ofTotalSeconds((int) weatherDetailsResponse.getTimezone()));
        dto.setSunrise(sunriseDateTime);

        long sunsetTimestamp = weatherDetailsResponse.getSys().getSunset();
        LocalDateTime sunsetDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetTimestamp),
                ZoneOffset.ofTotalSeconds((int) weatherDetailsResponse.getTimezone()));
        dto.setSunset(sunsetDateTime);

        return dto;
    }


}
