package BL.cBook.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum PlaceOfBirth {

    UNKNOWN("UNKNOWN"),
    CALVING_BOX("Calving box"),
    SICK_BOX("Sick box"),
    COWSHED_PASSAGE("Cowshed passage"),
    YOUNGSTOCKSHED("Youngstock shed"),
    FIELD("Field");

    private String text;

    public String getText() {
        return text;
    }

    public static ObservableList<String> getAllValues() {

        ObservableList<String> list = FXCollections.observableArrayList();

        for (PlaceOfBirth value : PlaceOfBirth.values()) {
            list.add(value.text);
        }
        return list;

    }

    PlaceOfBirth(String text) {
        this.text = text;
    }
}
