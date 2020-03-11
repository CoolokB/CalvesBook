package BL.cBook.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Place {

    WITHCOW("WITHCOW"),
    SINGLEBOX("SINGLEBOX"),
    MILKBOX1("MILKBOX1"),
    MILKBOX2("MILKBOX2"),
    CALFSHED("CALFSHED"),
    YOUNGSTOCKSHED1("YOUNGSTOCKSHED1"),
    YOUNGSTOCKSHED2("YOUNGSTOCKSHED2"),
    YOUNGSTOCKSHED3("YOUNGSTOCKSHED3"),
    YOUNGSTOCKSHED4("YOUNGSTOCKSHED4"),
    YOUNGSTOCKSHED5("YOUNGSTOCKSHED5"),
    YOUNGSTOCKSHED6("YOUNGSTOCKSHED6"),
    COWSHED("COWSHED"),
    GRAZING("GRAZING"),
    ElectricFenceTraining("ElectricFenceTraining");

    private String text;

    Place(String text) {
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
        for (Place v : Place.values()) {
            list.add(v.getText());
        }
        return list;
    }
}
