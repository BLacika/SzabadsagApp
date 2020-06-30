package szabadsag.view;

import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import szabadsag.Main;
import szabadsag.model.Employee;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class EmployeeOverview {

    private Main main;
    @FXML
    private TableView<Employee> empTable;
    @FXML
    private TableColumn<Employee, String> empCol;
    @FXML
    private TableColumn<Employee, String> colPlace;
    @FXML
    private Button btEdit;
    @FXML
    private Label lbBirth;
    @FXML
    private Label lbTempAddress;
    @FXML
    private Label lbTax;
    @FXML
    private Button btDelete;
    @FXML
    private Label lbPermAddress;
    @FXML
    private Label lbSoc;
    @FXML
    private Label lbWorkPlace;
    @FXML
    private Label lbName;
    @FXML
    private Button btNew;
    @FXML
    private Label lbMothersName;
    @FXML
    private CheckBox chActive;

    @FXML
    private void initialize() {
        empCol.setCellValueFactory(data -> data.getValue().nameProperty());
        colPlace.setCellValueFactory(data -> data.getValue().workPlaceProperty());

        showEmployeeDetails(null);

        empTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showEmployeeDetails(newValue);
        });
    }

    @FXML
    private void showEmployeeDetails(Employee employee) {
        if (employee != null) {
            lbName.setText(employee.getName());
            lbBirth.setText(employee.getBirth());
            lbMothersName.setText(employee.getMothersName());
            lbTax.setText(employee.getTaxNumber());
            lbSoc.setText(employee.getSocialNumber());
            lbPermAddress.setText(employee.getPermAddress());
            lbTempAddress.setText(employee.getTempAddress());
            lbWorkPlace.setText(employee.getWorkPlace());
            chActive.setSelected(employee.isActive());
        } else {
            lbName.setText("");
            lbBirth.setText("");
            lbMothersName.setText("");
            lbTax.setText("");
            lbSoc.setText("");
            lbPermAddress.setText("");
            lbTempAddress.setText("");
            lbWorkPlace.setText("");
            chActive.setSelected(false);
        }
    }

    @FXML
    private void handleNewEmployee() throws IOException {
        Employee employee = new Employee();
        boolean isOkClicked = main.showEmployeeEditDialog(employee);
        if (isOkClicked) {
            main.getEmployees().add(employee);
        }
    }

    @FXML
    private void handleEditEmployee() throws IOException {
        Employee selected = empTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = main.showEmployeeEditDialog(selected);
            if (okClicked) {
                showEmployeeDetails(selected);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Nincs kiválasztva");
            alert.setHeaderText("Nincs alkalmazott kiválasztva");
            alert.setContentText("Kérlek válassz ki egy alkalmazottat!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteEmployee() {
        int selectedIndex = empTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(main.getStage());
            alert.setTitle("Alkalmazott törlése");
            alert.setHeaderText("Alkalmazott törlésének megerősítése!");
            alert.setContentText("Biztos, hogy törlöd az Alkalmazottat?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                empTable.getItems().remove(selectedIndex);
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Nincs kiválasztva");
            alert.setHeaderText("Nincs alkalmazott kiválasztva");
            alert.setContentText("Kérlek válassz ki egy alkalmazottat!");

            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main = main;

        empTable.setItems(main.getEmployees());
        main.getEmployees().sorted().comparatorProperty().bind(empTable.comparatorProperty());
        empTable.getSortOrder().add(empCol);
    }
}
