package szabadsag.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class Employee implements Comparable<Employee>{

    private StringProperty name;
    private StringProperty birth;
    private StringProperty mothersName;
    private StringProperty taxNumber;
    private StringProperty socialNumber;
    private StringProperty permAddress;
    private StringProperty tempAddress;
    private StringProperty workPlace;
    private BooleanProperty active;
    private ObservableList<Day> days;

    public Employee(String name, String birth, String mothersName,
                    String taxNumber, String socialNumber, String permAddress,
                    String tempAddress, String workPlace, boolean active) {
        this.name = new SimpleStringProperty(name);
        this.birth = new SimpleStringProperty(birth);
        this.mothersName = new SimpleStringProperty(mothersName);
        this.taxNumber = new SimpleStringProperty(taxNumber);
        this.socialNumber = new SimpleStringProperty(socialNumber);
        this.permAddress = new SimpleStringProperty(permAddress);
        this.tempAddress = new SimpleStringProperty(tempAddress);
        this.workPlace = new SimpleStringProperty(workPlace);
        this.active = new SimpleBooleanProperty(active);
        this.days = FXCollections.observableArrayList();
    }

    public Employee(String name) {
        this(name, "", "", "", "", "", "", "", false);
        this.days = FXCollections.observableArrayList();
    }

    public Employee() {
        this("", "", "", "", "", "", "", "", false);
        this.days = FXCollections.observableArrayList();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBirth() {
        return birth.get();
    }

    public StringProperty birthProperty() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth.set(birth);
    }

    public String getMothersName() {
        return mothersName.get();
    }

    public StringProperty mothersNameProperty() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName.set(mothersName);
    }

    public String getTaxNumber() {
        return taxNumber.get();
    }

    public StringProperty taxNumberProperty() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber.set(taxNumber);
    }

    public String getSocialNumber() {
        return socialNumber.get();
    }

    public StringProperty socialNumberProperty() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber.set(socialNumber);
    }

    public String getPermAddress() {
        return permAddress.get();
    }

    public StringProperty permAddressProperty() {
        return permAddress;
    }

    public void setPermAddress(String permAddress) {
        this.permAddress.set(permAddress);
    }

    public String getTempAddress() {
        return tempAddress.get();
    }

    public StringProperty tempAddressProperty() {
        return tempAddress;
    }

    public void setTempAddress(String tempAddress) {
        this.tempAddress.set(tempAddress);
    }

    public String getWorkPlace() {
        return workPlace.get();
    }

    public StringProperty workPlaceProperty() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace.set(workPlace);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public ObservableList<Day> getDays() {
        return days;
    }

    public void setDays(ObservableList<Day> days) {
        this.days = days;
    }

    public void addDay(Day day) {
        this.days.add(day);
    }

    @Override
    public int compareTo(Employee o) {
        Employee employee = (Employee) o;
        return this.getName().compareTo(employee.getName());
    }
}
