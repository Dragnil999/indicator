package com.example.indicator.ui;

import com.example.indicator.presentation.viewmodel.ScreenViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ScreenView implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private VBox inputVBox;

    @FXML
    private VBox parametersVBox;

    @FXML
    private TextField maxValueTextField;

    @FXML
    private TextField minValueTextField;

    @FXML
    private TextField maxWarningTextField;

    @FXML
    private TextField minWarningTextField;

    @FXML
    private TextField maxBreakdownTextField;

    @FXML
    private TextField minBreakdownTextField;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button indicateButton;

    private Indicator indicator;

    private final ScreenViewModel viewModel = new ScreenViewModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        indicator = new Indicator();
        root.getChildren().add(0, indicator);
        setListeners();
        handleState();
        viewModel.showParameters();
    }

    private void setListeners() {
        maxValueTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            indicator.setMaxValue(value);
        });

        minValueTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            indicator.setMinValue(value);
        });

        maxWarningTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            indicator.setMaxWarning(value);
        });

        minWarningTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            indicator.setMinWarning(value);
        });

        maxBreakdownTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            indicator.setMaxBreakdown(value);
        });

        minBreakdownTextField.textProperty().addListener((observableValue, s, t1) -> {
            double value = getDoubleFromTextField(t1);
            indicator.setMinBreakdown(value);
        });
    }

    private void handleState() {
        viewModel.state.addListener((observableValue, screenState, t1) -> {
            switch (t1) {
                case INITIAL -> renderParameters();
                case CONTENT -> renderContent();
            }
        });
    }

    private void renderParameters() {
        indicator.setVisible(false);
        parametersVBox.setVisible(true);
        inputVBox.setVisible(true);
        indicateButton.setVisible(true);

        indicator.setManaged(false);
        parametersVBox.setManaged(true);
        inputVBox.setManaged(true);
        indicateButton.setManaged(true);
    }

    private void renderContent() {
        indicator.setVisible(true);
        parametersVBox.setVisible(false);
        inputVBox.setVisible(false);
        indicateButton.setVisible(false);

        indicator.setManaged(true);
        parametersVBox.setManaged(false);
        inputVBox.setManaged(false);
        indicateButton.setVisible(false);
    }

    public void addIndicators() {
        for (String value : getValues()) {
            indicator.addNewIndicator(value);
        }
        viewModel.showIndicator();
    }

    private List<String> getValues() {
        return Arrays.stream(inputTextField.getText().split(" ")).toList();
    }

    private double getDoubleFromTextField(String text) {
        double value = 0;
        try {
            value = Double.parseDouble(text);
        } catch (Exception ignored) {
        }
        return value;
    }
}