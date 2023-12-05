package com.example.indicator.ui;

import com.example.indicator.presentation.IndicatorViewModel;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Indicator extends Pane {

    private static final double PREF_HEIGHT = 360.0;
    private static final double PREF_WIDTH = 50.0;
    private static final double COEFFICIENT = 3.6;

    private final IndicatorViewModel viewModel = new IndicatorViewModel();
    private final Rectangle normalZone = new Rectangle(PREF_WIDTH, PREF_HEIGHT, Color.GREEN);
    private final Rectangle topBreakdownZone = new Rectangle(PREF_WIDTH, 0, Color.RED);
    private final Rectangle bottomBreakdownZone = new Rectangle(PREF_WIDTH, 0, Color.RED);
    private final Rectangle topWarningZone = new Rectangle(PREF_WIDTH, 0, Color.YELLOW);
    private final Rectangle bottomWarningZone = new Rectangle(PREF_WIDTH, 0, Color.YELLOW);
    private final Rectangle topBarrier = new Rectangle(PREF_WIDTH, 0, Color.GHOSTWHITE);
    private final Rectangle bottomBarrier = new Rectangle(PREF_WIDTH, PREF_HEIGHT + 40, Color.GHOSTWHITE);

    public Indicator() {
        VBox root = new VBox();
        root.setLayoutY(20.0);
        this.setPrefSize(PREF_WIDTH, PREF_HEIGHT + 40);
        this.getChildren().addAll(root, topBarrier, bottomBarrier);
        topBarrier.setLayoutY(20.0);
        bottomBarrier.setLayoutY(PREF_HEIGHT + 20);
        root.setAlignment(Pos.CENTER);
        root.getChildren()
                .addAll(topBreakdownZone, topWarningZone, normalZone, bottomWarningZone, bottomBreakdownZone);
        setListeners();
    }

    private void setListeners() {
        viewModel.maxValue.addListener(((observableValue, number, t1) -> {
            double newHeight = PREF_HEIGHT - t1.doubleValue() * COEFFICIENT;
            topBarrier.setHeight(newHeight);
        }));

        viewModel.minValue.addListener(((observableValue, number, t1) -> {
            double newHeight = PREF_HEIGHT + 20 - t1.doubleValue() * COEFFICIENT;
            bottomBarrier.setLayoutY(newHeight);
        }));

        viewModel.maxWarning.addListener(((observableValue, number, t1) -> {
            double newHeight = t1.doubleValue() * COEFFICIENT;
            normalZone.setHeight(calculateNormalZone() * COEFFICIENT);
            topWarningZone.setHeight(newHeight);
        }));

        viewModel.minWarning.addListener(((observableValue, number, t1) -> {
            double newHeight = t1.doubleValue() * COEFFICIENT;
            normalZone.setHeight(calculateNormalZone() * COEFFICIENT);
            bottomWarningZone.setHeight(newHeight);
        }));

        viewModel.maxBreakdown.addListener(((observableValue, number, t1) -> {
            double newHeight = t1.doubleValue() * COEFFICIENT;
            normalZone.setHeight(calculateNormalZone() * COEFFICIENT);
            topBreakdownZone.setHeight(newHeight);
        }));

        viewModel.minBreakdown.addListener(((observableValue, number, t1) -> {
            double newHeight = t1.doubleValue() * COEFFICIENT;
            normalZone.setHeight(calculateNormalZone() * COEFFICIENT);
            bottomBreakdownZone.setHeight(newHeight);
        }));
    }

    private double calculateNormalZone() {
        double scaleSize = viewModel.maxValue.get() - viewModel.minValue.get();
        return scaleSize - viewModel.maxBreakdown.get() - viewModel.minBreakdown.get() - viewModel.maxWarning.get() - viewModel.minWarning.get();
    }

    public void setMaxValue(double value) {
        viewModel.maxValue.set(value);
    }

    public void setMinValue(double value) {
        viewModel.minValue.set(value);
    }

    public void setMaxWarning(double value) {
        viewModel.maxWarning.set(value);
    }

    public void setMinWarning(double value) {
        viewModel.minWarning.set(value);
    }

    public void setMaxBreakdown(double value) {
        viewModel.maxBreakdown.set(value);
    }

    public void setMinBreakdown(double value) {
        viewModel.minBreakdown.set(value);
    }
}
