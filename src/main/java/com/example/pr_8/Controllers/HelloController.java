package com.example.pr_8.Controllers;

import com.example.pr_8.Log;
import com.example.pr_8.MainApp;
import com.example.pr_8.Pet;
import com.example.pr_8.Vaccine;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class HelloController {
    // список питомцев
    @FXML
    private TableView<Pet> petsTable;
    @FXML
    private TableColumn<Pet, String> NameColumn;
    @FXML
    private TableColumn<Pet, String> TypeColumn;

    // список прививок для каждого питомца
    private ObservableList<Vaccine> vaccinesData = FXCollections.observableArrayList();

    // таблица прививок
    @FXML
    private TableView<Vaccine> vaccinesTable;
    @FXML
    private TableColumn<Vaccine, LocalDate> DateOfVaccination;
    @FXML
    private TableColumn<Vaccine, StringProperty> TypeV;
    @FXML
    private TableColumn<Vaccine, String> NameV;

    // информация о питомце
    @FXML
    private Label NameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label BirthDateLabel;


    private MainApp mainApp;
    public HelloController() { }

    @FXML
    private void initialize() {
        // заполнение табилцы питомцов
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TypeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        showPetsDetails(null);
        petsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPetsDetails(newValue));

        // устанавливаем тип и значение которое должно хранится в колонке
        DateOfVaccination.setCellValueFactory(new PropertyValueFactory<>("DateOfVaccination"));
        TypeV.setCellValueFactory(new PropertyValueFactory<>("TypeV"));
        NameV.setCellValueFactory(new PropertyValueFactory<>("NameV"));

        // Вывод таблицы прививок
        vaccinesTable.setItems(vaccinesData);
    }

    // кнопка "Добавить питомца"
    public void addPet(ActionEvent event){
        try{
            Log.writeInto("[HelloController][addPet] - открыто окно добавление питомца");
            Stage addingStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("AddingMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            addingStage.setTitle("Adding a pet!");
            addingStage.setScene(scene);
            addingStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // кнопка "Добавить прививку"
    public void addVaccine(ActionEvent event){
        if (MainApp.activePet == null) return;
        try{
            Log.writeInto("[HelloController][addVaccine] - открыто окно добавление прививки");
            Stage addingStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("AddVaccine.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            addingStage.setTitle("Adding a vaccine!");
            addingStage.setScene(scene);
            addingStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // кнопка "Удалить питомца"
    public void removePet(ActionEvent event){
        MainApp.petsData.removeAll(MainApp.activePet);
        Log.writeInto("[HelloController][removePet] - питомец '" + MainApp.activePet.getName().toString() + "' удален из списка");
        initialize();
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Вывод таблицы питомцов
        petsTable.setItems(mainApp.getPetsData());
    }

    private void showPetsDetails(Pet pet){
        if (pet != null){
            MainApp.activePet = pet;

            // очищаем таблицу прививок
            vaccinesTable.getItems().clear();
            vaccinesData.clear();

            NameLabel.setText(pet.getName());
            ageLabel.setText(pet.ageProperty());
            BirthDateLabel.setText(pet.getBhday().toString());
            pet.getVaccine().forEach(x -> vaccinesData.add(x));
        }
        else {
            // очищаем таблицу прививок
            vaccinesTable.getItems().clear();
            vaccinesData.clear();

            NameLabel.setText("");
            ageLabel.setText("");
            BirthDateLabel.setText("");
        }
    }
}
