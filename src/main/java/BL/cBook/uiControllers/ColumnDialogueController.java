package BL.cBook.uiControllers;

import BL.cBook.model.Calf;
import BL.cBook.model.FeedHistory;
import BL.cBook.model.PlaceOfHabitat;
import BL.cBook.model.Treatment;
import BL.cBook.services.MyControllerWithParameter;
import BL.cBook.utils.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@Getter
public class ColumnDialogueController implements MyControllerWithParameter<ParametersContainer> {

    public AnchorPane pane;

    public CheckBox DateOfBirth;
    public CheckBox GestationDays;
    public CheckBox Quantity;
    public CheckBox Age;
    public CheckBox LactationN;
    public CheckBox Breed;
    public CheckBox dayMilk90;
    public CheckBox Birthplace;
    public CheckBox Colostrum;
    public CheckBox Gender;

    public CheckBox DeathDate;
    public CheckBox SlaughterWeight;
    public CheckBox SlaughterClassF1;
    public CheckBox SlaughterClassF2;
    public CheckBox DeathCause;
    public CheckBox DeathReason;
    public CheckBox FatClassF1;
    public CheckBox FatClassF2;

    public CheckBox PlaceStartDate;
    public CheckBox PlaceEndDate;
    public CheckBox PlacePlace;
    public CheckBox GrazingN;

    public CheckBox FeedFeed;
    public CheckBox FeedPlace;
    public CheckBox FeedField;
    public CheckBox FeedEndDate;
    public CheckBox FeedStartDate;
    public CheckBox FeedWithdrawalMilk;
    public CheckBox FeedWithdrawalSlaughter;

    public CheckBox TreatmentDate;
    public CheckBox TreatmentOccurrence;
    public CheckBox TreatmentMedicine;
    public CheckBox TreatmentAmount;
    public CheckBox WithdrawalDaysMilk;
    public CheckBox WithdrawalDaysSlaughter;
    public CheckBox WithdrawalUntilMilk;
    public CheckBox WithdrawalUntilSlaughter;
    public CheckBox tAge;

    public Label column1;
    public Label column2;
    public Label column3;
    public Label column4;
    public Label column5;
    public Label column6;
    public Label column7;
    public Label column8;
    public Label column9;
    public GridPane grid;

    private static int selectedLabelsCount;
    private MainController mainController;

    private List<Label> labels;
    private List<CheckBox> checkBoxList;
    private LinkedHashSet<String> columnsToPrint;
    private List<List<String>> rowsList;
    private ParametersContainer container;

    public void initialize(MainController mc,ParametersContainer container) {

        this.mainController = mc;
        this.container = container;

        labels = new ArrayList<>();
        columnsToPrint = new LinkedHashSet<>(9);
        rowsList = new ArrayList<>();

        labels.add(column1);
        labels.add(column2);
        labels.add(column3);
        labels.add(column4);
        labels.add(column5);
        labels.add(column6);
        labels.add(column7);
        labels.add(column8);
        labels.add(column9);

        checkBoxList = Arrays.asList(DateOfBirth, GestationDays, Quantity, Age, LactationN, Breed, dayMilk90, Birthplace, Colostrum, Gender,
                DeathDate, SlaughterWeight, SlaughterClassF1, SlaughterClassF2, DeathCause, DeathReason, FatClassF1, FatClassF2,
                PlaceStartDate, PlaceEndDate, PlacePlace, GrazingN,
                FeedPlace, FeedField, FeedStartDate, FeedFeed, FeedWithdrawalMilk, FeedWithdrawalSlaughter, FeedEndDate,
                TreatmentDate, TreatmentOccurrence, TreatmentMedicine, TreatmentAmount, WithdrawalDaysMilk, WithdrawalDaysSlaughter,
                WithdrawalUntilMilk, WithdrawalUntilSlaughter, tAge
        );

        MakeBox makeBox = x -> {
            if (x.isSelected()) {
                selectedLabelsCount++;
                columnsToPrint.add(x.getText());
                pane.getScene().getWindow().setOnCloseRequest((a) -> cancel());
            } else {
                selectedLabelsCount--;
                columnsToPrint.remove(x.getText());
                labels.forEach(l -> l.setText(""));
            }

            turnOff();
        };

        for (CheckBox checkBox : checkBoxList) {
            checkBox.setOnAction(e -> makeBox.make(checkBox));
        }
    }

    private void turnOff() {

        for (int i = 0; i < columnsToPrint.size(); i++) {
            labels.get(i).setText(columnsToPrint.toArray()[i].toString());
        }
        if (selectedLabelsCount > 8) {
            grid.setDisable(true);

        }
    }

