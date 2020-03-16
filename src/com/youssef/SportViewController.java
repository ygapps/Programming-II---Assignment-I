package com.youssef;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class SportViewController extends Stage {

    public SportViewControllerDelegate delegate;
    private Scene scene;
    private VBox verticalLayout;

    private Label newSportLabel;
    private ComboBox newSportNameComboBox;
    private Spinner<Integer> newSportMinutesSpinner;
    private Button saveButton;

    private ObservableList<String> sports;
    private HBox horizontalStack;

    public SportViewController() {
        this.prepareScene();
        this.setScene(this.scene);
        this.setTitle("Add New Activity");
        this.initStyle(StageStyle.DECORATED);
    }

    private void prepareScene() {
        // LABEL
        this.newSportLabel = new Label("New Activity");
        this.newSportLabel.setFont(new Font(60));

        // COMBO BOX

        this.sports = FXCollections.observableArrayList();
        sports.addAll("Swimming", "Running", "Kickboxing", "Strength Training");

        this.newSportNameComboBox = new ComboBox(this.sports);
        this.newSportNameComboBox.setValue("Swimming");
        this.newSportNameComboBox.setTooltip(new Tooltip("SPORT TYPE"));

        // SPINNER

        this.newSportMinutesSpinner = new Spinner<Integer>();
        this.newSportMinutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120, 5, 1));
        this.newSportMinutesSpinner.setTooltip(new Tooltip("MINUTES DONE"));


        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sportName = String.valueOf(newSportNameComboBox.getValue());
                Integer minutesPlayed = newSportMinutesSpinner.getValue();

                if (delegate != null) {

                    Sport sportItem;
                    if (sportName.equals("Swimming")) {
                        sportItem = new Swimming(minutesPlayed);
                    } else if (sportName.equals("Running")) {
                        sportItem = new Running(minutesPlayed);
                    } else if (sportName.equals("Strength Training")) {
                        sportItem = new StrengthTraining(minutesPlayed);
                    } else {
                        sportItem = new Kickboxing(minutesPlayed);
                    }

                    delegate.sportViewControllerDidAddItem(sportItem);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Tracker");
                    alert.setHeaderText("Success");
                    alert.setContentText("Activity Recorded Successfully");
                    alert.show();
                }
            }
        });

        Region spacer = new Region();
        spacer.setMinWidth(Region.USE_PREF_SIZE); HBox.setHgrow(spacer, Priority.ALWAYS);

        this.horizontalStack = new HBox(spacer ,this.saveButton);
        this.horizontalStack.setPadding(new Insets(0, 20, 0, 0));

        this.verticalLayout = new VBox(this.newSportNameComboBox, this.newSportMinutesSpinner, this.horizontalStack);
        this.verticalLayout.setSpacing(10);
        this.verticalLayout.setAlignment(Pos.BASELINE_LEFT);
        this.verticalLayout.setPadding(new Insets(0, 0, 0, 25));
        this.scene = new Scene(this.verticalLayout, 350, 120);
    }
}
