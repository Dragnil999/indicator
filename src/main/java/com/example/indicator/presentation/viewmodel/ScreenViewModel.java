package com.example.indicator.presentation.viewmodel;

import com.example.indicator.presentation.ScreenState;
import javafx.beans.property.SimpleObjectProperty;

public class ScreenViewModel {

    public final SimpleObjectProperty<ScreenState> state = new SimpleObjectProperty<>();

    public void showIndicator() {
        state.setValue(ScreenState.CONTENT);
    }

    public void showParameters() {
        state.setValue(ScreenState.INITIAL);
    }
}
