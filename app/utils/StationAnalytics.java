package utils;
import models.Reading;
import models.Station;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

public class StationAnalytics {
    public static HashMap<Integer, String> weatherConditions = new HashMap<Integer, String>();
    public static HashMap<Integer, String> weatherIcons = new HashMap<Integer, String>();

    /**
     * a method to convert the weather code to a String of the current
     * weather conditions
     *
     * @param weatherCode
     * @return description of current weather conditions
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
     * @param weatherCode
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
     * to farenheit
     *
     * @param degreesCelsius
     * @return temperature in farenheit
     */
    public static double celsiusToFahrenheit(double degreesCelsius) {
        return ((degreesCelsius * 9) / 5) + 32;
    }

    /**
     * method to convert the windspeed in km/hr to Beaufort
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
     * method to convert the wind direction in degrees to compass direction
     * @param windDirection
     * @return compass Direction
     */
    public static String degreesToDirection (int windDirection) {
        String[] direction = {"N","NNE","NE","ENE","E","ESE","SE","SSE","S","SSW","SW","WSW","W","WNW","NW","NNW","N"};
        int index = (int) Math.round((windDirection / 22.5));
        return direction[index];
    }

    /**
     * a method to calculate the wind chill
     * @params temperature in degrees celsius, wind speed in km/h
     * @return wind chill
     */
    public static double windChillCalculator (double t, double v) {
        return toTwoDecimalPlaces(13.12 + (0.6215*t) - 11.37 * Math.pow(v,0.16) + 0.3965 * t * Math.pow(v,0.16));
    }

    /**
     * a private helper method to convert a double to two decimal places
     * @param num
     * @return num to two decimal places
     */
    private static double toTwoDecimalPlaces(double num) {
        BigDecimal bd = new BigDecimal(Double.toString(num));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * A method to calculate the minimum wind speed from all current wind speed readings
     * @param readings for the station
     * @return the minimum wind speed of all the wind speed readings
     */
    public static double minWindSpeed (List<Reading> readings) {
        Reading minReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.windSpeed < minReading.windSpeed) {
                minReading = reading;
            }
        }
        return minReading.windSpeed;
    }

    /**
     * A method to calculate the maximum wind speed from all current wind speed readings
     * @param readings for the station
     * @return the maximum wind speed of all the wind speed readings
     */
    public static double maxWindSpeed (List<Reading> readings) {
        Reading maxReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.windSpeed > maxReading.windSpeed) {
                maxReading = reading;
            }
        }
        return maxReading.windSpeed;
    }

    /**
     * A method to calculate the minimum pressure from all current pressure readings
     * @param readings for the station
     * @return the minimum pressure of all the pressure readings
     */
    public static int minPressure (List<Reading> readings) {
        Reading minReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.pressure < minReading.pressure) {
                minReading = reading;
            }
        }
        return minReading.pressure;
    }

    /**
     * A method to calculate the maximum pressure from all current pressure readings
     * @param readings for the station
     * @return the maximum pressure of all the pressure readings
     */
    public static int maxPressure (List<Reading> readings) {
        Reading maxReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.pressure > maxReading.pressure) {
                maxReading = reading;
            }
        }
        return maxReading.pressure;
    }

    /**
     * A method to calculate the minimum temperature from all current temperature readings
     * @param readings for the station
     * @return the minimum temperature of all the temperature readings
     */
    public static double minTemp (List<Reading> readings) {
        Reading minReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.temperature < minReading.temperature) {
                minReading = reading;
            }
        }
        return minReading.temperature;
    }

    /**
     * A method to calculate the maximum temperature from all current temperature readings
     * @param readings for the station
     * @return the maximum temperature of all the temperature readings
     */
    public static double maxTemp (List<Reading> readings) {
        Reading maxReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.temperature > maxReading.temperature) {
                maxReading = reading;
            }
        }
        return maxReading.temperature;
    }

}
