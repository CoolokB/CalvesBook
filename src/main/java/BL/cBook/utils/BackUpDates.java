package BL.cBook.utils;

import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class BackUpDates {

    private SimpleObjectProperty<LocalDate> PlaceEndDate = new SimpleObjectProperty<>(LocalDate.now());
    private SimpleObjectProperty<LocalDate> treatmentMilkEndDate = new SimpleObjectProperty<>(LocalDate.now());
    private SimpleObjectProperty<LocalDate> treatmentSlaughterEndDate = new SimpleObjectProperty<>(LocalDate.now());
    private SimpleObjectProperty<LocalDate> feedingEnd = new SimpleObjectProperty<>(LocalDate.now());
    private SimpleObjectProperty<LocalDate> feedingMilkEndDate = new SimpleObjectProperty<>(LocalDate.now());
    private SimpleObjectProperty<LocalDate> feedingSlaughterEndDate = new SimpleObjectProperty<>(LocalDate.now());

}
