package BL.cBook.uiControllers;

import BL.cBook.databaseModel.Tools;
import BL.cBook.model.Calf;
import BL.cBook.model.FeedHistory;
import BL.cBook.model.PlaceOfHabitat;
import BL.cBook.model.Treatment;
import BL.cBook.services.MyController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;

public class SummaryController implements MyController {

    @FXML private TableView<Calf> mainTable;
    @FXML private TableView<Calf> deathTable;
    @FXML private TableView<FeedHistory> feedTable;
    @FXML private TableView<Treatment> treatmentTable;
    @FXML private TableView<PlaceOfHabitat> placeOfHabitatTable;


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
    @FXML private TableColumn<Calf, String>  milk90Column;
    @FXML private TableColumn<Calf, String> isAliveColumn;


    @FXML private TableColumn<Calf, LocalDate> deathDateColumn;
    @FXML private TableColumn<Calf, String> causeOfDeathColumn;
    @FXML private TableColumn<Calf, String> reasonOfDeathColumn;
    @FXML private TableColumn<Calf, String> slaughterWeightColumn;
    @FXML private TableColumn<Calf, String> fatClass1Column;
    @FXML private TableColumn<Calf, String> fatClass2Column;
    @FXML private TableColumn<Calf, String> slaughterClass1Column;
    @FXML private TableColumn<Calf, String> slaughterClass2Column;


    @FXML private TableColumn<FeedHistory, String> fPlaceColumn;
    @FXML private TableColumn<FeedHistory, String> fFieldColumn;
    @FXML private TableColumn<FeedHistory, String> fFeedColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fStartDateColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fEndDateColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fWithdrawalUntilMilkColumn;
    @FXML private TableColumn<FeedHistory, LocalDate> fWithdrawalUntilSlaughterColumn;


    @FXML private TableColumn<PlaceOfHabitat, LocalDate> pStartDateColumn;
    @FXML private TableColumn<PlaceOfHabitat, LocalDate> pEndDateColumn;
    @FXML private TableColumn<PlaceOfHabitat, String> placeOfHabitatColumn;
    @FXML private TableColumn<PlaceOfHabitat, String> grazingFieldColumn;


    @FXML private TableColumn<Treatment, LocalDate> tDate;
    @FXML private TableColumn<Treatment, String> occurrence;
    @FXML private TableColumn<Treatment, String> medicine;
    @FXML private TableColumn<Treatment, String> amount;
    @FXML private TableColumn<Treatment, String> tWithdrawalDaysMilk;
    @FXML private TableColumn<Treatment, LocalDate> tWithdrawalUntilMilk;
    @FXML private TableColumn<Treatment, String> tWithdrawalDaysSlaughter;
    @FXML private TableColumn<Treatment, LocalDate> tWithdrawalUntilSlaughter;


    @FXML
    public void initialize(MainController msc) {

        mainTable.setItems(msc.getEditList());
        deathTable.setItems(msc.getEditList());
        feedTable.setItems(msc.getEditList().get(0).getFeedingHistory());
        placeOfHabitatTable.setItems(msc.getEditList().get(0).getPlaces());
        treatmentTable.setItems(msc.getEditList().get(0).getTreatment());
        TableInit();
    }

    private void TableInit() {

        lactationNumberColumn.setCellValueFactory(l -> l.getValue().getLactationNumber());
        milk90Column.setCellValueFactory(m -> m.getValue().getDay90());

        Tools.Initializer.initializeMainFields(isAliveColumn,dateOfBirthColumn, idColumn, genderColumn, daysOfGestationColumn,
                breedColumn, placeOfBirthColumn, colostrumByBottleColumn, quantityColumn, ageColumn);

        Tools.Initializer.initializeDeathFields(deathDateColumn, causeOfDeathColumn, reasonOfDeathColumn,
                slaughterWeightColumn, slaughterClass1Column, slaughterClass2Column,
                fatClass1Column, fatClass2Column);

        Tools.Initializer.initializePlaceOfHabitatFields(pStartDateColumn, pEndDateColumn,
                placeOfHabitatColumn, grazingFieldColumn);

        Tools.Initializer.initializeTreatmentFields(tDate, occurrence, medicine, amount, tWithdrawalDaysMilk,
                tWithdrawalUntilMilk, tWithdrawalDaysSlaughter, tWithdrawalUntilSlaughter);

        Tools.Initializer.initializeFeedFields(fPlaceColumn, fFieldColumn, fFeedColumn, fStartDateColumn,
                fEndDateColumn, fWithdrawalUntilMilkColumn, fWithdrawalUntilSlaughterColumn);

    }

}
