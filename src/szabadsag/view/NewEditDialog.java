package szabadsag.view;

import javafx.stage.Stage;
import szabadsag.Main;
import szabadsag.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class NewEditDialog {

    private Stage dialogStage;
    private Employee employee;
    private boolean isOkClicked = false;
    @FXML
    private TextField txBirth;
    @FXML
    private Button btOK;
    @FXML
    private TextField txTempAddress;
    @FXML
    private TextField txSoc;
    @FXML
    private Button btCancel;
    @FXML
    private TextField txWorkPlace;
    @FXML
    private CheckBox chActive;
    @FXML
    private TextField txName;
    @FXML
    private TextField txMothersName;
    @FXML
    private TextField txTax;
    @FXML
    private TextField txPermAddress;

    @FXML
    private void handleOK() {
        employee.setName(txName.getText());
        employee.setBirth(txBirth.getText());
        employee.setMothersName(txMothersName.getText());
        employee.setTaxNumber(txTax.getText());
        employee.setSocialNumber(txSoc.getText());
        employee.setPermAddress(txPermAddress.getText());
        employee.setTempAddress(txTempAddress.getText());
        employee.setWorkPlace(txWorkPlace.getText());
        employee.setActive(chActive.isSelected());

        isOkClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;

        txName.setText(employee.getName());
        txBirth.setText(employee.getBirth());
        txMothersName.setText(employee.getMothersName());
        txTax.setText(employee.getTaxNumber());
        txSoc.setText(employee.getSocialNumber());
        txPermAddress.setText(employee.getPermAddress());
        txTempAddress.setText(employee.getTempAddress());
        txWorkPlace.setText(employee.getWorkPlace());
        chActive.setSelected(employee.isActive());
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }
    public void setOkClicked(boolean okClicked) {
        isOkClicked = okClicked;
    }
}