    @FXML private void cancel() {

        grid.setDisable(false);
        selectedLabelsCount = 0;
        checkBoxList.forEach(s -> s.setSelected(false));
        for (Label label : labels) {
            label.setText("");
        }
        columnsToPrint.clear();
        rowsList.clear();
    }

    @FXML private void ok() {

        List<Calf> filteredCalves = new ArrayList<>(mainController.getCalvesFilteredList());

        int amount;

        if (selectedLabelsCount < 6) {
            amount = 46;
        } else {
            amount = 31;
        }

        if (filteredCalves.size() < amount + 1) {
            makePrintPage(filteredCalves);
        } else {
            while (filteredCalves.size() > amount) {

                List<Calf> temp = filteredCalves.subList(0, amount);
                makePrintPage(temp);
                filteredCalves.removeAll(temp);
                container.setRowsList(rowsList);

                if (selectedLabelsCount < 6) { loadPortrait(); }
                else { loadLandscape(); }
            }

        }
        makePrintPage(filteredCalves);
        container.setRowsList(rowsList);

        if (selectedLabelsCount < 6) { loadPortrait(); }
        else { loadLandscape(); }
    }

    private void loadPortrait() {
        container.setLabelList( Arrays.asList("Id", column1.getText(), column2.getText(), column3.getText(), column4.getText(),
                column5.getText()));

        Loader.load(mainController, "/cards/PrintPage.fxml", "Printer", container);
    }

    private void loadLandscape() {
        container.setLabelList(Arrays.asList("Id", column1.getText(), column2.getText(), column3.getText(), column4.getText(),
                column5.getText(), column6.getText(), column7.getText(), column8.getText(), column9.getText()));

        Loader.load(mainController, "/cards/PrintPage2.fxml", "Printer", container);
    }

