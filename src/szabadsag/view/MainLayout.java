package szabadsag.view;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import szabadsag.Main;

import java.io.File;
import java.io.IOException;

public class MainLayout {

    private Main main;
    @FXML
    private MenuItem employeeMenu;
    @FXML
    private MenuItem workingTimeMenu;
    @FXML
    private MenuItem exitMenu;
    @FXML
    private MenuItem newMenu;
    @FXML
    private MenuItem openMenu;
    @FXML
    private MenuItem saveMenu;
    @FXML
    private MenuItem saveAsMenu;

    @FXML
    private void onNewMenu() {
        main.getEmployees().clear();
        main.setEmployeeFilePath(null);
    }

    @FXML
    private void onOpenMenu() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(main.getStage());
        if (file != null) {
            main.loadEmployeeDataFromFile(file);
        }
    }

    @FXML
    private void onSaveMenu() throws IOException {
        File file = main.getEmployeeFilePath();
        if (file != null) {
            main.saveEmployeeDataToFile(file);
        } else {
            onSaveAsMenu();
        }
    }

    @FXML
    private void onSaveAsMenu() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(main.getStage());
        if (file != null) {
            if (!file.getPath().endsWith(".json")) {
                file = new File(file.getPath() + ".json");
            }
            main.saveEmployeeDataToFile(file);
        }
    }

    @FXML
    private void onExitMenu() {
        System.exit(0);
    }

    @FXML
    private void onEmployeeMenu() throws IOException {
        main.showEmployeeOverview();
    }

    @FXML
    private void onWorkingTimeMenu() throws IOException {
        main.showWorkingTimeView();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
