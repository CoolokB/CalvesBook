package BL.cBook.uiControllers;

import BL.cBook.services.MyController;
import BL.cBook.utils.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChartConstructorController implements MyController {

    @FXML
    private Button build;

    @FXML private DatePicker to;
    @FXML private DatePicker from;

    @FXML private RadioButton radio1;
    @FXML private RadioButton radio2;

    @FXML private TitledPane genderPane;
    @FXML private TitledPane breedPane;
    @FXML private TitledPane healthPane;
    @FXML private TitledPane placePane;
    @FXML private TitledPane gestationPane;

    @FXML private CheckBox Diarrhoea;
    @FXML private CheckBox Pneumonia;
    @FXML private CheckBox Both;
    @FXML private CheckBox Neither;

    @FXML private CheckBox UNKNOWN;
    @FXML private CheckBox CALVING_BOX;
    @FXML private CheckBox SICK_BOX;
    @FXML private CheckBox COWSHED_PASSAGE;
    @FXML private CheckBox YOUNGSTOCKSHED;
    @FXML private CheckBox FIELD;

    @FXML private CheckBox gestationA;
    @FXML private CheckBox gestationB;
    @FXML private CheckBox gestationC;
    @FXML private CheckBox gestationD;
    @FXML private CheckBox gestationE;
    @FXML private CheckBox gestationF;
    @FXML private CheckBox gestationG;

    @FXML private CheckBox genderA;
    @FXML private CheckBox genderB;
    @FXML private CheckBox genderC;

    @FXML private CheckBox SLB;
    @FXML private CheckBox SRB;
    @FXML private CheckBox SLBXSRB;
    @FXML private CheckBox XCHAR;
    @FXML private CheckBox XHER;
    @FXML private CheckBox XAA;
    @FXML private CheckBox XJER;
    @FXML private CheckBox XSKB;

    private static int counter1;
    private static int counter2;
    private static int counter3;
    private static int counter4;
    private static int counter5;

    private Set<String> health;
    private Set<String> place;
    private Set<String> gestation;
    private Set<String> Gender;
    private Set<String> Breed;

    public void initialize(MainController mc) {

        health = new HashSet<>();
        place = new HashSet<>();
        gestation = new HashSet<>();
        Gender = new HashSet<>();
        Breed = new HashSet<>();

        radio1.setOnAction(a -> {
            if (radio1.isArmed()) {
                genderPane.setVisible(false);
                breedPane.setVisible(false);
            }
        });
        radio2.setOnAction(a -> {
            if (radio2.isArmed()) {
                genderPane.setVisible(true);
                breedPane.setVisible(true);
            }
        });

        func(Diarrhoea, health, 1);
        func(Pneumonia, health, 1);
        func(Both, health, 1);
        func(Neither, health, 1);

        func(UNKNOWN, place, 2);
        func(CALVING_BOX, place, 2);
        func(SICK_BOX, place, 2);
        func(COWSHED_PASSAGE, place, 2);
        func(YOUNGSTOCKSHED, place, 2);
        func(FIELD, place, 2);

        func(gestationA, gestation, 3);
        func(gestationB, gestation, 3);
        func(gestationC, gestation, 3);
        func(gestationD, gestation, 3);
        func(gestationE, gestation, 3);
        func(gestationF, gestation, 3);
        func(gestationG, gestation, 3);

        func(genderA, Gender, 4);
        func(genderB, Gender, 4);
        func(genderC, Gender, 4);

        func(SLB, Breed, 5);
        func(SRB, Breed, 5);
        func(SLBXSRB, Breed, 5);
        func(XCHAR, Breed, 5);
        func(XHER, Breed, 5);
        func(XAA, Breed, 5);
        func(XJER, Breed, 5);
        func(XSKB, Breed, 5);

        build.setOnAction(s -> {

            ParametersContainer container = ParametersContainer.getContainer();

            boolean condition1 = radio1.isSelected();
            boolean condition2 = radio2.isSelected();
            boolean condition3 = from.getValue() != null;
            boolean condition4 = to.getValue() != null;

            if (condition1) { container.setChartButton(1); }
            if (condition2) { container.setChartButton(1); }

            if (condition3) {
               container.setChartFrom(from.getValue());
            } else {
                container.setChartFrom(LocalDate.now());
            }

            if (condition4) {
                container.setChartTo(to.getValue());
            } else {
                container.setChartTo(LocalDate.now());
            }

            if (health.size() > 0) {container.setChartSet(health); }
            if (place.size() > 0) { container.setChartSet(place); }
            if (gestation.size() > 0) { container.setChartSet(gestation); }
            if (Gender.size() > 0) { container.setChartSet(Gender); }
            if (Breed.size() > 0) { container.setChartSet(Breed); }

            if ((condition1||condition2)&&condition3&&condition4) {
                Loader.load(mc, "/cards/Chart.fxml", "Chart", container);
            }

        });
    }

    private void func(CheckBox checkBox, Set<String> set, int counter) {

        checkBox.setOnAction(a -> {
            if (checkBox.isSelected()) {
                switch (counter) {
                    case 1: {
                        counter1++;
                        disableOther1();
                    }
                    break;
                    case 2: {
                        counter2++;
                        disableOther2();
                    }
                    break;
                    case 3: {
                        counter3++;
                        disableOther3();
                    }
                    break;
                    case 4: {
                        counter4++;
                        disableOther4();
                    }
                    break;
                    case 5: {
                        counter5++;
                        disableOther5();
                    }
                    break;
                }
                set.add(checkBox.getText());
            } else {
                switch (counter) {
                    case 1: {
                        counter1--;
                        if (counter1 == 0) ableOther1();
                    }
                    break;
                    case 2: {
                        counter2--;
                        if (counter2 == 0) ableOther2();
                    }
                    break;
                    case 3: {
                        counter3--;
                        if (counter3 == 0) ableOther3();
                    }
                    break;
                    case 4: {
                        counter4--;
                        if (counter4 == 0) ableOther4();
                    }
                    break;
                    case 5: {
                        counter5--;
                        if (counter5 == 0) ableOther5();
                    }
                    break;
                }
                set.remove(checkBox.getText());
            }
        });
    }

    private void ableOther1() {
        genderPane.setVisible(true);
        breedPane.setVisible(true);
        placePane.setVisible(true);
        gestationPane.setVisible(true);
    }

    private void ableOther2() {
        healthPane.setVisible(true);
        breedPane.setVisible(true);
        genderPane.setVisible(true);
        gestationPane.setVisible(true);
    }

    private void ableOther3() {
        healthPane.setVisible(true);
        breedPane.setVisible(true);
        genderPane.setVisible(true);
        placePane.setVisible(true);
    }

    private void ableOther4() {
        healthPane.setVisible(true);
        breedPane.setVisible(true);
        gestationPane.setVisible(true);
        placePane.setVisible(true);
    }

    private void ableOther5() {
        healthPane.setVisible(true);
        genderPane.setVisible(true);
        gestationPane.setVisible(true);
        placePane.setVisible(true);
    }

    private void disableOther1() {
        genderPane.setVisible(false);
        breedPane.setVisible(false);
        placePane.setVisible(false);
        gestationPane.setVisible(false);
    }

    private void disableOther2() {
        healthPane.setVisible(false);
        breedPane.setVisible(false);
        genderPane.setVisible(false);
        gestationPane.setVisible(false);
    }

    private void disableOther3() {
        healthPane.setVisible(false);
        breedPane.setVisible(false);
        genderPane.setVisible(false);
        placePane.setVisible(false);
    }

    private void disableOther4() {
        healthPane.setVisible(false);
        breedPane.setVisible(false);
        gestationPane.setVisible(false);
        placePane.setVisible(false);
    }

    private void disableOther5() {
        healthPane.setVisible(false);
        genderPane.setVisible(false);
        gestationPane.setVisible(false);
        placePane.setVisible(false);
    }

}
