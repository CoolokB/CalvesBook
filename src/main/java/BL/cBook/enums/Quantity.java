package BL.cBook.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Quantity {

    ONE("SINGLE"),
    TWO("TWIN"),
    THREE("TRIPLET");

    private String text;

    Quantity(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return this.text;
    }

    public static ObservableList<String> getAllValues() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Quantity v : Quantity.values()) {
            list.add(v.getText());
        }
        return list;
    }
}
