package com.xdxdxdcat.bots.herbCleaner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class herbController implements Initializable {
    @FXML
    private Button buttonClean;
    @FXML
    private ComboBox<String> selectHerb;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        selectHerb.getItems().addAll("Grimy guam leaf", "Grimy marrentill", "Grimy tarromin", "Grimy harralander",
                "Grimy ranarr weed", "Grimy toadflax", "Grimy irit leaf", "Grimy avantoe", "Grimy kwuarm",
                "Grimy snapdragon", "Grimy cadantine", "Grimy lantadyme", "Grimy dwarf weed", "Grimy torstol");
        selectHerb.getSelectionModel().selectFirst();
        buttonClean.setOnAction(getButtonCleanAction());
    }

    private final HerbCleaner script;
    private final Stage stage;

    public herbController(HerbCleaner script, Stage stage)
    {
        this.script = script;
        this.stage = stage;
    }

    private EventHandler<ActionEvent> getButtonCleanAction()
    {
        return event -> {
            script.setHerb(selectHerb.getSelectionModel().getSelectedItem());
            stage.hide();
        };
    }
}
