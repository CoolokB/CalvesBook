package BL.cBook.databaseModel;

import BL.cBook.model.*;
import BL.cBook.utils.BackUpDates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tools {

    public static void alert(String title, String header, String text) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(text);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/res/cow.png"));
            alert.showAndWait();

    }

    public static void deadCowAlert(Calf calf, Stage s) {

        Alert alert = new Alert(Alert.AlertType.WARNING);

        ButtonType buttonTypeOne = new ButtonType("Make it alive permanent");
        ButtonType buttonTypeTwo = new ButtonType("Make it alive for the current action");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        alert.setContentText("Sorry , but this cow seems to be dead");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image("/res/cow.png"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            calf.getAlive().set(true);
        }
        if (result.get() == buttonTypeTwo) {
            calf.getAlive().set(true);
            s.setOnCloseRequest(e -> calf.getAlive().set(false));
        }
    }

    public static void updateFeed(ObservableList<Calf> calves,ObservableList<FeedControl> FeedControllersList) {

        for (Calf calf : calves) {

            if (calf.getPlaces().size() > 0) {

                ObservableList<FeedHistory> temp = FXCollections.observableArrayList();

                for (FeedControl aFeedControllersList : FeedControllersList) {

                    for (int i = 0; i < calf.getPlaces().size() - 1; i++) {

                        LocalDate startDate = calf.getPlaces().get(i).getStartDate().getValue();
                        LocalDate endDate = calf.getPlaces().get(i).getEndDate().getValue();

                        boolean condition1 = aFeedControllersList.getPlace().equals(calf.getPlaces().get(i).getPlace().getValue());
                        boolean condition2 = endDate.isEqual(aFeedControllersList.getStart()) || endDate.isAfter(aFeedControllersList.getStart());
                        boolean condition3 = aFeedControllersList.getPlace().equals("GRAZING");
                        boolean condition4 = aFeedControllersList.getField().equals(calf.getPlaces().get(i).getGrazing_field_number().getValue());
                        boolean condition5 = startDate.isBefore(aFeedControllersList.getEnd()) || startDate.isEqual(aFeedControllersList.getEnd());

                        if (condition1) {
                            if (condition3) {
                                if (condition4) {
                                    if (condition2 && condition5) {
                                        addFeedHistory(aFeedControllersList, calf, i, temp);
                                    }
                                }
                            } else {
                                if (condition2 && condition5) {
                                    addFeedHistory(aFeedControllersList, calf, i, temp);
                                }
                            }
                            calf.setFeedingHistory(temp);
                        }
                    }
                }
            }
        }
    }

    private static void addFeedHistory(FeedControl f, Calf c, int i, ObservableList<FeedHistory> temp) {

        if (c.getAlive().get()) {
            FeedHistory fh = new FeedHistory();

            LocalDate start;
            LocalDate end;
            PlaceOfHabitat p = c.getPlaces().get(i);

            if (p.getStartDate().getValue().isBefore(f.getStart())) {
                start = f.getStart();
            } else {
                start = p.getStartDate().getValue();
            }

            if (p.getEndDate().getValue().isBefore(f.getEnd())) {
                end = p.getEndDate().getValue();
            } else {
                end = f.getEnd();
            }

            fh.getStart().set(start);
            fh.getEnd().set(end);

            fh.getWithdrawalSlaughter().set(end.plusMonths(12));
            fh.getWithdrawalMilk().set(end.plusMonths(6));
            fh.getPlace().set(f.getPlace());
            fh.getField().set(f.getField());

            temp.add(fh);
        }
    }

    public static class Transformer{

        public static Calf transform(DBCalf dbCalf) {

            Calf calf = new Calf();

            calf.setId(dbCalf.getId());

            DeathInfo deathInfo = new DeathInfo();
            BackUpDates backUpDates = new BackUpDates();

            calf.getMyId().set(dbCalf.getCid());
            calf.getColostrum_by_bottle().set(dbCalf.getColostrum_by_bottle());
            calf.getQuantity().set(dbCalf.getQuantity());
            calf.getGender().set(dbCalf.getGender());
            calf.getDaysOfGestation().set(dbCalf.getDaysOfGestation());
            calf.getBreed().set(dbCalf.getBreed());
            calf.getAlive().set(dbCalf.isAlive());

            calf.getDay90().set(dbCalf.getDay90());
            calf.getLactationNumber().set(dbCalf.getLactationNumber());

            calf.getDateOfBirth().set(dbCalf.getDateOfBirth());
            calf.getPlaceOfBirth().set(dbCalf.getPlaceOfBirth());

            deathInfo.getSlaughterWeight().set(dbCalf.getDbDeathInfo().getSlaughterWeight());
            deathInfo.getCauseOfDeath().set(dbCalf.getDbDeathInfo().getCauseOfDeath());
            deathInfo.getDeathReason().set(dbCalf.getDbDeathInfo().getReasonOfDeath());
            deathInfo.getDateOfDeath().set(dbCalf.getDbDeathInfo().getDateOfDeath());

            deathInfo.getSlaughterField1().set(dbCalf.getDbDeathInfo().getFatClassField1());
            deathInfo.getSlaughterField2().set(dbCalf.getDbDeathInfo().getFatClassField2());

            deathInfo.getFatField1().set(dbCalf.getDbDeathInfo().getFatClassField1());
            deathInfo.getFatField2().set(dbCalf.getDbDeathInfo().getFatClassField2());

            if (!calf.getAlive().get()) {
                backUpDates.getFeedingEnd().set(dbCalf.getBackup().getFeedingEnd());
                backUpDates.getFeedingMilkEndDate().set(dbCalf.getBackup().getFeedingMilkEnd());
                backUpDates.getFeedingSlaughterEndDate().set(dbCalf.getBackup().getFeedingSlaughterEnd());
                backUpDates.getTreatmentMilkEndDate().set(dbCalf.getBackup().getTreatmentMilkEnd());
                backUpDates.getTreatmentSlaughterEndDate().set(dbCalf.getBackup().getTreatmentSlaughterEnd());
                backUpDates.getPlaceEndDate().set(dbCalf.getBackup().getPlaceEnd());
            }

            calf.setBackup(backUpDates);
            calf.setDeathInfo(deathInfo);

            List<DBPlaceOfHabitat> dbPlacesList = dbCalf.getDbPlaceHistory();
            List<DBTreatment> dbTreatmentList = dbCalf.getDbTreatmentHistory();
            List<DBFeedHistory> dbFeedHistoryList = dbCalf.getDbFeedingHistory();

            if (dbPlacesList.size() > 0) {
                ObservableList<PlaceOfHabitat> PlacesList = FXCollections.observableArrayList();
                dbPlacesList.forEach(s -> {
                    PlaceOfHabitat place = new PlaceOfHabitat();
                    place.getGrazing_field_number().set(s.getGrazing_field_number());
                    place.getStartDate().set(s.getHabitatStartDate());
                    place.getEndDate().set(s.getHabitatEndDate());
                    place.getPlace().set(s.getHabitatPlace());
                    PlacesList.add(place);
                });
                calf.setPlaces(PlacesList);
            }

            if (dbTreatmentList.size() > 0) {
                ObservableList<Treatment> TreatmentList = FXCollections.observableArrayList();
                dbTreatmentList.forEach(s -> {
                    Treatment treatment = new Treatment();
                    treatment.getWithdrawalUntilSlaughter().set(s.getWithdrawalUntilSlaughter());
                    treatment.getWithdrawalUntilMilk().set(s.getWithdrawalUntilMilk());
                    treatment.getWithdrawalDaysSlaughter().set(s.getWithdrawalDaysSlaughter());
                    treatment.getWithdrawalDaysMilk().set(s.getWithdrawalDaysMilk());
                    treatment.getOccurrence().set(s.getOccurrence());
                    treatment.getMedicine().set(s.getNameOfMedicine());
                    treatment.getDate().set(s.getDateOfOccurrence());
                    treatment.getAmount().set(s.getAmountOfMedicine());
                    treatment.getAge().set(s.getAgeAtTheTimeOfOccurrence());
                    TreatmentList.add(treatment);
                });
                calf.setTreatment(TreatmentList);

            }

            if (dbFeedHistoryList.size() > 0) {
                ObservableList<FeedHistory> FeedHistoryList = FXCollections.observableArrayList();
                dbFeedHistoryList.forEach(s -> {
                    FeedHistory feedHistory = new FeedHistory();
                    feedHistory.getStart().set(s.getStartOfFeeding());
                    feedHistory.getEnd().set(s.getEndOfFeeding());
                    feedHistory.getField().set(s.getField());
                    feedHistory.getFoodType().set(s.getFoodType());
                    feedHistory.getPlace().set(s.getPlaceOfFeeding());
                    feedHistory.getWithdrawalMilk().set(s.getWithdrawalMilk());
                    feedHistory.getWithdrawalSlaughter().set(s.getWithdrawalSlaughter());
                    FeedHistoryList.add(feedHistory);
                });
                calf.setFeedingHistory(FeedHistoryList);
            }

            return calf;

        }

        public static DBCalf transform(Calf calf) {

            DBCalf dbcalf = new DBCalf();

            DBDeathInfo deathInfo = new DBDeathInfo();
            DBBackUpDates backUpDates = new DBBackUpDates();

            dbcalf.setCid(calf.getMyId().get());
            dbcalf.setColostrum_by_bottle(calf.getColostrum_by_bottle().get());
            dbcalf.setQuantity(calf.getQuantity().get());
            dbcalf.setGender(calf.getGender().get());
            dbcalf.setDaysOfGestation(calf.getDaysOfGestation().get());
            dbcalf.setBreed(calf.getBreed().get());
            dbcalf.setAlive(calf.getAlive().get());
            dbcalf.setAge(calf.getAge().get());

            dbcalf.setId(calf.getId());

            dbcalf.setDay90(calf.getDay90().get());
            dbcalf.setLactationNumber(calf.getLactationNumber().get());

            dbcalf.setDateOfBirth(calf.getDateOfBirth().get());
            dbcalf.setPlaceOfBirth(calf.getPlaceOfBirth().get());

            deathInfo.setSlaughterWeight(calf.getDeathInfo().getSlaughterWeight().get());
            deathInfo.setCauseOfDeath(calf.getDeathInfo().getCauseOfDeath().get());
            deathInfo.setReasonOfDeath(calf.getDeathInfo().getDeathReason().get());
            deathInfo.setDateOfDeath(calf.getDeathInfo().getDateOfDeath().get());

            deathInfo.setFatClassField1(calf.getDeathInfo().getFatField1().get());
            deathInfo.setFatClassField2(calf.getDeathInfo().getFatField2().get());

            deathInfo.setSlaughterClassField1(calf.getDeathInfo().getSlaughterField1().get());
            deathInfo.setSlaughterClassField1(calf.getDeathInfo().getSlaughterField1().get());

            dbcalf.setDbDeathInfo(deathInfo);

            if (!dbcalf.isAlive()) {
                backUpDates.setFeedingEnd(calf.getBackup().getFeedingEnd().get());
                backUpDates.setFeedingMilkEnd(calf.getBackup().getFeedingMilkEndDate().get());
                backUpDates.setFeedingSlaughterEnd(calf.getBackup().getFeedingSlaughterEndDate().get());
                backUpDates.setTreatmentMilkEnd(calf.getBackup().getTreatmentMilkEndDate().get());
                backUpDates.setTreatmentSlaughterEnd(calf.getBackup().getTreatmentSlaughterEndDate().get());
                backUpDates.setPlaceEnd(calf.getBackup().getPlaceEndDate().get());
            }

            dbcalf.setBackup(backUpDates);

            ObservableList<PlaceOfHabitat> placesList = calf.getPlaces();
            ObservableList<Treatment> treatmentList = calf.getTreatment();
            ObservableList<FeedHistory> feedHistoryList = calf.getFeedingHistory();

            if (placesList.size() > 0) {
                List<DBPlaceOfHabitat> dbPlacesList = new ArrayList<>();
                placesList.forEach(s -> {

                    DBPlaceOfHabitat place = new DBPlaceOfHabitat();
                    place.setGrazing_field_number(s.getGrazing_field_number().get());
                    place.setHabitatEndDate(s.getEndDate().get());
                    place.setHabitatPlace(s.getPlace().get());
                    place.setHabitatStartDate(s.getStartDate().get());
                    dbPlacesList.add(place);
                });
                dbcalf.setDbPlaceHistory(dbPlacesList);
            }

            if (treatmentList.size() > 0) {
                List<DBTreatment> dbTreatmentList = new ArrayList<>();
                treatmentList.forEach(s -> {
                    DBTreatment dbtreatment = new DBTreatment();
                    dbtreatment.setWithdrawalUntilSlaughter(s.getWithdrawalUntilSlaughter().get());
                    dbtreatment.setWithdrawalUntilMilk(s.getWithdrawalUntilMilk().get());
                    dbtreatment.setWithdrawalDaysSlaughter(s.getWithdrawalDaysSlaughter().get());
                    dbtreatment.setWithdrawalDaysMilk(s.getWithdrawalDaysMilk().get());
                    dbtreatment.setOccurrence(s.getOccurrence().get());
                    dbtreatment.setNameOfMedicine(s.getMedicine().get());
                    dbtreatment.setDateOfOccurrence(s.getDate().get());
                    dbtreatment.setAmountOfMedicine(s.getAmount().get());
                    dbtreatment.setAgeAtTheTimeOfOccurrence(s.getAge().get());
                    dbTreatmentList.add(dbtreatment);
                });
                dbcalf.setDbTreatmentHistory(dbTreatmentList);

            }

            if (feedHistoryList.size() > 0) {
                List<DBFeedHistory> dbFeedHistoryList = new ArrayList<>();
                feedHistoryList.forEach(s -> {
                    DBFeedHistory dbfeedHistory = new DBFeedHistory();
                    dbfeedHistory.setStartOfFeeding(s.getStart().get());
                    dbfeedHistory.setEndOfFeeding(s.getEnd().get());
                    dbfeedHistory.setField(s.getField().get());
                    dbfeedHistory.setFoodType(s.getFoodType().get());
                    dbfeedHistory.setPlaceOfFeeding(s.getPlace().get());
                    dbfeedHistory.setWithdrawalMilk(s.getWithdrawalMilk().get());
                    dbfeedHistory.setWithdrawalSlaughter(s.getWithdrawalSlaughter().get());
                    dbFeedHistoryList.add(dbfeedHistory);
                });
                dbcalf.setDbFeedingHistory(dbFeedHistoryList);
            }

            return dbcalf;

        }

        public static List<DBCalf> transform(ObservableList<Calf> list) {

            List<DBCalf> temp = new ArrayList<>();
            list.forEach(c -> temp.add(Tools.Transformer.transform(c)));
            return temp;
        }

        public static ObservableList<Calf> transform(List<DBCalf> list) {

            ObservableList<Calf> temp = FXCollections.observableArrayList();
            list.forEach(c -> temp.add(Tools.Transformer.transform(c)));
            return temp;
        }

    }
    public static class Initializer{

        public static void initializeMainFields(TableColumn<Calf, String> isAliveColumn,TableColumn<Calf, LocalDate> dateOfBirthColumn, TableColumn<Calf, String> idColumn,
                                                TableColumn<Calf, String> genderColumn, TableColumn<Calf, String> daysOfGestationColumn,
                                                TableColumn<Calf, String> breedColumn, TableColumn<Calf, String> placeOfBirthColumn,
                                                TableColumn<Calf, String> colostrumByBottleColumn, TableColumn<Calf, String> quantityColumn,
                                                TableColumn<Calf, String> age) {
            isAliveColumn.setCellValueFactory(i -> i.getValue().getAlive().asString());
            dateOfBirthColumn.setCellValueFactory(d -> d.getValue().getDateOfBirth());
            idColumn.setCellValueFactory(cellData -> cellData.getValue().getMyId());
            genderColumn.setCellValueFactory(g -> g.getValue().getGender());
            daysOfGestationColumn.setCellValueFactory(d -> d.getValue().getDaysOfGestation());
            breedColumn.setCellValueFactory(b -> b.getValue().getBreed());
            placeOfBirthColumn.setCellValueFactory(p -> p.getValue().getPlaceOfBirth());
            colostrumByBottleColumn.setCellValueFactory(c -> c.getValue().getColostrum_by_bottle());
            quantityColumn.setCellValueFactory(q -> q.getValue().getQuantity());
            age.setCellValueFactory(a -> a.getValue().ageProperty());
        }

        public static void initializeDeathFields(TableColumn<Calf, LocalDate> deathDateColumn, TableColumn<Calf, String> causeOfDeathColumn,
                                                 TableColumn<Calf, String> reasonOfDeathColumn, TableColumn<Calf, String> slaughterWeightColumn,
                                                 TableColumn<Calf, String> slaughterClass1Column, TableColumn<Calf, String> slaughterClass2Column,
                                                 TableColumn<Calf, String> fatClass1Column, TableColumn<Calf, String> fatClass2Column) {
            deathDateColumn.setCellValueFactory(d -> d.getValue().getDeathInfo().getDateOfDeath());
            causeOfDeathColumn.setCellValueFactory(c -> c.getValue().getDeathInfo().getCauseOfDeath());
            reasonOfDeathColumn.setCellValueFactory(r -> r.getValue().getDeathInfo().getDeathReason());
            slaughterWeightColumn.setCellValueFactory(w -> w.getValue().getDeathInfo().getSlaughterWeight());
            slaughterClass1Column.setCellValueFactory(s -> s.getValue().getDeathInfo().getSlaughterField1());
            slaughterClass2Column.setCellValueFactory(s -> s.getValue().getDeathInfo().getSlaughterField2());
            fatClass1Column.setCellValueFactory(f -> f.getValue().getDeathInfo().getFatField1());
            fatClass2Column.setCellValueFactory(f -> f.getValue().getDeathInfo().getFatField2());
        }

        public static void initializePlaceOfHabitatFields(TableColumn<PlaceOfHabitat, LocalDate> pStartDateColumn, TableColumn<PlaceOfHabitat,
                LocalDate> pEndDateColumn, TableColumn<PlaceOfHabitat, String> placeOfHabitatColumn,
                                                          TableColumn<PlaceOfHabitat, String> grazingFieldColumn) {
            pStartDateColumn.setCellValueFactory(p -> p.getValue().getStartDate());
            pEndDateColumn.setCellValueFactory(p -> p.getValue().getEndDate());
            placeOfHabitatColumn.setCellValueFactory(p -> p.getValue().getPlace());
            grazingFieldColumn.setCellValueFactory(g -> g.getValue().getGrazing_field_number());
        }

        public static void initializeTreatmentFields(TableColumn<Treatment, LocalDate> date, TableColumn<Treatment, String> occurrence, TableColumn<Treatment, String> medicine,
                                                     TableColumn<Treatment, String> amount, TableColumn<Treatment, String> withdrawalDaysMilk, TableColumn<Treatment, LocalDate> withdrawalUntilMilk,
                                                     TableColumn<Treatment, String> withdrawalDaysSlaughter, TableColumn<Treatment, LocalDate> withdrawalUntilSlaughter) {
            date.setCellValueFactory(s -> s.getValue().getDate());
            occurrence.setCellValueFactory(s -> s.getValue().getOccurrence());
            medicine.setCellValueFactory(s -> s.getValue().getMedicine());
            amount.setCellValueFactory(s -> s.getValue().getAmount());
            withdrawalDaysMilk.setCellValueFactory(s -> s.getValue().getWithdrawalDaysMilk());
            withdrawalUntilMilk.setCellValueFactory(s -> s.getValue().getWithdrawalUntilMilk());
            withdrawalDaysSlaughter.setCellValueFactory(s -> s.getValue().getWithdrawalDaysSlaughter());
            withdrawalUntilSlaughter.setCellValueFactory(s -> s.getValue().getWithdrawalUntilSlaughter());
        }

        public static void initializeFeedFields(TableColumn<FeedHistory, String> fPlaceColumn, TableColumn<FeedHistory, String> fFieldColumn, TableColumn<FeedHistory, String> fFeedColumn,
                                                TableColumn<FeedHistory, LocalDate> fStartDateColumn, TableColumn<FeedHistory, LocalDate> fEndDateColumn, TableColumn<FeedHistory, LocalDate> fWithdrawalUntilMilkColumn,
                                                TableColumn<FeedHistory, LocalDate> fWithdrawalUntilSlaughterColumn) {
            fPlaceColumn.setCellValueFactory(param -> param.getValue().getPlace());
            fFieldColumn.setCellValueFactory(param -> param.getValue().getField());
            fFeedColumn.setCellValueFactory(param -> param.getValue().getFoodType());
            fStartDateColumn.setCellValueFactory(param -> param.getValue().getStart());
            fEndDateColumn.setCellValueFactory(param -> param.getValue().getEnd());
            fWithdrawalUntilMilkColumn.setCellValueFactory(param -> param.getValue().getWithdrawalMilk());
            fWithdrawalUntilSlaughterColumn.setCellValueFactory(param -> param.getValue().getWithdrawalSlaughter());
        }

        public static void initializeWithdrawalMilk(TableColumn<Treatment, LocalDate> withdrawalUntilMilk) {
            Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>> cellFactory = new Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>>() {
                @Override
                public TableCell<Treatment, LocalDate> call(final TableColumn<Treatment, LocalDate> param) {
                    return new TableCell<Treatment, LocalDate>() {
                        private final DatePicker btn = new DatePicker();

                        {
                            btn.setOnAction((ActionEvent event) -> {
                                Treatment t = getTableView().getItems().get(getIndex());
                                t.getWithdrawalUntilMilk().set((btn.getValue()));
                            });
                        }

                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                                if (getTableView().getItems().get(getIndex()).getWithdrawalUntilMilk().getValue() != null) {
                                    btn.setPromptText(getTableView().getItems().get(getIndex()).getWithdrawalUntilMilk().getValue().toString());
                                }
                            }
                        }
                    };
                }
            };

            withdrawalUntilMilk.setCellFactory(cellFactory);
        }
        public static void initializeWithdrawalSlaughter(TableColumn<Treatment, LocalDate> withdrawalUntilSlaughter) {
            Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>> cellFactory = new Callback<TableColumn<Treatment, LocalDate>, TableCell<Treatment, LocalDate>>() {
                @Override
                public TableCell<Treatment, LocalDate> call(final TableColumn<Treatment, LocalDate> param) {
                    return new TableCell<Treatment, LocalDate>() {
                        private final DatePicker btn = new DatePicker();

                        {
                            btn.setOnAction((ActionEvent event) -> {
                                Treatment t = getTableView().getItems().get(getIndex());
                                t.getWithdrawalUntilSlaughter().set((btn.getValue()));
                            });
                        }

                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                                if (getTableView().getItems().get(getIndex()).getWithdrawalUntilSlaughter().getValue() != null) {
                                    btn.setPromptText(getTableView().getItems().get(getIndex()).getWithdrawalUntilSlaughter().getValue().toString());
                                }

                            }
                        }
                    };
                }
            };

            withdrawalUntilSlaughter.setCellFactory(cellFactory);
        }
    }

}

