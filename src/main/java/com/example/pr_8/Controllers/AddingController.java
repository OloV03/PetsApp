package com.example.pr_8.Controllers;

import com.example.pr_8.Log;
import com.example.pr_8.MainApp;
import com.example.pr_8.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddingController {
    @FXML
    private Button cancelButton;

    @FXML
    private TextField newName;
    @FXML
    private TextField newType;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField vaccineName;
    @FXML
    private DatePicker vaccineDate;

    // кнопка "Отмена"
    public void cancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Log.writeInto("[AddingController][cancelButtonClick] - закрытие окна добавления питомца");
        stage.close();
    }

    // кнопка "Добавить питомца"
    public void addButtonClick(ActionEvent actionEvent) {
        if (newName.getText() != null && birthDate.getValue() != null && newType.getText() != null) {
            Pet newPet = new Pet(newName.getText(), newType.getText(), birthDate.getValue());

            if (vaccineName.getText() != "") {
                newPet.addVaccine(vaccineName.getText(), "По желанию владельца", vaccineDate.getValue());
            }
            MainApp.petsData.add(newPet);

            // закрываем окно
            Log.writeInto("[AddingController][addButtonClick] - новый питомец добавлен, закрытие окна добавление питомца");
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
