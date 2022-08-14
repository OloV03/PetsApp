package com.example.pr_8;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Pet {
    Random random = new Random();
    private final StringProperty Name;
    private final StringProperty Type;
    private final ObjectProperty<LocalDate> BirthDate;
    private int age;
    private final StringProperty ageLabel;
    private final ArrayList<Vaccine> vaccines = new ArrayList<Vaccine>();

    int minDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
    int maxDay = (int) LocalDate.of(2021, 1, 1).toEpochDay();
    int randomDay = minDay + random.nextInt(maxDay - minDay);

    public Pet(String Name, String Type){
        this.Name = new SimpleStringProperty(Name);
        this.Type = new SimpleStringProperty(Type);
        this.age = ThreadLocalRandom.current().nextInt(1,5);
        ageLabel = new SimpleStringProperty(String.valueOf(age)+" в животных годах");
        this.BirthDate = new SimpleObjectProperty<LocalDate>(LocalDate.ofEpochDay(randomDay));
        getVaccines();

        Log.writeInto("[Pet][Конструктор] - создан новый питомец '" + Name.toString()+"'");
    }

    public Pet(String Name, String Type, LocalDate date){
        this.Name = new SimpleStringProperty(Name);
        this.Type = new SimpleStringProperty(Type);
        this.age = ThreadLocalRandom.current().nextInt(1,5);
        ageLabel = new SimpleStringProperty(String.valueOf(age)+" в животных годах");
        this.BirthDate = new SimpleObjectProperty<LocalDate>(date);

        Log.writeInto("[Pet][Конструктор] - создан новый питомец '" + Name.toString()+"'");
    }

    // присвоение случайных вакцин
    public void getVaccines(){
        int count = ThreadLocalRandom.current().nextInt(0,5);
        for (int i = 0; i < count; i++){
            vaccines.add(new Vaccine());
        }
    }

    public ArrayList<Vaccine> getVaccine(){
        return vaccines;
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public StringProperty typeProperty() {
        return Type;
    }

    public String ageProperty() {
        return ageLabel.get();
    }

    public LocalDate getBhday() {
        return BirthDate.get();
    }

    // добавляеем новую прививку питомцу
    public void addVaccine(String name, String type, LocalDate date){
        vaccines.add(new Vaccine(name, type, date));
        Log.writeInto("[Pet][addVaccine] - питомцу '" + Name.toString() + "' добавлена новая прививка '" + name + "'");
    }
}
