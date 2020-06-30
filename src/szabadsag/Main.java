package szabadsag;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGson;
import szabadsag.model.Employee;
import szabadsag.view.*;

import java.io.*;
import java.util.Collections;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage stage;
    private BorderPane mainLayout;
    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    public Main() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Szabadság App");
        stage.setResizable(false);
        stage.centerOnScreen();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainLayout.fxml"));
        mainLayout = (BorderPane) loader.load();
        MainLayout controller = loader.getController();
        controller.setMain(this);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        File file = getEmployeeFilePath();
        if (file != null) {
            loadEmployeeDataFromFile(file);
        }
    }

    public void showEmployeeOverview() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/EmployeeOverview.fxml"));
        AnchorPane ap = (AnchorPane) loader.load();
        EmployeeOverview controller = loader.getController();
        controller.setMain(this);
        this.mainLayout.setCenter(ap);
        this.stage.setWidth(900);
    }

    public boolean showEmployeeEditDialog(Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/NewEditDialog.fxml"));
        AnchorPane ap = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle(employee.getName());
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.stage);
        Scene scene = new Scene(ap);
        dialogStage.setScene(scene);

        NewEditDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEmployee(employee);

        dialogStage.showAndWait();

        return controller.isOkClicked();
    }

    public void showWorkingTimeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/WorkingTimeView.fxml"));
        AnchorPane ap = (AnchorPane) loader.load();
        WorkingTimeView controller = loader.getController();
        controller.setMain(this);
        this.mainLayout.setCenter(ap);
        this.stage.setWidth(900);
    }

    public File getEmployeeFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setEmployeeFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            this.stage.setTitle("Jelenléti App - " + file.getName());
        } else {
            prefs.remove("filePath");

            this.stage.setTitle("Jelenléti App");
        }
    }



    public void loadEmployeeDataFromFile(File file) throws IOException {
        Gson gson = FxGson.create();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        this.employees = gson.fromJson(reader, new TypeToken<ObservableList<Employee>>() {}.getType());
        reader.close();
        setEmployeeFilePath(file);
    }

    public void saveEmployeeDataToFile(File file) throws IOException {
        Gson gson = FxGson.create();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        gson.toJson(this.employees, writer);
        writer.close();
        setEmployeeFilePath(file);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ObservableList<Employee> employees) {
        this.employees = employees;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
