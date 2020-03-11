package BL.cBook.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Gender {

    FEMALE("Female"),
    MALE("Male");

    private String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public static ObservableList<String> getAllValues() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Gender v : Gender.values()) {
            list.add(v.getText());
        }
        return list;
    }
}