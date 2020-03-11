package BL.cBook.model;

import BL.cBook.utils.BackUpDates;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
public class Calf {

    private int id;

    private SimpleStringProperty myId = new SimpleStringProperty("");
    private SimpleStringProperty colostrum_by_bottle = new SimpleStringProperty("");
    private SimpleStringProperty Quantity = new SimpleStringProperty("");
    private SimpleStringProperty gender = new SimpleStringProperty("");
    private SimpleStringProperty daysOfGestation = new SimpleStringProperty("");
    private SimpleStringProperty breed = new SimpleStringProperty("");
    private SimpleStringProperty age = new SimpleStringProperty("");
    private SimpleBooleanProperty Alive = new SimpleBooleanProperty(true);

    private SimpleObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<>();
    private SimpleStringProperty placeOfBirth = new SimpleStringProperty("");

    private BackUpDates backup = new BackUpDates();
    private DeathInfo deathInfo = new DeathInfo();

    private ObservableList<PlaceOfHabitat> places = FXCollections.observableArrayList();
    private ObservableList<Treatment> treatment = FXCollections.observableArrayList();
    private ObservableList<FeedHistory> feedingHistory = FXCollections.observableArrayList();

    public SimpleStringProperty ageProperty() {

        LocalDate today = LocalDate.now();
        LocalDate birthday = dateOfBirth.getValue();

        if (Alive.get()) {
            return new SimpleStringProperty(String.valueOf(ChronoUnit.DAYS.between(birthday,today)));
        } else {
            LocalDate deathDate = deathInfo.getDateOfDeath().get();
            return new SimpleStringProperty(String.valueOf(ChronoUnit.DAYS.between(birthday, deathDate)));
        }

    }

    private SimpleStringProperty lactationNumber = new SimpleStringProperty("");
    private SimpleStringProperty day90 = new SimpleStringProperty("0");

}
