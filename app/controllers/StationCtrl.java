package controllers;

import java.util.Date;
import java.util.List;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        if (station.readings.size()>0) {
            Reading latestReading = station.readings.get(station.readings.size() - 1);
            station.tempCelsius = latestReading.temperature;
            station.pressure = latestReading.pressure;
            station.weatherConditions = StationAnalytics.codeToWeatherConditions(latestReading.code);
            station.tempFarenheit = StationAnalytics.celsiusToFahrenheit(latestReading.temperature);
            station.beaufort = StationAnalytics.kmToBeaufort(latestReading.windSpeed);
            station.compassDirection = StationAnalytics.degreesToDirection(latestReading.windDirection);
            if ((latestReading.temperature<=10) && (latestReading.windSpeed>4.8)) {
                station.windChill = StationAnalytics.windChillCalculator(latestReading.temperature, latestReading.windSpeed);
            } else {
                station.windChill = station.tempCelsius;
            }
            station.weatherIcon = StationAnalytics.codeToWeatherIcons(latestReading.code);
            station.minWindSpeed = StationAnalytics.minWindSpeed(station.readings);
            station.maxWindSpeed = StationAnalytics.maxWindSpeed(station.readings);
            station.maxPressure = StationAnalytics.maxPressure(station.readings);
            station.minPressure = StationAnalytics.minPressure(station.readings);
            station.maxTemp = StationAnalytics.maxTemp(station.readings);
            station.minTemp = StationAnalytics.minTemp(station.readings);
            station.tempTrend = StationAnalytics.tempTrend(station.readings);
            station.windTrend = StationAnalytics.windTrend(station.readings);
            station.pressureTrend = StationAnalytics.pressureTrend(station.readings);
        }
            render("station.html", station);
    }

    public static void addReading(long id, int code, double temperature, double windSpeed, int windDirection, int pressure) {
        Reading reading = new Reading (code, temperature, windSpeed, windDirection, pressure, new Date());
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deleteReading (long id, long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing reading");
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/stations/" + id);
    }
}
