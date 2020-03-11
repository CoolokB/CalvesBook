package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.enums.*;
import BL.cBook.model.Calf;
import BL.cBook.model.FeedHistory;
import BL.cBook.model.PlaceOfHabitat;
import BL.cBook.model.Treatment;
import BL.cBook.services.MyControllerWithParameter;
import BL.cBook.utils.Filter;
import BL.cBook.utils.Loader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class EditPageController implements MyControllerWithParameter<Boolean> {

    @FXML private TableView<Calf> Table;

    @FXML private TableColumn<Calf, String> ageColumn;
    @FXML private TableColumn<Calf, String> idColumn;
    @FXML private TableColumn<Calf, String> breedColumn;
    @FXML private TableColumn<Calf, String> genderColumn;
    @FXML private TableColumn<Calf, String> quantityColumn;
    @FXML private TableColumn<Calf, String> placeOfBirthColumn;
    @FXML private TableColumn<Calf, LocalDate> dateOfBirthColumn;
    @FXML private TableColumn<Calf, String> daysOfGestationColumn;
    @FXML private TableColumn<Calf, String> colostrumByBottleColumn;
    @FXML private TableColumn<Calf, String> lactationNumberColumn;
    @FXML private TableColumn<Calf, String> milk90Column;
    @FXML private TableColumn<Calf, String> isAliveColumn;

    @FXML private TableView<Calf> deathTable;

    @FXML private TableColumn<Calf, String> idColumn1;
    @FXML private TableColumn<Calf, LocalDate> deathDateColumn;
    @FXML private TableColumn<Calf, String> causeOfDeathColumn;
    @FXML private TableColumn<Calf, String> reasonOfDeathColumn;
    @FXML private TableColumn<Calf, String> slaughterWeightColumn;
    @FXML private TableColumn<Calf, String> fatClass1Column;
    @FXML private TableColumn<Calf, String> fatClass2Column;
    @FXML private TableColumn<Calf, String> slaughterClass1Column;
    @FXML private TableColumn<Calf, String> slaughterClass2Column;

    @FXML private DatePicker birthFrom;
    @FXML private DatePicker birthTo;
    @FXML private DatePicker deathFrom;
    @FXML private DatePicker deathTo;
    @FXML private ChoiceBox<String> placePlace;
    @FXML private DatePicker placeFrom;
    @FXML private DatePicker placeTo;
    @FXML private TextField placeField;
    @FXML private DatePicker feedTo;
    @FXML private DatePicker feedFrom;
    @FXML private ChoiceBox<String> feedPlace;
    @FXML private TextField feedField;
    @FXML private DatePicker treatmentDateFrom;
    @FXML private DatePicker treatmentDateTo;
    @FXML private TextField TreatmentAgeFrom;
    @FXML private TextField TreatmentAgeTo;
    @FXML private DatePicker treatmentMilkFrom;
    @FXML private DatePicker treatmentMilkTo;
    @FXML private DatePicker treatmentSlaughterFrom;
    @FXML private DatePicker treatmentSlaughterTo;
    @FXML private TextField ageFrom;
    @FXML private TextField ageTo;
    @FXML private TextField gestationFrom;
    @FXML private TextField gestationTo;
    @FXML private TextField Occurrence;
    @FXML private Label SearchResults;
    @FXML private ChoiceBox<String> quantity;
    @FXML private ChoiceBox<String> gender;
    @FXML private ChoiceBox<String> breed;
    @FXML private ChoiceBox<String> colostrum;
    @FXML private ChoiceBox<String> aliveChoiceBox;
    @FXML private TextField slaughterWeightFrom;
    @FXML private TextField slaughterWeightTo;

    @FXML private RadioButton radioID;
    @FXML private RadioButton radioAge;
    @FXML private RadioButton radioDate;
    @FXML private RadioButton radioMin;
    @FXML private RadioButton radioMax;

    @FXML private MenuItem summary;
    @FXML private MenuItem delete;
    @FXML private MenuItem feedHistory;
    @FXML private MenuItem placeHistory;
    @FXML private MenuItem treatmentHistory;

    private MainController mainController;
    private boolean isFiltered;
    private ObservableList<Calf> list;
    private ParametersContainer container;

    @FXML public void initialize(MainController mc,Boolean filterMode) {

        this.mainController = mc;
        container = ParametersContainer.getContainer();
        Table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deathTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        if (!filterMode) {
            list = mc.getEditList();
            Table.setItems(list);
            deathTable.setItems(list);
        }

        setFields();
        setCells();
        isAliveEdit();
        setContextMenu();

    }

    private void refresh(){
        mainController.Table.refresh();
        Table.refresh();
        deathTable.refresh();
    }

    private void sort(List<Calf> list, Comparator<Calf> comp, RadioButton radio) {

        if (radio.isSelected()) {

            if (radioMin.isSelected()) {
                list.sort(comp);
            }

            if (radioMax.isSelected()) {
                list.sort(comp.reversed());
            }
        }
    }

    private void addDateOfBirth() {

        Callback<TableColumn<Calf, LocalDate>, TableCell<Calf, LocalDate>> cellFactory = new Callback<TableColumn<Calf, LocalDate>, TableCell<Calf, LocalDate>>() {
            @Override
            public TableCell<Calf, LocalDate> call(final TableColumn<Calf, LocalDate> param) {
                return new TableCell<Calf, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Calf calf = getTableView().getItems().get(getIndex());
                            calf.getDateOfBirth().set(btn.getValue());
                            refresh();
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            btn.setPromptText(getTableView().getItems().get(getIndex()).getDateOfBirth().getValue().toString());
                        }
                    }
                };
            }
        };

        dateOfBirthColumn.setCellFactory(cellFactory);
    }

    private void addDateOfDeath() {

        Callback<TableColumn<Calf, LocalDate>, TableCell<Calf, LocalDate>> cellFactory = new Callback<TableColumn<Calf, LocalDate>, TableCell<Calf, LocalDate>>() {
            @Override
            public TableCell<Calf, LocalDate> call(final TableColumn<Calf, LocalDate> param) {
                return new TableCell<Calf, LocalDate>() {
                    private final DatePicker btn = new DatePicker();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Calf calf = getTableView().getItems().get(getIndex());
                            if (!calf.getAlive().getValue()) {

                                calf.getDeathInfo().getDateOfDeath().set(btn.getValue());
                                changeDates(calf, btn.getValue());
                                refresh();
                            }
                        });
                    }

                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            if (!getTableView().getItems().get(getIndex()).getAlive().getValue()) {
                                btn.setPromptText(getTableView().getItems().get(getIndex()).getDeathInfo().getDateOfDeath().getValue().toString());
                            }
                        }
                    }
                };
            }
        };

        deathDateColumn.setCellFactory(cellFactory);
    }

    private void isAliveEdit() {

        MenuButton menuButton = new MenuButton();
        MenuItem menuItemAlive = new MenuItem("Alive");
        MenuItem menuItemDead = new MenuItem("Dead");

        menuButton.getItems().add(menuItemAlive);
        menuButton.getItems().add(menuItemDead);

        isAliveColumn.setCellFactory(new Callback<TableColumn<Calf, String>, TableCell<Calf, String>>() {

            public TableCell<Calf, String> call(TableColumn<Calf, String> param) {
                return new ComboBoxTableCell<Calf, String>() {

                    @Override
                    public void startEdit() {

                        super.startEdit();
                        Calf calf = getTableView().getItems().get(getIndex());
                        menuButton.setText(calf.getAlive().getValue().toString());
                        setGraphic(menuButton);

                        menuItemAlive.setOnAction(event -> {
                            if (!calf.getAlive().get()) {
                                calf.getAlive().set(true);
                                restore(calf);
                                refresh();
                            }
                            super.commitEdit("Alive");
                        });
                        menuItemDead.setOnAction(event -> {

                            if (calf.getAlive().get()) {
                                calf.getAlive().set(false);
                                calf.getDeathInfo().getDateOfDeath().set(LocalDate.now());
                                changeDates(calf, calf.getDeathInfo().getDateOfDeath().getValue());
                                refresh();
                            }
                            super.commitEdit("Dead");
                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

    }

    private void setFields() {

        Table.setEditable(true);
        deathTable.setEditable(true);

        milk90Column.setCellValueFactory(m -> m.getValue().getDay90());
        idColumn1.setCellValueFactory(cellData -> cellData.getValue().getMyId());
        lactationNumberColumn.setCellValueFactory(l -> l.getValue().getLactationNumber());

        Tools.Initializer.initializeMainFields(isAliveColumn,dateOfBirthColumn, idColumn, genderColumn, daysOfGestationColumn,
                breedColumn, placeOfBirthColumn, colostrumByBottleColumn, quantityColumn, ageColumn);

        Tools.Initializer.initializeDeathFields(deathDateColumn, causeOfDeathColumn, reasonOfDeathColumn,
                slaughterWeightColumn, slaughterClass1Column, slaughterClass2Column, fatClass1Column,
                fatClass2Column);

        placePlace.setItems(Place.getAllValues());
        feedPlace.setItems(Place.getAllValues());
        quantity.setItems(Quantity.getAllValues());
        gender.setItems(Gender.getAllValues());
        breed.setItems(Breed.getAllValues());
        colostrum.setItems(Colostrum_by_bottle.getAllValues());
        aliveChoiceBox.setItems(FXCollections.observableArrayList("Alive","Dead"));

    }

    private void setCells() {

        addDateOfBirth();
        addDateOfDeath();

        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        causeOfDeathColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        genderColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Gender.getAllValues()));

        daysOfGestationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        breedColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Breed.getAllValues()));
        placeOfBirthColumn.setCellFactory(ComboBoxTableCell.forTableColumn(PlaceOfBirth.getAllValues()));
        colostrumByBottleColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Colostrum_by_bottle.getAllValues()));
        quantityColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Quantity.getAllValues()));
        reasonOfDeathColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        slaughterWeightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        slaughterClass1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slaughterClass2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        fatClass1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        fatClass2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        lactationNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        milk90Column.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    private void changeDates(Calf c, LocalDate death) {

        int fSize = c.getFeedingHistory().size();
        int pSize = c.getPlaces().size();
        int tSize = c.getTreatment().size();

        if (fSize > 0) {
            if (c.getFeedingHistory().get(fSize - 1).getEnd().get().isAfter(death))
            {
                c.getBackup().getFeedingEnd().set(c.getFeedingHistory().get(fSize - 1).getEnd().getValue());
                c.getBackup().getFeedingMilkEndDate().set(c.getFeedingHistory().get(fSize - 1).getWithdrawalMilk().getValue());
                c.getBackup().getFeedingSlaughterEndDate().set(c.getFeedingHistory().get(fSize - 1).getWithdrawalSlaughter().getValue());

                c.getFeedingHistory().get(fSize - 1).getEnd().set(death);
                c.getFeedingHistory().get(fSize-1).getWithdrawalMilk().set(death);
                c.getFeedingHistory().get(fSize-1).getWithdrawalSlaughter().set(death);
            }
        }

        if (pSize > 0) { c.getPlaces().get(pSize - 1).getEndDate().set(death); }

        if (tSize > 0) {
            if (c.getTreatment().get(tSize - 1).getWithdrawalUntilMilk().get().isAfter(death)) {
                c.getBackup().getTreatmentMilkEndDate().set(c.getTreatment().get(tSize - 1).getWithdrawalUntilMilk().get());
                c.getTreatment().get(tSize - 1).getWithdrawalUntilMilk().set(death);
            }
            if (c.getTreatment().get(tSize - 1).getWithdrawalUntilSlaughter().get().isAfter(death)) {
                c.getBackup().getTreatmentSlaughterEndDate().set(c.getTreatment().get(tSize - 1).getWithdrawalUntilSlaughter().get());
                c.getTreatment().get(tSize - 1).getWithdrawalUntilSlaughter().set(death);
            }
        }
    }

    private void restore(Calf c) {

        int pSize = c.getPlaces().size();
        int fSize = c.getFeedingHistory().size();
        int tSize = c.getTreatment().size();

        if (pSize > 0) {

            c.getPlaces().get(pSize - 1).getEndDate().set(null);
        }

        if (fSize > 0) {

            c.getFeedingHistory().get(fSize - 1).getEnd().set(c.getBackup().getFeedingEnd().getValue());
            c.getFeedingHistory().get(fSize - 1).getWithdrawalMilk().set(c.getBackup().getFeedingMilkEndDate().getValue());
            c.getFeedingHistory().get(fSize - 1).getWithdrawalSlaughter().set(c.getBackup().getFeedingSlaughterEndDate().getValue());
        }

        if (tSize>0){

            c.getTreatment().get(tSize-1).getWithdrawalUntilMilk().set(c.getBackup().getTreatmentMilkEndDate().get());
            c.getTreatment().get(tSize-1).getWithdrawalUntilSlaughter().set(c.getBackup().getTreatmentSlaughterEndDate().get());

        }
    }

    @FXML private void filterCalves() {

        setSearchParameters();

        Predicate<Calf> mainInfo = calf -> {

            Boolean allNull = quantity.getValue() == null && gender.getValue() == null && breed.getValue() == null && colostrum.getValue() == null && aliveChoiceBox.getValue() == null;

            if (!allNull) {

                boolean condition1;
                if (quantity.getValue() != null) {
                    condition1 = calf.getQuantity().get().equals(quantity.getValue());
                } else {
                    condition1 = true;
                }

                boolean condition2;
                if (gender.getValue() != null) {
                    condition2 = calf.getGender().get().equals(gender.getValue());
                } else {
                    condition2 = true;
                }

                boolean condition3;
                if (breed.getValue() != null) {
                    condition3 = calf.getBreed().get().equals(breed.getValue());
                } else {
                    condition3 = true;
                }
                boolean condition4;
                if (colostrum.getValue() != null) {
                    condition4 = calf.getColostrum_by_bottle().get().equals(colostrum.getValue());
                } else {
                    condition4 = true;
                }
                boolean condition5;
                if (aliveChoiceBox.getValue() != null) {

                    boolean isAlive = calf.getAlive().get();
                    boolean alive = isAlive && aliveChoiceBox.getValue().equals("Alive");
                    boolean dead = !isAlive && aliveChoiceBox.getValue().equals("Dead");

                    condition5=alive||dead;

                } else {
                    condition5 = true;
                }

                return condition1 && condition2 && condition3 && condition4 && condition5;

            } else return true;
        };
        Predicate<Calf> birthPredicate = calf -> {
            LocalDate date = calf.getDateOfBirth().getValue();

            Boolean condition1;
            if (birthFrom.getValue() != null) {
                condition1 = date.isEqual(birthFrom.getValue()) || date.isAfter(birthFrom.getValue());
            } else {
                condition1 = true;
            }
            Boolean condition2;
            if (birthTo.getValue() != null) {
                condition2 = date.isBefore(birthTo.getValue()) || date.isEqual(birthTo.getValue());
            } else {
                condition2 = true;
            }
            return condition1 && condition2;
        };
        Predicate<Calf> deathPredicate = calf -> {

            Boolean condition1;
            Boolean condition2;

            if ((deathFrom.getValue() != null & deathTo.getValue() != null) | (deathFrom.getValue() != null | deathTo.getValue() != null)) {
                if (calf.getDeathInfo().getDateOfDeath() == null) {
                    return false;
                } else {
                    LocalDate date2 = calf.getDeathInfo().getDateOfDeath().getValue();


                    if (deathFrom.getValue() != null) {
                        condition1 = date2.isEqual(deathFrom.getValue()) | date2.isAfter(deathFrom.getValue());
                    } else {
                        condition1 = true;
                    }
                    if (deathTo.getValue() != null) {
                        condition2 = date2.isBefore(deathTo.getValue()) | date2.isEqual(deathTo.getValue());
                    } else {
                        condition2 = true;
                    }
                    return condition1 && condition2;
                }
            }
            return true;
        };
        Predicate<Calf> placePredicate = calf -> {

            Boolean empty = calf.getPlaces().size() == 0;
            Boolean allNull = placeField.getText().equals("") && placePlace.getValue() == null && placeFrom.getValue() == null && placeTo.getValue() == null;
            boolean skip = empty && allNull;

            if (!skip) {
                for (PlaceOfHabitat place : calf.getPlaces()) {

                    boolean condition1;
                    if (placeFrom.getValue() != null) {
                        condition1 = placeFrom.getValue().isBefore(place.getEndDate().getValue()) || placeFrom.getValue().isEqual(place.getEndDate().getValue());
                    } else {
                        condition1 = true;
                    }
                    boolean condition2;
                    if (placeTo.getValue() != null) {
                        condition2 = placeTo.getValue().isEqual(place.getStartDate().getValue()) || placeTo.getValue().isAfter(place.getStartDate().getValue());
                    } else {
                        condition2 = true;
                    }
                    boolean condition3;
                    if (placePlace.getValue() != null) {
                        condition3 = place.getPlace().getValue().equals(placePlace.getValue());
                    } else {
                        condition3 = true;
                    }
                    boolean condition4;
                    if (!placeField.getText().equals("")) {
                        condition4 = place.getGrazing_field_number().getValue().equals(placeField.getText());
                    } else {
                        condition4 = true;
                    }
                    if (condition1 && condition2 && condition3 && condition4) {
                        return true;
                    }
                }
                return false;
            } else return true;

        };
        Predicate<Calf> ageAndGestation = calf -> {

            boolean condition1;
            if (!ageFrom.getText().equals("") && !ageTo.getText().equals("")) {
                int age = Integer.parseInt(calf.ageProperty().get());
                int ageF = Integer.parseInt(ageFrom.getText());
                int ageT = Integer.parseInt(ageTo.getText());

                condition1 = ageF <= age && ageT >= age;
            } else condition1 = true;

            boolean condition2;
            if (!gestationFrom.getText().equals("") && !gestationTo.getText().equals("")) {
                int gestation = Integer.parseInt(calf.getDaysOfGestation().getValue());
                int gestationF = Integer.parseInt(gestationFrom.getText());
                int gestationT = Integer.parseInt(gestationTo.getText());

                condition2 = gestationF <= gestation && gestationT >= gestation;
            } else condition2 = true;
            boolean condition3;
            if (!slaughterWeightFrom.getText().equals("") && !slaughterWeightTo.getText().equals("")) {
                int slaughter = Integer.parseInt(calf.getDeathInfo().getSlaughterWeight().getValue());
                int slaughterF = Integer.parseInt(slaughterWeightFrom.getText());
                int slaughterT = Integer.parseInt(slaughterWeightTo.getText());

                condition3 = slaughterF <= slaughter && slaughterT >= slaughter;
            } else condition3 = true;

            return condition1 && condition2 && condition3;
        };
        Predicate<Calf> treatmentPredicate = calf -> {

            Boolean empty = calf.getTreatment().size() == 0;
            Boolean allNull = treatmentSlaughterTo.getValue() == null && treatmentSlaughterFrom.getValue() == null &&
                    treatmentMilkTo.getValue() == null && treatmentMilkFrom.getValue() == null && treatmentDateFrom.getValue() == null && treatmentDateTo.getValue() == null &&
                    Occurrence.getText().equals("") && TreatmentAgeFrom.getText().equals("") && TreatmentAgeTo.getText().equals("");
            boolean skip = empty && allNull;

            if (!skip) {
                for (Treatment treatment : calf.getTreatment()) {

                    boolean condition1;
                    if (treatmentDateFrom.getValue() != null) {
                        condition1 = treatmentDateFrom.getValue().isEqual(treatment.getDate().getValue()) || treatmentDateFrom.getValue().isBefore(treatment.getDate().getValue());
                    } else {
                        condition1 = true;
                    }
                    boolean condition2;
                    if (treatmentDateTo.getValue() != null) {
                        condition2 = treatmentDateTo.getValue().isEqual(treatment.getDate().getValue()) || treatmentDateTo.getValue().isAfter(treatment.getDate().getValue());
                    } else {
                        condition2 = true;
                    }
                    boolean condition3;
                    if (treatmentMilkFrom.getValue() != null) {
                        condition3 = treatmentMilkFrom.getValue().isEqual(treatment.getWithdrawalUntilMilk().getValue()) || treatmentMilkFrom.getValue().isBefore(treatment.getWithdrawalUntilMilk().getValue());
                    } else {
                        condition3 = true;
                    }
                    boolean condition4;
                    if (treatmentMilkTo.getValue() != null) {
                        condition4 = treatmentMilkTo.getValue().isEqual(treatment.getWithdrawalUntilMilk().getValue()) || treatmentMilkTo.getValue().isAfter(treatment.getWithdrawalUntilMilk().getValue());
                    } else {
                        condition4 = true;
                    }
                    boolean condition5;
                    if (treatmentSlaughterFrom.getValue() != null) {
                        condition5 = treatmentSlaughterFrom.getValue().isEqual(treatment.getWithdrawalUntilSlaughter().getValue()) || treatmentSlaughterFrom.getValue().isBefore(treatment.getWithdrawalUntilSlaughter().getValue());
                    } else {
                        condition5 = true;
                    }
                    boolean condition6;
                    if (treatmentSlaughterTo.getValue() != null) {
                        condition6 = treatmentSlaughterTo.getValue().isEqual(treatment.getWithdrawalUntilSlaughter().getValue()) || treatmentSlaughterTo.getValue().isAfter(treatment.getWithdrawalUntilSlaughter().getValue());
                    } else {
                        condition6 = true;
                    }
                    boolean condition7;
                    if (!Occurrence.getText().equals("")) {
                        String occurrenceText = treatment.getOccurrence().getValue();
                        String occurrenceField = Occurrence.getText();
                        condition7 = occurrenceText.toLowerCase().contains(occurrenceField.toLowerCase());

                    } else {
                        condition7 = true;
                    }

                    boolean condition8;
                    if (!TreatmentAgeFrom.getText().equals("") && !TreatmentAgeTo.getText().equals("")) {
                        int age = Integer.parseInt(treatment.getAge().getValue());
                        int ageF = Integer.parseInt(TreatmentAgeFrom.getText());
                        int ageT = Integer.parseInt(TreatmentAgeTo.getText());

                        condition8 = ageF <= age && ageT >= age;
                    } else condition8 = true;

                    if (condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7 && condition8) {
                        return true;
                    }

                }
                return false;
            } else return true;

        };
        Predicate<Calf> feedHistoryPredicate = calf -> {
            Boolean empty = calf.getFeedingHistory().size() == 0;
            Boolean allNull = feedField.getText().equals("") && feedPlace.getValue() == null && feedFrom.getValue() == null && feedTo.getValue() == null;
            boolean skip = empty && allNull;

            if (!skip) {
                for (FeedHistory f : calf.getFeedingHistory()) {
                    boolean condition1;
                    if (feedFrom.getValue() != null) {
                        condition1 = feedFrom.getValue().isEqual(f.getEnd().getValue()) || feedFrom.getValue().isBefore(f.getEnd().getValue());
                    } else {
                        condition1 = true;
                    }
                    boolean condition2;
                    if (feedTo.getValue() != null) {
                        condition2 = feedTo.getValue().isEqual(f.getStart().getValue()) || feedTo.getValue().isAfter(f.getStart().getValue());
                    } else {
                        condition2 = true;
                    }
                    boolean condition3;
                    if (feedPlace.getValue() != null) {
                        condition3 = feedPlace.getValue().equals(f.getPlace().get());
                    } else {
                        condition3 = true;
                    }
                    boolean condition4;
                    if (!feedField.getText().equals("")) {
                        condition4 = feedField.getText().equals(f.getField().get());
                    } else {
                        condition4 = true;
                    }
                    if (condition1 && condition2 && condition3 && condition4) {
                        return true;
                    }
                }
                return false;
            } else return true;
        };

        List<Predicate<Calf>> listOfPredicates = new ArrayList<>();
        listOfPredicates.add(birthPredicate);
        listOfPredicates.add(deathPredicate);
        listOfPredicates.add(placePredicate);
        listOfPredicates.add(treatmentPredicate);
        listOfPredicates.add(feedHistoryPredicate);
        listOfPredicates.add(ageAndGestation);
        listOfPredicates.add(mainInfo);

        list = Filter.filter(mainController.getCalves(), listOfPredicates);
        Table.setItems(list);
        deathTable.setItems(list);
        mainController.setCalvesFilteredList(list);

        isFiltered = true;

    }

    @FXML private void sort() {

        List<Calf> list = mainController.getCalvesFilteredList();

        Comparator<Calf> idComparator = Comparator.comparingInt(x -> Integer.parseInt(x.getMyId().getValue()));
        Comparator<Calf> ageComparator = Comparator.comparingInt(x -> Integer.parseInt(x.getAge().getValue()));
        Comparator<Calf> dateComparator = Comparator.comparing(x -> x.getDateOfBirth().getValue());

        sort(list, idComparator, radioID);
        sort(list, ageComparator, radioAge);
        sort(list, dateComparator, radioDate);
    }

    @FXML private void reset() {

        birthFrom.setValue(null);
        birthTo.setValue(null);
        deathFrom.setValue(null);
        deathTo.setValue(null);
        placePlace.setItems(Place.getAllValues());
        placeFrom.setValue(null);
        placeTo.setValue(null);
        placeField.clear();
        feedTo.setValue(null);
        feedPlace.setItems(Place.getAllValues());
        feedFrom.setValue(null);
        feedField.clear();
        treatmentDateFrom.setValue(null);
        treatmentDateTo.setValue(null);
        TreatmentAgeFrom.clear();
        TreatmentAgeTo.clear();
        treatmentMilkFrom.setValue(null);
        treatmentMilkTo.setValue(null);
        treatmentSlaughterFrom.setValue(null);
        treatmentSlaughterTo.setValue(null);
        ageFrom.clear();
        ageTo.clear();
        gestationFrom.clear();
        gestationTo.clear();
        Occurrence.clear();
        gender.setItems(Gender.getAllValues());
        placePlace.setItems(Place.getAllValues());
        feedPlace.setItems(Place.getAllValues());
        SearchResults.setText("");
        if (isFiltered) {
            list.clear();
        }

    }

    @FXML private void printPage() {

        container.setPlaceOfHabitat(placePlace.getValue());
        container.setPlaceDateFrom(placeFrom.getValue());
        container.setPlaceDateTo(placeTo.getValue());
        container.setPlaceGrazingField(placeField.getText());

        container.setFeedStartDate(feedFrom.getValue());
        container.setFeedEndDate(feedTo.getValue());

        container.setFeedPlace(feedPlace.getValue());
        container.setFeedGrazingField(feedField.getText());

        container.setTreatmentDateFrom(treatmentDateFrom.getValue());
        container.setTreatmentDateTo(treatmentDateTo.getValue());
        container.setTreatmentAgeFrom(TreatmentAgeFrom.getText());
        container.setTreatmentAgeTo(TreatmentAgeTo.getText());
        container.setTreatmentMilkFrom(treatmentMilkFrom.getValue());
        container.setTreatmentMilkTo(treatmentMilkTo.getValue());
        container.setTreatmentSlaughterFrom(treatmentSlaughterFrom.getValue());
        container.setTreatmentSlaughterTo(treatmentSlaughterTo.getValue());
        container.setOccurrence(Occurrence.getText());

        Loader.load(mainController, "/cards/ColumnChooseDialogue.fxml", "Printer",container);
    }

    private void setSearchParameters() {

        Set<String> set = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();

        if (birthFrom.getValue() != null) set.add("Birth Date from - " + birthFrom.getValue().toString() + "\n");
        if (birthTo.getValue() != null) set.add("Birth Date to - " + birthTo.getValue().toString() + "\n");
        if (deathFrom.getValue() != null) set.add("Death Date from - " + deathFrom.getValue().toString() + "\n");
        if (deathTo.getValue() != null) set.add("Death Date to - " + deathTo.getValue().toString() + "\n");
        if (placePlace.getValue() != null) set.add("Place of habitat - " + placePlace.getValue() + "\n");
        if (placeFrom.getValue() != null) set.add("Place Date from - " + placeFrom.getValue().toString() + "\n");
        if (placeTo.getValue() != null) set.add("Place Date to - " + placeTo.getValue().toString() + "\n");
        if (!placeField.getText().equals("")) set.add("Grazing field - " + placeField.getText() + "\n");
        if (feedFrom.getValue() != null) set.add("Feed start Date - " + feedFrom.getValue().toString() + "\n");
        if (feedTo.getValue() != null) set.add("Feed end  Date  - " + feedTo.getValue().toString() + "\n");
        if (feedPlace.getValue() != null) set.add("Feed place - " + feedPlace.getValue() + "\n");
        if (!feedField.getText().equals("")) set.add("feed grazing field - " + feedField.getText() + "\n");
        if (treatmentDateFrom.getValue() != null) set.add("Treatment Date from - " + treatmentDateFrom.getValue().toString() + "\n");
        if (treatmentDateTo.getValue() != null) set.add("Treatment Date to - " + treatmentDateTo.getValue().toString() + "\n");
        if (!TreatmentAgeFrom.getText().equals("")) set.add("Treatment age from - " + TreatmentAgeFrom.getText() + "\n");
        if (!TreatmentAgeTo.getText().equals("")) set.add("Treatment age to - " + TreatmentAgeTo.getText() + "\n");
        if (treatmentMilkFrom.getValue() != null) set.add("Treatment milk from - " + treatmentMilkFrom.getValue().toString() + "\n");
        if (treatmentMilkTo.getValue() != null) set.add("Treatment milk to - " + treatmentMilkTo.getValue().toString() + "\n");
        if (treatmentSlaughterFrom.getValue() != null) set.add("Treatment slaughter from - " + treatmentSlaughterFrom.getValue().toString() + "\n");
        if (treatmentSlaughterTo.getValue() != null) set.add("Treatment slaughter to- " + treatmentSlaughterTo.getValue().toString() + "\n");
        if (!gestationFrom.getText().equals("")) set.add("Gestation from - " + gestationFrom.getText() + "\n");
        if (!gestationTo.getText().equals("")) set.add("Gestation to - " + gestationTo.getText() + "\n");
        if (!ageFrom.getText().equals("")) set.add("Age from - " + ageFrom.getText() + "\n");
        if (!ageTo.getText().equals("")) set.add("Age to - " + ageTo.getText() + "\n");
        if (!Occurrence.getText().equals("")) set.add("Occurrence - " + Occurrence.getText() + "\n");
        if (quantity.getValue() != null) set.add("Quantity - " + quantity.getValue() + "\n");
        if (gender.getValue() != null) set.add("Gender - " + gender.getValue() + "\n");
        if (breed.getValue() != null) set.add("Breed - " + breed.getValue() + "\n");
        if (colostrum.getValue() != null) set.add("Colostrum - " + colostrum.getValue() + "\n");
        if (!slaughterWeightFrom.getText().equals("")) set.add("Slaughter weight from - " + slaughterWeightFrom.getText() + "\n");
        if (!slaughterWeightTo.getText().equals("")) set.add("Slaughter weight to - " + slaughterWeightTo.getText() + "\n");

        for (String string : set) {
            sb.append(string);
        }

       SearchResults.setText(sb.toString());

    }

    private void setContextMenu() {

        summary.setOnAction(s -> Loader.load(mainController, "/cards/Summary.fxml", Table.getSelectionModel().getSelectedItem().getMyId().getValue()));

        delete.setOnAction(s -> {

            ObservableList<Calf> list = Table.getSelectionModel().getSelectedItems();
            mainController.getService().deleteAll(Tools.Transformer.transform(list));
            mainController.getCalves().removeAll(list);
            if (isFiltered) Table.getItems().removeAll(list);

        });

        feedHistory.setOnAction(s ->
                Loader.load(mainController, "/cards/FeedHistory.fxml", Table.getSelectionModel().getSelectedItem().getMyId().getValue()));

        placeHistory.setOnAction(s ->
                Loader.load(mainController, "/cards/PlaceOfHabitat.fxml", Table.getSelectionModel().getSelectedItem().getMyId().getValue()));

        treatmentHistory.setOnAction(s ->
                Loader.load(mainController, "/cards/Treatment.fxml", Table.getSelectionModel().getSelectedItem().getMyId().getValue()));

    }
}