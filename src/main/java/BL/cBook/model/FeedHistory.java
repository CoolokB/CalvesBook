package BL.cBook.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FeedHistory {

    private SimpleObjectProperty<LocalDate> start = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> end = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> withdrawalMilk = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> withdrawalSlaughter = new SimpleObjectProperty<>();
    private SimpleStringProperty place = new SimpleStringProperty("");
    private SimpleStringProperty foodType = new SimpleStringProperty("Conventional");
    private SimpleStringProperty field = new SimpleStringProperty("");

}

