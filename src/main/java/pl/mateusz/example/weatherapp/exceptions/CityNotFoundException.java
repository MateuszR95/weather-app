package pl.mateusz.example.weatherapp.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super("Nie znaleziono wskazanego miasta");
    }
}
