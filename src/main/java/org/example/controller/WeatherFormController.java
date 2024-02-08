package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.weather.WeatherApp;
import org.json.simple.JSONObject;

public class WeatherFormController {
    public TextField txtLocation;
    public ImageView imgView;
    public Label lblTemp;
    public Label lblCondition;
    public Label lblHumidity;
    public Label lblSpeed;
    private JSONObject weatherData;

    public void locationOnAction(ActionEvent actionEvent) {
        SearchOnAction(actionEvent);
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        // get location from user
        String userInput = txtLocation.getText();

        // validate input - remove whitespace to ensure non-empty text
        if(userInput.replaceAll("\\s", "").length() <= 0){
            return;
        }

        // retrieve weather data
        weatherData = WeatherApp.getWeatherData(userInput);

        // update weather image
        String weatherCondition = (String) weatherData.get("weather_condition");

        // update the weather image
        switch(weatherCondition){
            case "Clear":
                imgView.setImage(new Image("/assets/clear.png"));
                break;
            case "Cloudy":
                imgView.setImage(new Image("/assets/cloudy.png"));
                break;
            case "Rain":
                imgView.setImage(new Image("/assets/rain.png"));
                break;
            case "Snow":
                imgView.setImage(new Image("/assets/snow.png"));
                break;
        }

        // update temperature text
        double temperature = (double) weatherData.get("temperature");
        lblTemp.setText(temperature + " C");

        // update weather condition text
        lblCondition.setText(weatherCondition);

        // update humidity text
        long humidity = (long) weatherData.get("humidity");
        lblHumidity.setText(humidity + "%");

        // update windspeed text
        double windspeed = (double) weatherData.get("windspeed");
        lblSpeed.setText(windspeed + "km/h");
    }
}
