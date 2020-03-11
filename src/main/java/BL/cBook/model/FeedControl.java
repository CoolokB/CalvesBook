package BL.cBook.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class FeedControl {

    private int id;

    private SimpleObjectProperty<LocalDate> start = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> end = new SimpleObjectProperty<>();
    private SimpleStringProperty place = new SimpleStringProperty("");
    private SimpleStringProperty foodType = new SimpleStringProperty("Conventional");
    private SimpleStringProperty field = new SimpleStringProperty("");

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getField() {
        return field.get();
    }
    public SimpleStringProperty fieldProperty() {
        return field;
    }
    public void setField(String field) {
        this.field = new SimpleStringProperty(field);
    }
    public LocalDate getStart() {
        return start.get();
    }
    public SimpleObjectProperty<LocalDate> startProperty() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = new SimpleObjectProperty<>(start);
    }
    public LocalDate getEnd() {
        return end.get();
    }
    public SimpleObjectProperty<LocalDate> endProperty() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = new SimpleObjectProperty<>(end);
    }
    public String getPlace() {
        return place.getValue();
    }
    public SimpleStringProperty placeProperty() {
        return place;
    }
    public void setPlace(String place) {
        this.place = new SimpleStringProperty(place);
    }
    public void setFoodType(String foodType) {
        this.foodType.set(foodType);
    }
    public String getFoodType() {
        return foodType.get();
    }
    public SimpleStringProperty foodTypeProperty() {
        return foodType;
    }

}