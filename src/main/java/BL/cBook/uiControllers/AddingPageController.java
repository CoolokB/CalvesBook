package BL.cBook.uiControllers;

import BL.cBook.databaseModel.DBCalf;
import BL.cBook.databaseModel.Tools;
import BL.cBook.enums.*;
import BL.cBook.model.Calf;
import BL.cBook.services.MyController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class AddingPageController implements MyController {

    @FXML private Button addButton;
    @FXML private TextField idField;
    @FXML private DatePicker dateOfBirthField;
    @FXML private TextField daysOfGestationField;
    @FXML private ChoiceBox<String> genderField;
    @FXML private ChoiceBox<String> breedField;
    @FXML private ChoiceBox<String> placeOfBirthField;
    @FXML private ChoiceBox<String> feedField;
    @FXML private ChoiceBox<String> quantityField;

    private MainController mainController;
    
    @FXML public void initialize(MainController mc) {

        this.mainController = mc;
        OnlyDigitCheck(idField);
        OnlyDigitCheck(daysOfGestationField);
        dateOfBirthField.setPromptText(LocalDate.now().toString());
        genderField.setItems(Gender.getAllValues());
        breedField.setItems(Breed.getAllValues());
        placeOfBirthField.setItems(PlaceOfBirth.getAllValues());
        feedField.setItems(Colostrum_by_bottle.getAllValues());
        quantityField.setItems(Quantity.getAllValues());
    }

    private void OnlyDigitCheck(TextField t) {
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                t.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML private void handleNewCalf() {

        DBCalf dbCalf = new DBCalf();

        dbCalf.setCid(idField.getText());
        dbCalf.setDaysOfGestation(daysOfGestationField.getText());
        dbCalf.setBreed(breedField.getValue());
        dbCalf.setColostrum_by_bottle(feedField.getValue());
        dbCalf.setGender(genderField.getValue());
        dbCalf.setQuantity(quantityField.getValue());
        dbCalf.setDaysOfGestation(daysOfGestationField.getText());

        LocalDate dateOfBirth;
        if (dateOfBirthField.getValue() != null) {
            dateOfBirth = dateOfBirthField.getValue();
        } else dateOfBirth = LocalDate.now();

        dbCalf.setDateOfBirth(dateOfBirth);
        dbCalf.setPlaceOfBirth(placeOfBirthField.getValue());

        mainController.getService().save(dbCalf);
        Calf calf = Tools.Transformer.transform(dbCalf);
        mainController.getCalves().add(calf);
        addButton.getScene().getWindow().hide();
    }
}