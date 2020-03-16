package com.youssef;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HomeViewController extends Stage implements SportViewControllerDelegate {

    public SportViewController addSportViewController;
    private ObservableList<Sport> sports;
    private ObservableList<PieChart.Data> pieChartCaloriesSports = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartHeartRateSports = FXCollections.observableArrayList();

    private Scene scene;
    private BorderPane borderLayout;

    private HBox horizontalStack;
    private ToolBar toolBar;

    private TableView<Sport> tableView;
    private TableColumn<Sport, Integer> rank;
    private TableColumn<Sport, String> name;
    private TableColumn<Sport, Integer> minutes;
    private TableColumn<Sport, Integer> caloriesBurnt;
    private TableColumn<Sport, Float> heartRateIncrease;

    private PieChart pieChartCalories;
    private PieChart pieChartHeartRate;

    private Button tableViewModeButton;
    private Button pieChartModeButton;
    private Button addNewSportButton;

    private Label caloriesIndicatorLabel;
    private Label heartRateIndicatorLabel;
    private Label caloriesLabel;
    private Label heartRateLabel;

    private VBox caloriesStack;
    private VBox heartRateStack;
    private HBox pieChartsHStack;

    private ScaleTransition scaleAnimation;

    private final Integer heartRateInitialValue = 80;

    public HomeViewController() {
        this.prepareScene();
        this.setScene(this.scene);
        this.setTitle("Tracker");
    }

    public HomeViewController(StageStyle style) {
        super(style);
    }

    public ObservableList<Sport> getSports() {
        return sports;
    }

    public void setSports(ObservableList<Sport> sports) {
        this.sports = sports;
    }

    @Override
    public void sportViewControllerDidAddItem(Sport sportItem) {
        sportItem.heartRateIncrease = Float.parseFloat(this.heartRateIndicatorLabel.getText()) * sportItem.minutesPlayed * sportItem.heartRateIncreaseRate;
        this.sports.add(sportItem);

        this.updateIndicators();
        this.updatePieChartCaloriesData();
        this.updatePieChartHeartRateData();
    }


    public void updateIndicators() {
        Integer totalCaloriesBurnt = 0;
        Float totalHeartRate = 0.0f;

        for (Sport sportItem: sports) {
            totalCaloriesBurnt += sportItem.caloriesBurnt;
            totalHeartRate += sportItem.heartRateIncrease;
        }

        this.caloriesIndicatorLabel.setText("" + totalCaloriesBurnt);
        this.heartRateIndicatorLabel.setText("" + String.format("%.2f", (heartRateInitialValue + totalHeartRate)));
    }

    public void updatePieChartCaloriesData() {
        pieChartCaloriesSports.clear();

        Integer swimmingCalories = 0;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Swimming")) {
                swimmingCalories += sportItem.getCaloriesBurnt();
            }
        }

        if (swimmingCalories != 0) {
            pieChartCaloriesSports.add(new PieChart.Data("Swimming", swimmingCalories));
        }

        Integer runningCalories = 0;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Running")) {
                runningCalories += sportItem.getCaloriesBurnt();
            }
        }

        if (runningCalories != 0) {
            pieChartCaloriesSports.add(new PieChart.Data("Running", runningCalories));
        }

        Integer kickboxingCalories = 0;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Kickboxing")) {
                kickboxingCalories += sportItem.getCaloriesBurnt();
            }
        }

        if (kickboxingCalories != 0) {
            pieChartCaloriesSports.add(new PieChart.Data("Kickboxing", kickboxingCalories));
        }

        Integer strengthTraining = 0;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Strength Training")) {
                strengthTraining += sportItem.getCaloriesBurnt();
            }
        }

        if (strengthTraining != 0) {
            pieChartCaloriesSports.add(new PieChart.Data("Strength Training", strengthTraining));
        }

        pieChartCalories.setData(pieChartCaloriesSports);
    }

    public void updatePieChartHeartRateData() {
        pieChartHeartRateSports.clear();

        Float swimmingHeartRateIncrease = 0.0f;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Swimming")) {
                swimmingHeartRateIncrease += sportItem.getHeartRateIncrease();
            }
        }

        if (swimmingHeartRateIncrease != 0) {
            pieChartHeartRateSports.add(new PieChart.Data("Swimming", swimmingHeartRateIncrease));
        }

        Float runningHeartRateIncrease = 0.0f;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Running")) {
                runningHeartRateIncrease += sportItem.getCaloriesBurnt();
            }
        }

        if (runningHeartRateIncrease != 0) {
            pieChartHeartRateSports.add(new PieChart.Data("Running", runningHeartRateIncrease));
        }

        Float kickboxingHeartRateIncrease = 0.0f;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Kickboxing")) {
                kickboxingHeartRateIncrease += sportItem.getCaloriesBurnt();
            }
        }

        if (kickboxingHeartRateIncrease != 0) {
            pieChartHeartRateSports.add(new PieChart.Data("Kickboxing", kickboxingHeartRateIncrease));
        }

        Float strengthTrainingHeartRateIncrease = 0.0f;
        for (Sport sportItem: sports) {
            if (sportItem.getName().equals("Strength Training")) {
                strengthTrainingHeartRateIncrease += sportItem.getCaloriesBurnt();
            }
        }

        if (strengthTrainingHeartRateIncrease != 0) {
            pieChartHeartRateSports.add(new PieChart.Data("Strength Training", strengthTrainingHeartRateIncrease));
        }

        pieChartHeartRate.setData(pieChartHeartRateSports);
    }

    private void prepareScene() {

        // CREATING TOP BAR
        this.tableViewModeButton = new Button("Table View");
        this.tableViewModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition transition = new FadeTransition(Duration.seconds(1));
                transition.setFromValue(1.0);
                transition.setToValue(0.0);
                transition.setNode(borderLayout.getBottom());
                transition.play();

                transition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tableView.setOpacity(0.0);
                        borderLayout.setBottom(tableView);

                        FadeTransition newFade = new FadeTransition(Duration.seconds(0.7));
                        newFade.setFromValue(0.0);
                        newFade.setToValue(1.0);
                        newFade.setNode(borderLayout.getBottom());
                        newFade.play();
                    }
                });
            }
        });
        this.pieChartModeButton = new Button("Pie Chart");
        this.pieChartModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FadeTransition transition = new FadeTransition(Duration.seconds(1));
                transition.setFromValue(1.0);
                transition.setToValue(0.0);
                transition.setNode(borderLayout.getBottom());
                transition.play();

                transition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        pieChartsHStack.setOpacity(0.0);
                        borderLayout.setBottom(pieChartsHStack);

                        FadeTransition newFade = new FadeTransition(Duration.seconds(0.7));
                        newFade.setFromValue(0.0);
                        newFade.setToValue(1.0);
                        newFade.setNode(borderLayout.getBottom());
                        newFade.play();
                    }
                });
            }
        });

        this.addNewSportButton = new Button("Add Activity");
        this.addNewSportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (addSportViewController == null) {
                    addSportViewController = new SportViewController();
                }

                addSportViewController.delegate = HomeViewController.this;
                addSportViewController.show();
            }
        });

        Region spacer = new Region();
        spacer.setMinWidth(Region.USE_PREF_SIZE);

        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.toolBar = new ToolBar();
        this.toolBar.getItems().addAll(this.tableViewModeButton, new Separator(), this.pieChartModeButton, spacer, this.addNewSportButton);
        this.toolBar.setOrientation(Orientation.HORIZONTAL);

        // END CREATING TOP BAR

        // CREATING MIDDLE INDICATORS

        this.caloriesIndicatorLabel = new Label("0");
        this.caloriesIndicatorLabel.setFont(new Font(80));
        this.heartRateIndicatorLabel = new Label("" + heartRateInitialValue);
        this.heartRateIndicatorLabel.setFont(new Font(80));
        this.heartRateIndicatorLabel.setTextFill(Color.web("#ff4d4d"));
        this.caloriesLabel = new Label("Calories");
        this.heartRateLabel = new Label("Heart Rate");

        this.scaleAnimation = new ScaleTransition();
        this.scaleAnimation.setDuration(Duration.millis(2000));
        this.scaleAnimation.setNode(this.heartRateIndicatorLabel);
        this.scaleAnimation.setByY(0.25);
        this.scaleAnimation.setByX(0.25);
        this.scaleAnimation.setCycleCount(Timeline.INDEFINITE);
        this.scaleAnimation.setAutoReverse(true);
        this.scaleAnimation.play();

        this.caloriesStack = new VBox(this.caloriesIndicatorLabel, this.caloriesLabel);
        this.caloriesStack.setAlignment(Pos.BASELINE_CENTER);
        this.caloriesStack.setSpacing(12);
        this.heartRateStack = new VBox(this.heartRateIndicatorLabel, this.heartRateLabel);
        this.heartRateStack.setAlignment(Pos.BASELINE_CENTER);
        this.heartRateStack.setSpacing(12);

        this.horizontalStack = new HBox(this.caloriesStack, this.heartRateStack);
        this.horizontalStack.setSpacing(50);
        this.horizontalStack.setPadding(new Insets(0, 0, 25, 0));
        this.horizontalStack.setAlignment(Pos.BASELINE_CENTER);
        // END CREATING MIDDLE INDICATORS



        // CREATING TABLE
        this.name = new TableColumn<Sport, String>("Sport Name");
        this.minutes = new TableColumn<Sport, Integer>("Minutes");
        this.caloriesBurnt = new TableColumn<Sport, Integer>("Calories Burnt");
        this.heartRateIncrease = new TableColumn<Sport, Float>("Heart Rate Increase");

        this.tableView = new TableView<Sport>();
        this.tableView.setPlaceholder(new Label("No activities"));
        this.tableView.getColumns().addAll(this.name, this.minutes, this.caloriesBurnt, this.heartRateIncrease);

        this.name.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.minutes.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        this.minutes.setCellValueFactory(new PropertyValueFactory<>("minutesPlayed"));

        this.caloriesBurnt.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        this.caloriesBurnt.setCellValueFactory(new PropertyValueFactory<>("caloriesBurnt"));

        this.heartRateIncrease.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        this.heartRateIncrease.setCellValueFactory(new PropertyValueFactory<>("heartRateIncrease"));
        // END CREATING TABLE


        // ADDING PIE CHART

        this.pieChartCalories = new PieChart();
        this.pieChartCalories.setLabelLineLength(50);
        this.pieChartCalories.setTitle("Based on Calories");
        this.pieChartCalories.setLabelsVisible(true);

        this.pieChartHeartRate = new PieChart();
        this.pieChartHeartRate.setLabelLineLength(50);
        this.pieChartHeartRate.setTitle("Based on Heart Rate");
        this.pieChartHeartRate.setLabelsVisible(true);

        this.pieChartsHStack = new HBox(pieChartCalories, pieChartHeartRate);
        this.pieChartsHStack.setSpacing(10);
        // END

        // ADDING SPORTS
        this.sports = FXCollections.observableArrayList();
        this.tableView.setItems(sports);
        // END ADDING SPORTS

        this.borderLayout = new BorderPane();
        this.borderLayout.setTop(this.toolBar);
        this.borderLayout.setCenter(this.horizontalStack);
        this.borderLayout.setBottom(this.tableView);
        this.scene = new Scene(borderLayout, 800, 700);
    }
}
