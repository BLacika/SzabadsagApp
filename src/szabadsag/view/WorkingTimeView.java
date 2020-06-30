package szabadsag.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szabadsag.Main;
import szabadsag.calendar.CalendarCell;
import szabadsag.calendar.FXCalendar;
import szabadsag.model.Day;
import szabadsag.model.Employee;
import java.time.YearMonth;

public class WorkingTimeView {

    private Main main;
    private VBox calendar;
    private FXCalendar fxCalendar;
    @FXML
    private VBox calendarBox;
    @FXML
    private TableView<Employee> empTable;
    @FXML
    private TableColumn<Employee, String> empCol;
    @FXML
    private HBox choiceBox;
    @FXML
    private ChoiceBox<Integer> chYear;
    @FXML
    private ChoiceBox<Integer> chMonth;
    @FXML
    private Button btSave;

    @FXML
    private void initialize() {
        YearMonth yearMonth = YearMonth.now();
        empCol.setCellValueFactory(data -> data.getValue().nameProperty());

        showEmployeeWorkTime(null, null);

        empTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showEmployeeWorkTime(newValue, YearMonth.of(chYear.getValue(), chMonth.getValue()));
        });

        chYear.getItems().addAll(2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025);
        chYear.setValue(yearMonth.getYear());
        chMonth.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        chMonth.setValue(yearMonth.getMonthValue());

        chYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showEmployeeWorkTime(empTable.getSelectionModel().getSelectedItem(),
                    YearMonth.of(newValue, chMonth.getValue()));
        });
        chMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showEmployeeWorkTime(empTable.getSelectionModel().getSelectedItem(),
                    YearMonth.of(chYear.getValue(), newValue));
        });

        btSave.setOnMouseClicked(e -> {
            Employee employee = empTable.getSelectionModel().getSelectedItem();
            ObservableList<Day> days = employee.getDays();
            days.clear();
            for (CalendarCell cell : fxCalendar.getAllCalendarDays()) {
                days.add(new Day(cell.getDate().getYear(), cell.getDate().getMonthValue(),
                        cell.getDate().getDayOfMonth(), cell.getLbStatus().getText()));
            }
        });
    }

    @FXML
    private void showEmployeeWorkTime(Employee employee, YearMonth yearMonth) {
        if (employee != null) {
            calendarBox.getChildren().clear();
            fxCalendar = new FXCalendar(YearMonth.of(yearMonth.getYear(), yearMonth.getMonth()), employee);
            calendar = fxCalendar.getView();
            calendarBox.getChildren().addAll(choiceBox, calendar);
        }
    }

    public void setMain(Main main) {
        this.main = main;

        empTable.setItems(main.getEmployees().sorted());
    }
}
