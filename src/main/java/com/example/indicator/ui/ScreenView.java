package com.example.indicator.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenView implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private Slider maxValueSlider;

    @FXML
    private Slider minValueSlider;

    @FXML
    private TextField maxWarningTextField;

    @FXML
    private TextField minWarningTextField;

    @FXML
    private TextField maxBreakdownTextField;

    @FXML
    private TextField minBreakdownTextField;

    private Indicator indicator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        indicator = new Indicator();
        root.getChildren().add(1, indicator);
        setListeners();
    }

    private void setListeners() {
        maxValueSlider.valueProperty().addListener((observableValue, number, t1) -> {
            indicator.setMaxValue(t1.doubleValue());
        });

        minValueSlider.valueProperty().addListener((observableValue, number, t1) -> {
            indicator.setMinValue(t1.doubleValue());
        });

        maxWarningTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            if (value >= 0) {
                indicator.setMaxWarning(value);
            }
        });

        minWarningTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            if (value >= 0) {
                indicator.setMinWarning(value);
            }
        });

        maxBreakdownTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            if (value >= 0) {
                indicator.setMaxBreakdown(value);
            }
        });

        minBreakdownTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            if (value >= 0) {
                indicator.setMinBreakdown(value);
            }
        });
    }

    private double getDoubleFromTextField(String text) {
        double value = -1.0;
        try {
            value = Double.parseDouble(text);
        } catch (Exception ignored) {
        }
        return value;
    }
}