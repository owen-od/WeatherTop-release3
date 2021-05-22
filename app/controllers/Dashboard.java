package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;
import utils.StationConversion;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        Collections.sort(stations, Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));
        for (Station station : stations) {
            if (station.readings.size() > 0) {
                Reading latestReading = station.readings.get(station.readings.size() - 1);
                station.tempCelsius = latestReading.temperature;
                station.pressure = latestReading.pressure;
                station.weatherConditions = StationConversion.codeToWeatherConditions(latestReading.code);
                station.tempFarenheit = StationConversion.celsiusToFahrenheit(latestReading.temperature);
                station.beaufort = StationConversion.kmToBeaufort(latestReading.windSpeed);
                station.compassDirection = StationConversion.degreesToDirection(latestReading.windDirection);
                if ((latestReading.temperature <= 10) && (latestReading.windSpeed > 4.8)) {
                    station.windChill = StationAnalytics.windChillCalculator(latestReading.temperature, latestReading.windSpeed);
                } else {
                    station.windChill = station.tempCelsius;
                }
                station.weatherIcon = StationConversion.codeToWeatherIcons(latestReading.code);
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
        }
        render("dashboard.html", stations);
    }

    public static void addStation(String name, double latitude, double longitude) {
        Logger.info("Adding a station called: " + name);
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(name, latitude, longitude);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }

    public static void deleteStation(long id) {
        Logger.info("Removing a station");
        Member member = Accounts.getLoggedInMember();
        Station station = Station.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }
}
