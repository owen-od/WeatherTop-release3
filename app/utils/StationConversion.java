package utils;

import models.Reading;
import models.Station;
import play.Logger;

import java.util.HashMap;
import java.util.List;

public class StationConversion {
    public static HashMap<Integer, String> weatherConditions = new HashMap<Integer, String>();
    public static HashMap<Integer, String> weatherIcons = new HashMap<Integer, String>();

    /**
     * a method to convert the weather code to a String of the current
     * weather conditions corresponding to an icon in fomantic ui
     *
     * @param weatherCode for current conditions
     * @return description of current weather conditions corresponding to icon in fomantic ui
     */
    public static String codeToWeatherConditions(Integer weatherCode) {
        fillWeatherConditions();
        String weather = weatherConditions.get(weatherCode);
        return weather;
    }

    /**
     * a simple method to initialize a hashmap with weather codes as keys and String values
     * describing the current weather
     */
    public static void fillWeatherConditions() {
        weatherConditions.put(100, "Clear");
        weatherConditions.put(200, "Partial Clouds");
        weatherConditions.put(300, "Cloudy");
        weatherConditions.put(400, "Light Showers");
        weatherConditions.put(500, "Heavy Showers");
        weatherConditions.put(600, "Rain");
        weatherConditions.put(700, "Snow");
        weatherConditions.put(800, "Thunder");
    }

    /**
     * a method to convert the weather code to a String for an icon in fomantic ui
     * representing the current weather conditions
     *
     * @param weatherCode for current conditions
     * @return String for icon of current conditions
     */
    public static String codeToWeatherIcons(Integer weatherCode) {
        fillWeatherIcons();
        return weatherIcons.get(weatherCode);
    }

    /**
     * a simple method to initialize a hashmap with weather codes and corresponding
     * String values representing weather icons in fomantic UI
     */
    public static void fillWeatherIcons() {
        weatherIcons.put(100, "yellow sun");
        weatherIcons.put(200, "yellow cloud sun");
        weatherIcons.put(300, "grey cloud");
        weatherIcons.put(400, "grey cloud sun rain");
        weatherIcons.put(500, "grey cloud showers heavy");
        weatherIcons.put(600, "blue cloud rain");
        weatherIcons.put(700, "blue snowflake");
        weatherIcons.put(800, "yellow bolt");
    }

    /**
     * method to convert the temperature in degree celsius
     * to fahrenheit
     *
     * @param degreesCelsius
     * @return temperature in fahrenheit
     */
    public static double celsiusToFahrenheit(double degreesCelsius) {
        return ((degreesCelsius * 9) / 5) + 32;
    }

    /**
     * method to convert the windspeed in km/hr to Beaufort
     *
     * @param windSpeed in km/h
     * @return wind reading on beaufort scale
     */
    public static int kmToBeaufort(double windSpeed) {
        int beaufort = 0;
        if ((windSpeed > 1) && (windSpeed <= 5)) {
            beaufort = 1;
        } else if ((windSpeed > 5) && (windSpeed <= 11)) {
            beaufort = 2;
        } else if ((windSpeed > 11) && (windSpeed <= 19)) {
            beaufort = 3;
        } else if ((windSpeed > 19) && (windSpeed <= 28)) {
            beaufort = 4;
        } else if ((windSpeed > 28) && (windSpeed <= 38)) {
            beaufort = 5;
        } else if ((windSpeed > 38) && (windSpeed <= 49)) {
            beaufort = 6;
        } else if ((windSpeed > 49) && (windSpeed <= 61)) {
            beaufort = 7;
        } else if ((windSpeed > 61) && (windSpeed <= 74)) {
            beaufort = 8;
        } else if ((windSpeed > 74) && (windSpeed <= 88)) {
            beaufort = 9;
        } else if ((windSpeed > 88) && (windSpeed <= 102)) {
            beaufort = 10;
        } else if ((windSpeed > 102) && (windSpeed <= 117)) {
            beaufort = 11;
        } else if (windSpeed > 117) {
            beaufort = 12;
        }
        return beaufort;
    }

    /**
     * method to convert the wind direction in degrees to compass/cardinal direction
     *
     * @param windDirection in degrees
     * @return compass/cardinal direction of wind
     */
    public static String degreesToDirection(int windDirection) {
        String[] direction = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N"};
        int index = (int) Math.round((windDirection / 22.5));
        return direction[index];
    }
}
