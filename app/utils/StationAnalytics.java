package utils;

import models.Reading;
import models.Station;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

public class StationAnalytics {
    /**
     * a method to calculate the wind chill
     *
     * @param t temperature in degrees celsius
     * @param v wind speed in km/h
     * @return wind chill
     */
    public static double windChillCalculator(double t, double v) {
        return toTwoDecimalPlaces(13.12 + (0.6215 * t) - 11.37 * Math.pow(v, 0.16) + 0.3965 * t * Math.pow(v, 0.16));
    }

    /**
     * a private helper method to convert a double to two decimal places
     *
     * @param num number in more that two decimal places
     * @return number to two decimal places
     */
    private static double toTwoDecimalPlaces(double num) {
        BigDecimal bd = new BigDecimal(Double.toString(num));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * A method to calculate the minimum wind speed from all current wind speed readings
     *
     * @param readings readings for the station
     * @return the minimum wind speed of all the wind speed readings
     */
    public static double minWindSpeed(List<Reading> readings) {
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
     *
     * @param readings readings for the station
     * @return the maximum wind speed of all the wind speed readings
     */
    public static double maxWindSpeed(List<Reading> readings) {
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
     *
     * @param readings readings for the station
     * @return the minimum pressure of all the pressure readings
     */
    public static int minPressure(List<Reading> readings) {
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
     *
     * @param readings readings for the station
     * @return the maximum pressure of all the pressure readings
     */
    public static int maxPressure(List<Reading> readings) {
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
     *
     * @param readings readings for the station
     * @return the minimum temperature of all the temperature readings
     */
    public static double minTemp(List<Reading> readings) {
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
     *
     * @param readings readings for the station
     * @return the maximum temperature of all the temperature readings
     */
    public static double maxTemp(List<Reading> readings) {
        Reading maxReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.temperature > maxReading.temperature) {
                maxReading = reading;
            }
        }
        return maxReading.temperature;
    }

    /**
     * a method determining if the last three temperature readings for a station are increasing, decreasing, or neither,
     * and returning a string corresponding to an icon in fomantic ui
     *
     * @param readings readings for the station
     * @return String corresponding to icon in fomantic ui
     */
    public static String tempTrend(List<Reading> readings) {
        if (readings.size() >= 4) {
            int n = readings.size() - 1;
            int increasing = 0;
            int decreasing = 0;
            for (int i = n; i > n - 3; i--) {
                if (readings.get(i).temperature > readings.get(i - 1).temperature) {
                    increasing++;
                }
            }
            if (increasing == 3) {
                return "arrow up";
            }
            for (int i = n; i > n - 3; i--) {
                if (readings.get(i).temperature < readings.get(i - 1).temperature) {
                    decreasing++;
                }
            }
            if (decreasing == 3) {
                return "arrow down";
            }
        }
        return "no trend";
    }

    /**
     * a method determining if the last three wind readings for a station are increasing, decreasing, or neither,
     * and returning a string corresponding to an icon in fomantic ui
     *
     * @param readings readings for the station
     * @return String corresponding to icon in fomantic ui
     */
    public static String windTrend(List<Reading> readings) {
        if (readings.size() >= 4) {
            int n = readings.size() - 1;
            int increasing = 0;
            int decreasing = 0;
            for (int i = n; i > n - 3; i--) {
                if (readings.get(i).windSpeed > readings.get(i - 1).windSpeed) {
                    increasing++;
                }
            }
            if (increasing == 3) {
                return "arrow up";
            }
            for (int i = n; i > n - 3; i--) {
                if (readings.get(i).windSpeed < readings.get(i - 1).windSpeed) {
                    decreasing++;
                }
            }
            if (decreasing == 3) {
                return "arrow down";
            }
        }
        return "no trend";
    }

    /**
     * a method determining if the last three pressure readings for a station are increasing, decreasing, or neither,
     * and returning a string corresponding to an icon in fomantic ui
     *
     * @param readings readings for the station
     * @return String corresponding to icon in fomantic ui
     */
    public static String pressureTrend(List<Reading> readings) {
        if (readings.size() >= 4) {
            int n = readings.size() - 1;
            int increasing = 0;
            int decreasing = 0;
            for (int i = n; i > n - 3; i--) {
                if (readings.get(i).pressure > readings.get(i - 1).pressure) {
                    increasing++;
                }
            }
            if (increasing == 3) {
                return "arrow up";
            }
            for (int i = n; i > n - 3; i--) {
                if (readings.get(i).pressure < readings.get(i - 1).pressure) {
                    decreasing++;
                }
            }
            if (decreasing == 3) {
                return "arrow down";
            }
        }
        return "no trend";
    }
}
