package pl.mateusz.example.weatherapp.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super("Nie znaleziono wskazanego miasta");
    }
}
