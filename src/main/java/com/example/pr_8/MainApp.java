package com.example.pr_8;

import com.example.pr_8.Controllers.HelloController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    public static Pet activePet;

    @Override
    public void start(Stage primaryStage) {
        Thread log = new Thread(new AutoSaveLogWorker());  // поток для сохранения логов
        log.start();

        Log.writeInto("Приложение запущено");

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Meow!");
        this.primaryStage.getIcons().add(new Image("file:resourses/images/fish.png"));

        showPetsOverview();
    }


    // отображение основного окна
    public void showPetsOverview() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("hello.fxml"));
            AnchorPane petsOverview = (AnchorPane) fxmlLoader.load();
            Scene scene = new Scene(petsOverview);
            HelloController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // список имеющихся питомцев
    public static ObservableList<Pet> petsData = FXCollections.observableArrayList();

    public MainApp(){
        petsData.add(new Pet("Bonya", "Кiшка"));
        petsData.add(new Pet("Push'ok", "Кiшка"));
        petsData.add(new Pet("Овальчик", "Собакен"));
        petsData.add(new Pet("Марк", "Кiшка"));
    }

    public ObservableList<Pet> getPetsData() {
        return petsData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}