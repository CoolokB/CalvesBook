package BL.cBook.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Colostrum_by_bottle {

    UNKNOWN("UNKNOWN"),
    YES("YES"),
    NO("NO");

    private String text;

    Colostrum_by_bottle(String text) {
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
        for (Colostrum_by_bottle v : Colostrum_by_bottle.values()) {
            list.add(v.getText());
        }
        return list;
    }
}
