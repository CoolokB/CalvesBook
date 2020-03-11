package BL.cBook.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Treatment {

    private SimpleStringProperty Amount = new SimpleStringProperty("");
    private SimpleStringProperty Occurrence = new SimpleStringProperty("");
    private SimpleStringProperty Medicine = new SimpleStringProperty("");
    private SimpleStringProperty WithdrawalDaysMilk = new SimpleStringProperty("");
    private SimpleStringProperty WithdrawalDaysSlaughter = new SimpleStringProperty("");
    private SimpleStringProperty Age = new SimpleStringProperty("");

    private SimpleObjectProperty<LocalDate> Date = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> WithdrawalUntilMilk = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> WithdrawalUntilSlaughter = new SimpleObjectProperty<>();

}
