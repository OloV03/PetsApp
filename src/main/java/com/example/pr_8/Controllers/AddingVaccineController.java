package com.example.pr_8.Controllers;

import com.example.pr_8.Log;
import com.example.pr_8.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddingVaccineController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField vaccineName;
    @FXML
    private TextField vaccineType;
    @FXML
    private DatePicker vaccineDate;

    // кнопка "Отмена"
    public void cancelButtonClick(ActionEvent actionEvent) {
        Log.writeInto("[AddingVaccineController][cancelButtonClick] - закрытие окна добавления прививки");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // кнопка "Добавить прививку"
    public void addButtonClick(ActionEvent actionEvent) {
        if (vaccineName.getText() != null && vaccineDate.getValue() != null && vaccineType.getText() != null) {
            MainApp.activePet.addVaccine(vaccineName.getText(), vaccineType.getText(), vaccineDate.getValue());

            // закрываем окно
            Log.writeInto("[AddingVaccineController][addButtonClick] - прививка добавлена, закрытие окна добавления прививки");
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Введены некорректные данные");
            alert.setHeaderText("Попробуйте ещё раз.");
            alert.setContentText("В одно или все поля были введены неправильные данные, попробуйте ещё раз.");
            alert.showAndWait();
        }
    }
}
