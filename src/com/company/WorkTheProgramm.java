package com.company;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;

public class WorkTheProgramm {
    @FXML
    ScrollBar scrollBar;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private void func()
    {
        scrollBar.valueProperty().addListener(event->{
            anchorPane.setPrefSize(500,500);
        });

    }
}

