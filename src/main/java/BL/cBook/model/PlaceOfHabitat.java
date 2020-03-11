package BL.cBook.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class PlaceOfHabitat {

    private SimpleObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private SimpleStringProperty place = new SimpleStringProperty("");
    private SimpleStringProperty grazing_field_number = new SimpleStringProperty("");

}
