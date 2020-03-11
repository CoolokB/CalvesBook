package BL.cBook.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Breed {

    SLB("SLB"),
    SRB("SRB"),
    SLBXSRB("SLBXSRB"),
    XJER("XJER"),
    XSKB("XSKB"),
    XAA("XAA"),
    XCHAR("XCHAR"),
    XHER("XHER"),
    XLIM("XLIM");

    private String text;

    Breed(String text) {
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
        for (Breed v : Breed.values()) {
            list.add(v.getText());
        }
        return list;
    }
}