    private String getAnswer(String column, Calf calf, int pIndex, int fIndex, int tIndex) {


        String s = "";

        if (column.equals(DateOfBirth.getText())) {
            s = calf.getDateOfBirth().get().toString();
        }
        if (column.equals(GestationDays.getText())) {
            s = calf.getDaysOfGestation().getValue();
        }
        if (column.equals(Quantity.getText())) {
            s = calf.getQuantity().getValue();
        }
        if (column.equals(Age.getText())) {
            s = calf.ageProperty().getValue();
        }
        if (column.equals(LactationN.getText())) {
            s = calf.getLactationNumber().getValue();
        }

        if (column.equals(Breed.getText())) {
            s = calf.getBreed().getValue();
        }
        if (column.equals(dayMilk90.getText())) {
            s = calf.getDay90().getValue();
        }
        if (column.equals(Birthplace.getText())) {
            s = calf.getPlaceOfBirth().getValue();
        }
        if (column.equals(Colostrum.getText())) {
            s = calf.getColostrum_by_bottle().getValue();
        }
        if (column.equals(Gender.getText())) {
            s = calf.getGender().getValue();
        }
        if (column.equals(DeathDate.getText())) {
            if (!calf.getAlive().getValue()) {
                s = calf.getDeathInfo().getDateOfDeath().get().toString();
            }
        }
        if (column.equals(SlaughterWeight.getText())) {
            s = calf.getDeathInfo().getSlaughterWeight().getValue();
        }
        if (column.equals(SlaughterClassF1.getText())) {
            s = calf.getDeathInfo().getSlaughterField1().getValue();
        }
        if (column.equals(SlaughterClassF2.getText())) {
            s = calf.getDeathInfo().getSlaughterField2().getValue();
        }
        if (column.equals(DeathCause.getText())) {
            s = calf.getDeathInfo().getCauseOfDeath().getValue();
        }
        if (column.equals(DeathReason.getText())) {
            s = calf.getDeathInfo().getDeathReason().getValue();
        }
        if (column.equals(FatClassF1.getText())) {
            s = calf.getDeathInfo().getFatField1().getValue();
        }
        if (column.equals(FatClassF2.getText())) {
            s = calf.getDeathInfo().getFatField2().getValue();
        }

        if (calf.getFeedingHistory().size() > 0) {
            if (column.equals(FeedPlace.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getPlace().getValue();
            }
            if (column.equals(FeedField.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getField().getValue();
            }
            if (column.equals(FeedStartDate.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getStart().get().toString();
            }
            if (column.equals(FeedFeed.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getFoodType().getValue();
            }
            if (column.equals(FeedWithdrawalMilk.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getWithdrawalMilk().get().toString();
            }
            if (column.equals(FeedWithdrawalSlaughter.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getWithdrawalSlaughter().get().toString();
            }
            if (column.equals(FeedEndDate.getText())) {
                s = calf.getFeedingHistory().get(fIndex).getEnd().get().toString();
            }
        }

        if (calf.getTreatment().size() > 0) {
            if (column.equals(TreatmentDate.getText())) {
                s = calf.getTreatment().get(tIndex).getDate().toString();
            }
            if (column.equals(TreatmentOccurrence.getText())) {
                s = calf.getTreatment().get(tIndex).getOccurrence().getValue();
            }
            if (column.equals(TreatmentMedicine.getText())) {
                s = calf.getTreatment().get(tIndex).getMedicine().getValue();
            }
            if (column.equals(TreatmentAmount.getText())) {
                s = calf.getTreatment().get(tIndex).getAmount().getValue();
            }
            if (column.equals(WithdrawalDaysMilk.getText())) {
                s = calf.getTreatment().get(tIndex).getWithdrawalDaysMilk().getValue();
            }
            if (column.equals(WithdrawalDaysSlaughter.getText())) {
                s = calf.getTreatment().get(tIndex).getWithdrawalDaysSlaughter().getValue();
            }
            if (column.equals(WithdrawalUntilMilk.getText())) {
                s = calf.getTreatment().get(tIndex).getWithdrawalUntilMilk().toString();
            }
            if (column.equals(WithdrawalUntilSlaughter.getText())) {
                s = calf.getTreatment().get(tIndex).getWithdrawalUntilSlaughter().toString();
            }
            if (column.equals(tAge.getText())) {
                s = calf.getTreatment().get(tIndex).getAge().getValue();
            }
        }

        if (calf.getPlaces().size() > 0) {
            if (column.equals(PlaceStartDate.getText())) {
                s = calf.getPlaces().get(pIndex).getStartDate().get().toString();
            }
            if (column.equals(PlaceEndDate.getText())) {
                s = calf.getPlaces().get(pIndex).getEndDate().get().toString();
            }
            if (column.equals(PlacePlace.getText())) {
                s = calf.getPlaces().get(pIndex).getPlace().getValue();
            }
            if (column.equals(GrazingN.getText())) {
                s = calf.getPlaces().get(pIndex).getGrazing_field_number().getValue();
            }
        }

        return s;
    }

    private void makePrintPage(List<Calf> filteredCalves) {

        rowsList.clear();
        for (Calf calf : filteredCalves) {

            List<String> answers = new ArrayList<>();

            answers.add(calf.getMyId().getValue());

            int placeIndex = getPlaceIndex(calf);
            int feedIndex = getFeedIndex(calf);
            int treatIndex = getTreatmentIndex(calf);

            for (String columnToPrint : columnsToPrint) {
                answers.add(getAnswer(columnToPrint, calf, placeIndex, feedIndex, treatIndex));
            }
           rowsList.add(answers);
        }

    }

    private int getFeedIndex(Calf calf) {
        int feedIndex = 0;
        for (int i = 0; i < calf.getFeedingHistory().size(); i++) {
            if (feedEquality(calf.getFeedingHistory().get(i))) {
                feedIndex = i;
            }
        }
        return feedIndex;
    }

    private int getPlaceIndex(Calf calf) {
        int placeIndex = 0;
        for (int i = 0; i < calf.getPlaces().size(); i++) {
            if (placeEquality(calf.getPlaces().get(i))) {
                placeIndex = i;
            }
        }
        return placeIndex;
    }

    private int getTreatmentIndex(Calf calf) {
        int treatIndex = 0;
        for (int i = 0; i < calf.getTreatment().size(); i++) {
            if (treatmentEquality(calf.getTreatment().get(i))) {
                treatIndex = i;
            }
        }
        return treatIndex;
    }

    private boolean placeEquality(PlaceOfHabitat place) {

        String placeString= container.getPlaceOfHabitat();
        LocalDate placeStart = container.getPlaceDateFrom();
        LocalDate placeEnd = container.getPlaceDateTo();
        String field = container.getPlaceGrazingField();

        boolean condition1;
        if (placeStart != null) {
            condition1 = placeStart.isBefore(place.getEndDate().getValue()) || placeStart.isEqual(place.getEndDate().getValue());
        } else condition1 = true;


        boolean condition2;
        if (placeEnd != null) {
            condition2 = placeEnd.isEqual(place.getStartDate().getValue()) || placeEnd.isAfter(place.getStartDate().getValue());
        } else condition2 = true;


        boolean condition3;
        if (placeString != null) {
            condition3 = place.getPlace().getValue().equals(placeString);
        } else condition3 = true;


        boolean condition4;
        if (field != null) {
            condition4 = place.getGrazing_field_number().getValue().equals(field);
        } else condition4 = true;

        return condition1 && condition2 && condition3 && condition4;
    }

    private boolean feedEquality(FeedHistory feed) {

        String placeString = container.getFeedPlace();
        LocalDate placeStart = container.getFeedStartDate();
        LocalDate placeEnd = container.getFeedEndDate();
        String field = container.getFeedGrazingField();

        boolean condition1;
        if (placeStart != null) {
            condition1 = placeStart.isBefore(feed.getEnd().getValue()) || placeStart.isEqual(feed.getEnd().getValue());
        } else condition1 = true;


        boolean condition2;
        if (placeEnd != null) {
            condition2 = placeEnd.isEqual(feed.getStart().getValue()) || placeEnd.isAfter(feed.getStart().getValue());
        } else condition2 = true;


        boolean condition3;
        if (placeString != null) {
            condition3 = feed.getPlace().getValue().equals(placeString);
        } else condition3 = true;


        boolean condition4;
        if (field != null) {
            condition4 = feed.getField().getValue().equals(field);
        } else condition4 = true;

        return condition1 && condition2 && condition3 && condition4;
    }

    private boolean treatmentEquality(Treatment treatment) {

        LocalDate treatmentDateFrom = container.getTreatmentDateFrom();
        LocalDate treatmentDateTo = container.getTreatmentDateTo();
        String tAgeFrom = container.getTreatmentAgeFrom();
        String tAgeTo = container.getTreatmentAgeTo();
        LocalDate treatmentMilkFrom = container.getTreatmentMilkFrom();
        LocalDate treatmentMilkTo = container.getTreatmentMilkTo();
        LocalDate treatmentSlaughterFrom = container.getTreatmentSlaughterFrom();
        LocalDate treatmentSlaughterTo = container.getTreatmentSlaughterTo();
        String Occurrence = container.getOccurrence();

        boolean condition1;
        if (treatmentDateFrom != null) {
            condition1 = treatmentDateFrom.isEqual(treatment.getDate().getValue()) || treatmentDateFrom.isBefore(treatment.getDate().getValue());
        } else {
            condition1 = true;
        }
        boolean condition2;
        if (treatmentDateTo != null) {
            condition2 = treatmentDateTo.isEqual(treatment.getDate().getValue()) || treatmentDateTo.isAfter(treatment.getDate().getValue());
        } else {
            condition2 = true;
        }
        boolean condition3;
        if (treatmentMilkFrom != null) {
            condition3 = treatmentMilkFrom.isEqual(treatment.getWithdrawalUntilMilk().getValue()) || treatmentMilkFrom.isBefore(treatment.getWithdrawalUntilMilk().getValue());
        } else {
            condition3 = true;
        }
        boolean condition4;
        if (treatmentMilkTo != null) {
            condition4 = treatmentMilkTo.isEqual(treatment.getWithdrawalUntilMilk().getValue()) || treatmentMilkTo.isAfter(treatment.getWithdrawalUntilMilk().getValue());
        } else {
            condition4 = true;
        }
        boolean condition5;
        if (treatmentSlaughterFrom != null) {
            condition5 = treatmentSlaughterFrom.isEqual(treatment.getWithdrawalUntilSlaughter().getValue()) || treatmentSlaughterFrom.isBefore(treatment.getWithdrawalUntilSlaughter().getValue());
        } else {
            condition5 = true;
        }
        boolean condition6;
        if (treatmentSlaughterTo != null) {
            condition6 = treatmentSlaughterTo.isEqual(treatment.getWithdrawalUntilSlaughter().getValue()) || treatmentSlaughterTo.isAfter(treatment.getWithdrawalUntilSlaughter().getValue());
        } else {
            condition6 = true;
        }
        boolean condition7;
        if (Occurrence != null && !Occurrence.equals("")) {
            String occurrenceT = treatment.getOccurrence().getValue();
            int count = 0;
            for (String s : occurrenceT.split(" ")) {

                for (String s1 : Occurrence.split(" ")) {

                    if (s.toLowerCase().equals(s1.toLowerCase())) count++;
                }
            }
            condition7 = count > 0;

        } else {
            condition7 = true;
        }
        boolean condition8;
        if (tAgeFrom != null && tAgeTo != null) {
            int age = Integer.parseInt(treatment.getAge().getValue());
            int ageF = Integer.parseInt(tAgeFrom);
            int ageT = Integer.parseInt(tAgeTo);

            condition8 = ageF <= age && ageT >= age;
        } else condition8 = true;

        return condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7 && condition8;
    }

}

interface MakeBox {
    void make(CheckBox checkBox);
}
