package BL.cBook.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeathInfo {

    private SimpleStringProperty causeOfDeath = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dateOfDeath = new SimpleObjectProperty<>();
    private SimpleStringProperty slaughterWeight = new SimpleStringProperty("0");
    private SimpleStringProperty deathReason = new SimpleStringProperty("");

    private SimpleStringProperty fatField1 = new SimpleStringProperty("");
    private SimpleStringProperty fatField2 = new SimpleStringProperty("");

    private SimpleStringProperty slaughterField1 = new SimpleStringProperty("");
    private SimpleStringProperty slaughterField2 = new SimpleStringProperty("");

}
